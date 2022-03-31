package gr.aegean.palaemon.rulesengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PassengerAssignment {

   private String musterStation;
   private String pathId;
   private String action ;

    public String getMusterStation() {
        return musterStation;
    }

    public void setMusterStation(String musterStation) {
        this.musterStation = musterStation;
    }

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
