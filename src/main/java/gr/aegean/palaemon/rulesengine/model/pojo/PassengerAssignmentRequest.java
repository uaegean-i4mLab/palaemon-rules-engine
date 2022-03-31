package gr.aegean.palaemon.rulesengine.model.pojo;


import gr.aegean.palaemon.rulesengine.model.MusterStation;
import gr.aegean.palaemon.rulesengine.model.Passenger;
import lombok.*;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PassengerAssignmentRequest {
    private List<Passenger> passengers;
    private List<MusterStation> musteringStations;
    private List<String> blocked;
}
