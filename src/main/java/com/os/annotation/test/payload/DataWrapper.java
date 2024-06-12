package com.os.annotation.test.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.os.annotation.test.util.Logger;
import com.os.annotation.test.message.BaseMessage;
import com.os.annotation.test.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author QuangNN
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
@Getter
@AllArgsConstructor
public class DataWrapper {
    private String data;
    private long clientSendTime = System.currentTimeMillis();
    private String event;
    private String packageName;
    private String platform;

    public DataWrapper(BaseMessage message, String packageName, String platform) throws JsonProcessingException {
        this.data = JsonUtil.toJson(message);
        this.event = message.getEvent();
        this.packageName = packageName;
        this.platform = platform;
    }

    public void send() throws JsonProcessingException {
        try{
            HttpResponse<String> response = Unirest.post("https://dwh-api.com/event-log-v2")
                    .header("Content-Type", "application/json")
                    .body(JsonUtil.toJson(this)).asString();
            Logger.info("Data sent to server: " + response.getBody());
        } catch (UnirestException e) {
            Logger.error(e);
        }
    }
}
