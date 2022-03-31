package gr.aegean.palaemon.rulesengine.service.impl;

import gr.aegean.palaemon.rulesengine.config.DroolsConfig;
import gr.aegean.palaemon.rulesengine.model.Passenger;
import gr.aegean.palaemon.rulesengine.model.PassengerAssignment;
import gr.aegean.palaemon.rulesengine.model.Path;
import gr.aegean.palaemon.rulesengine.model.SetOfPaths;
import gr.aegean.palaemon.rulesengine.service.MusterAssignmentService;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusterAssignmentServiceImpl implements MusterAssignmentService {

    private KieSession kieSession=new DroolsConfig().getKieSession();

    @Override
    public PassengerAssignment getAssignment(Passenger passenger, List<String> blockedGeofences, SetOfPaths availablePaths) {
        PassengerAssignment assignment = new PassengerAssignment();
        boolean preferableFree = true;

        kieSession.insert(passenger);
        kieSession.insert(blockedGeofences);
        // add all available path to the knowledge base
        availablePaths.getPaths().forEach(path -> kieSession.insert(path));

        kieSession.setGlobal("passengerAssignment",assignment);

        kieSession.fireAllRules();
//        System.out.println(phaseTask.getMessageObject());

        return  assignment;
    }
}
