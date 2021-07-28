package chatbot.chatbot.dialogflow;

import java.util.HashMap;
import java.util.Map;

public class TextDialogflow {
    private Map<String, String> text = new HashMap<>();

    public TextDialogflow(String text){
        this.text.put("text", text);
        this.text.put("language_code", "pt-BR");
    }

    @Override
    public String toString() {
        return "TextDialogflow{" +
                "text=" + text +
                '}';
    }

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }
}
