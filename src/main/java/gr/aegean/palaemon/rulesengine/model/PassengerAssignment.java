package gr.aegean.palaemon.rulesengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PassengerAssignment {

   private String musterStation;
   private String pathId;
   private String action ;

   private String geofence;

}
