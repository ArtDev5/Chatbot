package chatbot.chatbot.interfaces;

public interface ContextManager {

    String getContextData(String userId, String contextKey);
    void setContextData(String userId, String contextKey, String value);
    void deleteContextData(String userId, String contextKey);
    void clearContext(String userId);

}
