package gr.aegean.palaemon.rulesengine.model;

import lombok.*;

import java.util.List;
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    public String getLanguage() {
        return language;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public List<String> getBlockedGeofences() {
        return blockedGeofences;
    }

    public String getPathId() {
        return pathId;
    }

    public String getMusteringStation() {
        return musteringStation;
    }

    public String getAction() {
        return action;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public void setBlockedGeofences(List<String> blockedGeofences) {
        this.blockedGeofences = blockedGeofences;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public void setMusteringStation(String musteringStation) {
        this.musteringStation = musteringStation;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
