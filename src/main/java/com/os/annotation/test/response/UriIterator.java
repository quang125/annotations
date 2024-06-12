package com.os.annotation.test.response;

import com.os.annotation.test.util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author QuangNN
 */
public class UriIterator implements DataIterator<ErrorMessage>, AutoCloseable{
    private final InputStream inputStream;
    private final BufferedReader bufferedReader;
    ErrorMessage current;
    String pointer;
    public UriIterator(String url) throws IOException, URISyntaxException {
        inputStream = new URI(url).toURL().openStream();
        GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    @Override
    public ErrorMessage current() {
        return current;
    }

    @Override
    public boolean next() throws IOException {
        pointer=bufferedReader.readLine();
        if(pointer==null) return false;
        current = JsonUtil.fromJson(pointer,ErrorMessage.class);
        return true;
    }

    public List<ErrorMessage> readRest() throws IOException {
        List<ErrorMessage> result = new ArrayList<>();
        while(next()) {
            result.add(current);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
        inputStream.close();
    }
}
