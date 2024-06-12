package test;

import com.os.annotation.test.message.BaseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author QuangNN
 */
@AllArgsConstructor
@Getter
@Setter
public class LevelData extends BaseMessage {
    private long level;
    private String difficulty;
    private long duration;
    private String status;
}
