package chatbot.chatbot.dialogflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParametersDialogflow {
    private Date date;
    @JsonProperty("location")
    private LocationDialogflow location;
}
