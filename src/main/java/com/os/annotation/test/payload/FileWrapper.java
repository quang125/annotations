package com.os.annotation.test.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.os.annotation.test.util.Logger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
@Getter
public class FileWrapper {
    private File file;
    public FileWrapper(File file) {
        this.file = file;
    }
    public String send(){
        try {
            Unirest.setTimeouts(0,0);
            HttpResponse<String> response = Unirest.post("https://dwh-api.com/file/event-log-v2")
                    .field("file", file)
                    .asString();
            Logger.info("Data sent to server: " + response.getBody());
            return response.getBody();
        } catch (Exception e) {
            Logger.error(e);
            return "";
        }
    }
}

