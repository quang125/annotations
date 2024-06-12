package com.os.annotation.test.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.os.annotation.test.payload.DataWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@SuperBuilder
public abstract class BaseMessage {
    @JsonIgnore
    private String packageName;
    @JsonIgnore
    private String platform;
    @JsonIgnore
    public String getEvent(){
        return this.getClass().getSimpleName();
    }
    public void send() throws JsonProcessingException {
        new DataWrapper(this, packageName, platform).send();
    }
}
