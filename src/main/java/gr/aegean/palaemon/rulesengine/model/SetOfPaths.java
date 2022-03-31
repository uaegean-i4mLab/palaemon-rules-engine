package gr.aegean.palaemon.rulesengine.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SetOfPaths {

    List<Path> paths;

    public Path getFreePath(List<String> blocked) {
//        return this.getPaths().stream().anyMatch( path -> !path.containsGeofence(blocked) );
        Path path1 = this.getPaths().stream().filter(path -> !path.containsGeofence(blocked)).findFirst().get();
        return path1;
    }
}
