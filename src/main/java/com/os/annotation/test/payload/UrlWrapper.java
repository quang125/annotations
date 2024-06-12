package com.os.annotation.test.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.os.annotation.test.util.Logger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
@Getter
public class UrlWrapper {
    private String url;
    public UrlWrapper(String url) {
        this.url = url;
    }
    public String send() {
        try{
            Unirest.setTimeouts(0,0);
            HttpResponse<String> response = Unirest.post("https://dwh-api.com/url/event-log-v2")
                    .field("url", url).asString();
            Logger.info("Data sent to server: " + response.getBody());
            return response.getBody();
        } catch (UnirestException e) {
            Logger.error(e);
            return "";
        }
    }
}
