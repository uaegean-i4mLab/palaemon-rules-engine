package gr.aegean.palaemon.rulesengine.service.impl;

import gr.aegean.palaemon.rulesengine.config.DroolsConfig;
import gr.aegean.palaemon.rulesengine.model.Passenger;
import gr.aegean.palaemon.rulesengine.model.PassengerAssignment;
import gr.aegean.palaemon.rulesengine.model.Path;
import gr.aegean.palaemon.rulesengine.model.SetOfPaths;
import gr.aegean.palaemon.rulesengine.service.MusterAssignmentService;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MusterAssignmentServiceImpl implements MusterAssignmentService {

    private KieSession kieSession = new DroolsConfig().getKieSession();

    @Override
    public PassengerAssignment getAssignment(Passenger passenger, List<String> blockedGeofences, SetOfPaths availablePaths, String geofence) {
        PassengerAssignment assignment = new PassengerAssignment();
        FactHandle passengerFacts = kieSession.insert(passenger);
        FactHandle geofenceFacts = kieSession.insert(geofence);
        FactHandle blockedGeofencesFacts = kieSession.insert(blockedGeofences);
        // add all available path to the knowledge base
        ArrayList<FactHandle> pathFacts = new ArrayList<>();

        //get the paths that the passenger can follow
        SetOfPaths passengerPaths = new SetOfPaths();
        passengerPaths.setPaths(availablePaths.getPaths().stream().filter(path -> {
            return path.getGeofences().contains(passenger.getGeofence());
        }).collect(Collectors.toList()));

        passengerPaths.getPaths().forEach(path -> {
            FactHandle pathFact = kieSession.insert(path);
            pathFacts.add(pathFact);
        });

        kieSession.setGlobal("passengerAssignment", assignment);

        kieSession.fireAllRules();
//        System.out.println(phaseTask.getMessageObject());

        kieSession.delete(passengerFacts);
        kieSession.delete(blockedGeofencesFacts);
        kieSession.delete(geofenceFacts);
        pathFacts.forEach(factHandle -> kieSession.delete(factHandle));

        return assignment;
    }
}
