package test;

import com.os.annotation.test.message.BaseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author QuangNN
 */
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResourceData extends BaseMessage {
    private String flowType;
    private String itemType;
    private String itemId;
    private long amount;
    private String country;
}
