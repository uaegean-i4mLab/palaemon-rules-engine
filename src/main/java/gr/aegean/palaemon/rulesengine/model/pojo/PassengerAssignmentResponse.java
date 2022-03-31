package gr.aegean.palaemon.rulesengine.model.pojo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PassengerAssignmentResponse {
    private String musterStation;
    private String pathId;
    private String action ;
    private String hashedMacAddress;

}
