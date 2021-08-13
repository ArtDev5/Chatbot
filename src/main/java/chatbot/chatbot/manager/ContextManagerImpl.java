package chatbot.chatbot.manager;

import chatbot.chatbot.interfaces.ContextManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContextManagerImpl implements ContextManager {

    private Map<String, String> context = new HashMap<>();

    @Override
    public String getContextData(String userId, String contextKey) {

        String key = userId + ":" + contextKey;

        String result = context.get(key);

        return result != null ? result : "";
    }

    @Override
    public void setContextData(String userId, String contextKey, String value) {
        String key = userId + ":" + contextKey;

        context.put(key, value);
    }

    @Override
    public void deleteContextData(String userId, String contextKey) {

        String key = userId + ":" + contextKey;

        context.remove(key);
    }

    @Override
    public void clearContext(String userId) {

        Set<String> keys = context.keySet();

        keys.forEach(key -> {
            if (key.startsWith(userId + ":")) {
                context.remove(key);
            }
        });
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "MessageContextTest{" +
                "context=" + context +
                '}';
    }
}
