package com.os.annotation.test.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.os.annotation.test.response.FileResponse;
import com.os.annotation.test.message.BaseMessage;
import com.os.annotation.test.payload.DataWrapper;
import com.os.annotation.test.payload.FileWrapper;
import com.os.annotation.test.payload.UrlWrapper;
import com.os.annotation.test.util.JsonUtil;
import com.os.annotation.test.util.Logger;

import java.io.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author QuangNN
 */
public class MessageFile implements AutoCloseable, Flushable {

    private final File file = new File(UUID.randomUUID() + ".txt");
    private final FileWriter fileWriter;

    public MessageFile() throws IOException {
        this.fileWriter = new FileWriter(file);
    }

    public synchronized void append(BaseMessage message) throws IOException {
        try {
            fileWriter.write(JsonUtil.toJson(new DataWrapper(message, message.getPackageName(), message.getPlatform())) );
            fileWriter.write("\n");
        } catch (IOException e) {
            Logger.error(e);
        }
    }
    public void appendAll(Collection<BaseMessage> messages) throws IOException {
        for (BaseMessage message : messages) {
            append(message);
        }
    }
    public Optional<FileResponse> fileSend() throws IOException {
        flush();
        String response = new FileWrapper(file).send();
        if (response.isEmpty()) return Optional.empty();
        else return Optional.of(JsonUtil.fromJson(response, FileResponse.class));
    }

    public Optional<FileResponse> urlSend(AmazonS3 s3, String bucket, String folder) throws IOException {
        flush();
        String key = folder + '/' + file.getName();
        s3.putObject(bucket, key, file);
        Date expiration = Date.from(Instant.now().plusSeconds(86400));
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key)
                .withExpiration(expiration);
        String response = new UrlWrapper(s3.generatePresignedUrl(generatePresignedUrlRequest).toString()).send();
        if (!response.isEmpty()) return Optional.of(JsonUtil.fromJson(response, FileResponse.class));
        return Optional.empty();
    }
    public void clear() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file.getPath());
        writer.print("");
        writer.close();
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
        file.deleteOnExit();
    }

    @Override
    public void flush() throws IOException {
        fileWriter.flush();
    }
}
