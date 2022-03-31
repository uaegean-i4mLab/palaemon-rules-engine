package gr.aegean.palaemon.rulesengine.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Path {

    List<String> geofences;
    String pathId;
    boolean preferred; //denotes that this is the preferable path to follow

    public boolean containsGeofence(List<String> blocked) {
        return this.getGeofences().stream().anyMatch(blocked::contains);
    }
}
