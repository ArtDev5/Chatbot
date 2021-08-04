package chatbot.chatbot.manager;

import chatbot.chatbot.interfaces.ContextManager;

import java.util.HashMap;
import java.util.Map;

public class MessageContext implements ContextManager {

    private Map<String, Map<String, String>> context = new HashMap<>();

    @Override
    public String getContextData(String userId, String contextKey) {

        if(context.get(userId) != null){
            Map<String, String> result = context.get(userId);

            if(result.get(contextKey)!= null){
                return result.get(contextKey);

            }else{
                return "";
            }
        }
        return "";
    }

    @Override
    public void setContextData(String userId, String contextKey, String value) {
        if(context.get(userId) != null){
            Map<String, String> result = context.get(userId);
            result.put(contextKey, value);
            context.put(userId, result);
        }else{
            Map<String, String> result = new HashMap<>();
            result.put(contextKey, value);
            context.put(userId, result);
        }
    }

    @Override
    public void deleteContextData(String userId, String contextKey) {

        if(context.get(userId) != null){
            Map<String, String> result = context.get(userId);

            if(result != null && result.get(contextKey) != null){
                result.remove(contextKey);
                context.put(userId, result);
            }
        }
    }

    @Override
    public void clearContext(String userId) {

        if(context.get(userId) != null){
            Map<String, String> result = context.get(userId);

            if(result != null){
                result.clear();
                context.put(userId, result);
            }
        }
    }

    public Map<String, Map<String, String>> getContext() {
        return context;
    }

    public void setContext(Map<String, Map<String, String>> context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "MessageContextTest{" +
                "context=" + context +
                '}';
    }
}
