package gr.aegean.palaemon.rulesengine.model;

import lombok.*;

import java.util.List;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageBodyRequest {
    
    /*
    language: map <address,country_code>,
messageCode,
blockedGeofences,
action: list of follow/stay map with keys passengers hashedMacAddress,
pathIDs: list of pathIDs with key passenger hashedMacAddresss,
musteringStations: list of MusteringStation names with key passenger hashedMacAddresses
     */

    private String language;
    private String messageCode;
    private List<String> blockedGeofences;
    private String pathId;
    private String musteringStation;
    private String action;

    private String geofence;

}
