package gr.aegean.palaemon.rulesengine;

import gr.aegean.palaemon.rulesengine.model.*;
import gr.aegean.palaemon.rulesengine.service.MessageBodyService;
import gr.aegean.palaemon.rulesengine.service.MusterAssignmentService;
import gr.aegean.palaemon.rulesengine.service.TaskRuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestRuleServices {

    @Autowired
    TaskRuleService taskRuleService;

    @Autowired
    MessageBodyService messageBodyService;

    @Autowired
    MusterAssignmentService musterAssignmentService;


    @Test
    public void testExecutionOfRules() {
        PhaseTask task = new PhaseTask();
        task.setTask("1");
        task.setPhase("4");
        MessageObject mo = new MessageObject();
        taskRuleService.getMessageObject(task, mo);
        System.out.println(mo.toString());
    }

    @Test
    public void testAssignMessageBodyRules() {
        MessageBodyRequest req = new MessageBodyRequest();
        req.setAction("FOLLOW");
        req.setMessageCode("1");
        req.setLanguage("en");
        req.setPathId("PATH_1");
        req.setMusteringStation("MUSTER_STATION_1");
        ArrayList<String> blocked = new ArrayList<>();
        blocked.add("geofence1");
        req.setBlockedGeofences(blocked);

        MessageBodyResponse response = new MessageBodyResponse();


        messageBodyService.getMessageBody(req,response);
//        System.out.println(response.getContent());


    }


    @Test
    public void testAssignmentsNotBlocked(){
        //Passenger passenger, List<String> blockedGeofences, SetOfPaths availablePaths
        Passenger passenger = new Passenger();
        passenger.setDeck("Deck1");
        List<String> blockedGeofences = new ArrayList<>();
        blockedGeofences.add("g4");

        SetOfPaths availablePaths = new SetOfPaths();
        Path p1 = new Path();
        List<String> p1Geofences = new ArrayList<>();
        p1Geofences.add("g1");
        p1Geofences.add("g2");
        p1.setGeofences(p1Geofences);

        List<Path> listOfPaths = new ArrayList<>();
        listOfPaths.add(p1);

        availablePaths.setPaths(listOfPaths);

        musterAssignmentService.getAssignment(passenger,  blockedGeofences, availablePaths );

    }

    @Test
    public void testAssignmentsBlocked(){
        //Passenger passenger, List<String> blockedGeofences, SetOfPaths availablePaths
        Passenger passenger = new Passenger();
        passenger.setDeck("Deck1");
        passenger.setGeofence("geofence1");
        passenger.setHealthConditions(new ArrayList<>());
        List<String> blockedGeofences = new ArrayList<>();
        blockedGeofences.add("g6");

        SetOfPaths availablePaths = new SetOfPaths();
        Path p1 = new Path();
        List<String> p1Geofence = new ArrayList<>();
        p1Geofence.add("g1");
        p1Geofence.add("g2");
        p1.setGeofences(p1Geofence);
        p1.setPathId("PATH_1");
        p1.setPreferred(false);

        Path p2 = new Path();
        List<String> p2Geofence = new ArrayList<>();
        p2Geofence.add("g1");
        p2Geofence.add("g3");
        p2.setGeofences(p2Geofence);
        p2.setPathId("PATH_2");
        p2.setPreferred(false);

        Path p3 = new Path();
        List<String> p3Geofence = new ArrayList<>();
        p3Geofence.add("g1");
        p3Geofence.add("g5");
        p3.setGeofences(p3Geofence);
        p3.setPathId("PATH_3");
        p3.setPreferred(true);

        List<Path> listOfPaths = new ArrayList<>();
        listOfPaths.add(p1);
        listOfPaths.add(p2);
        listOfPaths.add(p3);

        availablePaths.setPaths(listOfPaths);

       PassengerAssignment assignment = musterAssignmentService.getAssignment(passenger,  blockedGeofences, availablePaths );
       System.out.println(assignment.getPathId());

    }

    @Test
    public void testAssignmentsHealthConditions(){
        //Passenger passenger, List<String> blockedGeofences, SetOfPaths availablePaths
        Passenger passenger = new Passenger();
        passenger.setDeck("Deck1");
        passenger.setGeofence("geofence1");
        ArrayList<String> healthConditions = new ArrayList();
        healthConditions.add("stretcher");
        passenger.setHealthConditions(healthConditions);
        List<String> blockedGeofences = new ArrayList<>();
        blockedGeofences.add("g6");

        SetOfPaths availablePaths = new SetOfPaths();
        Path p1 = new Path();
        List<String> p1Geofence = new ArrayList<>();
        p1Geofence.add("g1");
        p1Geofence.add("g2");
        p1.setGeofences(p1Geofence);
        p1.setPathId("PATH_1");
        p1.setPreferred(false);

        Path p2 = new Path();
        List<String> p2Geofence = new ArrayList<>();
        p2Geofence.add("g1");
        p2Geofence.add("g3");
        p2.setGeofences(p2Geofence);
        p2.setPathId("PATH_2");
        p2.setPreferred(false);

        Path p3 = new Path();
        List<String> p3Geofence = new ArrayList<>();
        p3Geofence.add("g1");
        p3Geofence.add("g5");
        p3.setGeofences(p3Geofence);
        p3.setPathId("PATH_3");
        p3.setPreferred(true);

        List<Path> listOfPaths = new ArrayList<>();
        listOfPaths.add(p1);
        listOfPaths.add(p2);
        listOfPaths.add(p3);

        availablePaths.setPaths(listOfPaths);

        PassengerAssignment assignment = musterAssignmentService.getAssignment(passenger,  blockedGeofences, availablePaths );
        System.out.println(assignment.getAction());

    }

}
