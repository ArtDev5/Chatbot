package chatbot.chatbot.controller;

import chatbot.chatbot.messenger.ReceiveMessage;
import chatbot.chatbot.messenger.ReceiveEvent;
import chatbot.chatbot.services.BotServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class MessengerController {

    @Value("${verifyToken}")
    private String verifyToken;
    @Value("${responseUrl}")
    private String responseUrl;

    BotServices bot;

    public MessengerController(BotServices bot){
        this.bot = bot;
    }

    @GetMapping
    public ResponseEntity<String> validateWebhookOnFacebook(@RequestParam(value = "hub.verify_token") String token,
                                                            @RequestParam(value = "hub.challenge") String challenge){
        if(token.equals(verifyToken)){
            return ResponseEntity.ok(challenge);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody ReceiveEvent event){

        ReceiveMessage receiveMessage = new ReceiveMessage(event);

        bot.answerUser(receiveMessage, responseUrl);

        return ResponseEntity.ok().build();
    }
}
