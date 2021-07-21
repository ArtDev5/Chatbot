package chatbot.chatbot.dialogflow;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

@Service
public class DialogflowServices {

    @Value("${dialogflowUrl}")
    private String dialogflowUrl;
    @Value("${serviceAccount}")
    private String serviceAccount;
    @Value("${apiEndpoint}")
    private String apiEndpoit;

    private final long ONE_HOUR_IN_MILLISECONDS = 3600 * 1000;

    private final RestTemplate restTemplate;

    public DialogflowServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public DialogflowMessage getIntentAndEntityFromDialogflow(String userMessage){

        String jwtAssigned = getSignedJWT();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtAssigned);

        TextDialogflow text = new TextDialogflow(userMessage);
        ResponseEventToDialogflow answer = new ResponseEventToDialogflow(text);

        HttpEntity<ResponseEventToDialogflow> entity = new HttpEntity<>(answer, headers);

        ReceiveMessageFromDialogflow answerDialogflow = restTemplate.postForObject(dialogflowUrl, entity,
                ReceiveMessageFromDialogflow.class);

        DialogflowMessage message = new DialogflowMessage(answerDialogflow);

        return message;
    }

    private String getSignedJWT(){
        try {
            GoogleCredential credential =
                    GoogleCredential.fromStream(new FileInputStream("src/main/resources/credentials.json"));
            PrivateKey privateKey = credential.getServiceAccountPrivateKey();
            String privateKeyId = credential.getServiceAccountPrivateKeyId();

            Date dateNow = new Date();
            Date dateExpire = new Date(dateNow.getTime() + ONE_HOUR_IN_MILLISECONDS);

            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);
            String signedJwt = JWT.create()
                    .withKeyId(privateKeyId)
                    .withIssuer(serviceAccount)
                    .withSubject(serviceAccount)
                    .withAudience(apiEndpoit)
                    .withIssuedAt(dateNow)
                    .withExpiresAt(dateExpire)
                    .sign(algorithm);

            return signedJwt;
        } catch(IOException e){
            return e.getMessage();
        }
    }

}
