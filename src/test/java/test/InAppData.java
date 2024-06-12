package test;
import com.os.annotation.test.message.BaseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @author QuangNN
 */
@AllArgsConstructor
@Getter
@SuperBuilder
public class InAppData extends BaseMessage {
    private String packageId;
    private String currency;


}
