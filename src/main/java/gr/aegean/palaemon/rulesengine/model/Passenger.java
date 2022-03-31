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
    private String geofence;
    private List<Distance> distances;
    private List<String> healthConditions;

    public boolean requiresMedicalAssistance(){
        return healthConditions.stream().anyMatch(s -> s.equals("assisted_gait") || s.equals("severe_walking_disability")
                || s.equals("unable_to_walk") || s.equals("cognitive_impaired") || s.equals("equip_required")
                || s.equals("stretcher") || s.equals("heavy_doses") || s.equals("complicated")
        );
    }

}
