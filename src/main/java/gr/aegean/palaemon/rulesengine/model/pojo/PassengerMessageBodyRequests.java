package gr.aegean.palaemon.rulesengine.model.pojo;

import lombok.*;

import java.util.List;
import java.util.Map;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PassengerMessageBodyRequests {
    Map<String, String> passengerLanguages; //key is passenger hashMacAddress value language code
    Map<String, String> actions; //key is passenger hashMacAddress value the strings FOLLOW or WAIT
    Map<String, String> assignedPathIDs; //key is passenger hashMacAddress value the assigned Path ID for each passenger
    Map<String,String> messageCodes; //key is passenger hashMacAddress value the MessageCode that is to be sent to the passenger
    Map<String,String> musterStation; //key is passenger hashMacAddress value the MessageCode that is to be sent to the passenger
    Map<String,String> currentGeofences; // key is passenger hashMacAddress value is the geofence the user is currently at
    List<String> blockedGeofences;

}
