package chatbot.chatbot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TextDialogflow {
    private Map<String, String> text = new HashMap<>();

    public TextDialogflow(String text){
        this.text.put("text", text);
        this.text.put("language_code", "pt-BR");
    }
}
