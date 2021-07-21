package chatbot.chatbot.messenger;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostEntry {
    private List<PostMessaging> messaging = new ArrayList<>();
}