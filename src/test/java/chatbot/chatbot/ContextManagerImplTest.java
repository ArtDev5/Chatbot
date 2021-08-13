package chatbot.chatbot;

import chatbot.chatbot.manager.ContextManagerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContextManagerImplTest {

    private ContextManagerImpl contextManager = new ContextManagerImpl();

    @Test
    public void shouldReturnEmptyStringWhenNotFoundValueInContext() {
        // WHEN
        String result = contextManager.getContextData("1", "key");

        // THEN
        assertEquals(result, "");
    }

    @Test
    public void shouldReturnValueWhenKeyExistInContext() {
        //GIVEN
        contextManager.setContextData("1", "key2", "2");

        // WHEN
        String result = contextManager.getContextData("1", "key2");

        // THEN
        assertEquals(result, "2");
    }
}
