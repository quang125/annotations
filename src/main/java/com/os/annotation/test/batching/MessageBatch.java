package com.os.annotation.test.batching;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.os.annotation.test.file.MessageFile;
import com.os.annotation.test.payload.BatchWrapper;
import com.os.annotation.test.response.FileResponse;
import com.os.annotation.test.message.BaseMessage;
import com.os.annotation.test.payload.DataWrapper;
import com.os.annotation.test.util.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageBatch extends ArrayList<BaseMessage> {
    public void send() throws JsonProcessingException {
        List<DataWrapper> dataWrappers = new ArrayList<>();
        for (BaseMessage message : this) {
            DataWrapper dataWrapper = new DataWrapper(message, message.getPackageName(), message.getPlatform());
            dataWrappers.add(dataWrapper);
        }
        BatchWrapper batchWrapper = new BatchWrapper(dataWrappers);
        batchWrapper.send();
    }
    public Optional<FileResponse> fileSend(){
        try (MessageFile file = new MessageFile()) {
            file.appendAll(this);
            return file.fileSend();
        } catch (IOException e) {
            Logger.error(e);
            return Optional.empty();
        }
    }
    public Optional<FileResponse> urlSend(AmazonS3 s3, String bucket, String folder){
        try (MessageFile file = new MessageFile()) {
            file.appendAll(this);
            return file.urlSend(s3,bucket,folder);
        } catch (IOException e) {
            Logger.error(e);
            return Optional.empty();
        }
    }
}
