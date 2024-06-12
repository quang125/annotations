package com.os.annotation.test.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author QuangNN
 */

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileResponse {
    private int successCount;
    private int failCount;
    private String errorFileReceive;

    public Optional<UriIterator> getErrorMessages() throws IOException, URISyntaxException {
        if(failCount==0) return Optional.empty();
        return Optional.of(new UriIterator(errorFileReceive));
    }
}
