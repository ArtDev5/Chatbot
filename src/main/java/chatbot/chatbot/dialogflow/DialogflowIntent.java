package chatbot.chatbot.dialogflow;

public class DialogflowIntent {
    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "IntentDialogflow{" +
                "displayName='" + displayName + '\'' +
                '}';
    }
}
