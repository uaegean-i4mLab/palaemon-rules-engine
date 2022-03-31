package gr.aegean.palaemon.rulesengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Passenger {

    private String hashedMacAddress;
    private String deck;
    @JsonProperty("x_coord")
    private String xCoord;
    @JsonProperty("y_coord")
    private String yCoord;
    private String geofence;
    private List<Distance> distances;
    private List<String> healthConditions;


}
