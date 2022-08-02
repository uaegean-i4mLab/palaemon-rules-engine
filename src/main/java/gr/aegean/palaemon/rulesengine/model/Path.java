package gr.aegean.palaemon.rulesengine.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Path implements Comparable<Path> {

    List<String> geofences;
    String pathId;
    boolean preferred; //denotes that this is the preferable path to follow

    public boolean containsGeofence(List<String> blocked) {
        return this.getGeofences().stream().anyMatch(blocked::contains);
    }


    public boolean onlyStartOfPathBlocked(List<String> blocked){
        if(blocked.size() == 0 || geofences.size() == 0 || geofences.size() == 1) return false;
        return blocked.stream().anyMatch(block -> block.equals(geofences.get(0)));
    }


    @Override
    public int compareTo(Path o) {
        return Integer.compare(o.getGeofences().size(), this.getGeofences().size());
    }
}
