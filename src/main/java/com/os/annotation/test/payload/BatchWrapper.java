package com.os.annotation.test.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.os.annotation.test.util.JsonUtil;
import com.os.annotation.test.util.Logger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
@Getter
public class BatchWrapper {
    private List<DataWrapper> dataWrappers;
    public BatchWrapper(List<DataWrapper> dataWrappers) {
        this.dataWrappers = dataWrappers;
    }
    public void send() throws JsonProcessingException {
        try {
            Unirest.setTimeouts(0,0);
            HttpResponse<String> response = Unirest.post("https://dwh-api.com/batch/event-log-v2")
                    .header("Content-Type", "application/json")
                    .body(JsonUtil.toJson(dataWrappers))
                    .asString();
            Logger.info("Batch data sent to server: " + response.getBody());
        } catch (UnirestException e) {
            Logger.error(e);
        }
    }
}

