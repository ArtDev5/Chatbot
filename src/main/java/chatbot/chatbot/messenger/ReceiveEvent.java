package chatbot.chatbot.messenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiveEvent {
    private String object;
    private List<PostEntry> entry = new ArrayList<>();

}
