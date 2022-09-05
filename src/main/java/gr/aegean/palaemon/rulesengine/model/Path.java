package gr.aegean.palaemon.rulesengine.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class Path implements Comparable<Path> {

    List<String> geofences;
    String pathId;
    boolean preferred; //denotes that this is the preferable path to follow

    public boolean containsGeofence(List<String> blocked) {
//        blocked.stream().forEach(geo -> {log.info(" blocked geofence to check {}",blocked);});
        boolean result =  this.getGeofences().stream().anyMatch(blocked::contains);
        return  result;
    }


    public boolean onlyStartOfPathBlocked(List<String> blocked){
        if(blocked.size() == 0 || geofences.size() == 0 || geofences.size() == 1) return false;
        boolean startBlocked = blocked.stream().anyMatch(block -> block.equals(geofences.get(0)));
        AtomicBoolean result = new AtomicBoolean(true);
        if(startBlocked){
            blocked.forEach(blockage ->{
                if(geofences.contains(blockage) && !blockage.equals(geofences.get(0))){
                    result.set(false);
                }
            });
        }

        return result.get();
    }


    @Override
    public int compareTo(Path o) {
        return Integer.compare(o.getGeofences().size(), this.getGeofences().size());
    }
}
