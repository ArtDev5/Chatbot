package chatbot.chatbot.dialogflow;

import chatbot.chatbot.entities.EntitiesData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.*;

public class DialogflowServices {

    @Value("${dialogflowUrl}")
    private String dialogflowUrl;
    @Value("${serviceAccount}")
    private String serviceAccount;
    @Value("${apiEndpoint}")
    private String apiEndpoint;

    private final long ONE_HOUR_IN_MILLISECONDS = 3600 * 1000;

    private final RestTemplate restTemplate;

    public DialogflowServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public EntitiesData getDialogflowData(String userMessage){

        String jwtAssigned = getSignedJWT();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + jwtAssigned);

        TextDialogflow textDialogflow = new TextDialogflow(userMessage);
        ResponseEventToDialogflow answer = new ResponseEventToDialogflow(textDialogflow);

        HttpEntity<ResponseEventToDialogflow> entity = new HttpEntity<>(answer, headers);

        DialogflowContract dialogflowContract = restTemplate.postForObject(dialogflowUrl, entity,
                DialogflowContract.class);

        assert dialogflowContract != null;
        return getConvertedData(dialogflowContract);
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
                    .withAudience(apiEndpoint)
                    .withIssuedAt(dateNow)
                    .withExpiresAt(dateExpire)
                    .sign(algorithm);

            return signedJwt;
        } catch(IOException e){
            return e.getMessage();
        }
    }

    private EntitiesData getConvertedData(DialogflowContract dialogflowContract){
        EntitiesData entitiesData = new EntitiesData();

        QueryResult queryResult = dialogflowContract.getQueryResult();
        DialogflowIntent dialogflowIntent = queryResult.getIntent();
        String intent = dialogflowIntent.getDisplayName();
        entitiesData.setIntent(intent);

        Map<String, String> entities = getEntitiesFromDialogflow(queryResult.getEntities());
        entitiesData.setEntities(entities);

        return entitiesData;
    }

    private Map<String, String> getEntitiesFromDialogflow(Map<String, Object> entities){
        Map<String, String> entitiesValues = new HashMap<>();

        if(entities != null) {
            for(Map.Entry<String, Object> values : entities.entrySet()){
                if(values.getKey().equals("location")){
                    Map<String, String> location = (Map<String, String>) values.getValue();
                    entitiesValues.put("city", location.get("city"));
                }else{
                    entitiesValues.put(values.getKey(), (String) values.getValue());
                }
            }
        }
        return entitiesValues;

    }

}
