package gr.aegean.palaemon.rulesengine.controllers;

import gr.aegean.palaemon.rulesengine.model.*;
import gr.aegean.palaemon.rulesengine.model.pojo.PassengerAssignmentRequest;
import gr.aegean.palaemon.rulesengine.model.pojo.PassengerAssignmentResponse;
import gr.aegean.palaemon.rulesengine.model.pojo.PassengerMessageBodyRequests;
import gr.aegean.palaemon.rulesengine.service.MessageBodyService;
import gr.aegean.palaemon.rulesengine.service.MusterAssignmentService;
import gr.aegean.palaemon.rulesengine.service.PathReaderService;
import gr.aegean.palaemon.rulesengine.service.TaskRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RuleControllers {


    @Autowired
    TaskRuleService taskRuleService;

    @Autowired
    MessageBodyService messageBodyService;

    @Autowired
    MusterAssignmentService musterAssignmentService;

    @Autowired
    PathReaderService pathReaderService;

    @GetMapping("/getMessageObject")
    public @ResponseBody
    MessageObject getMessageObject(@RequestParam String phase, @RequestParam String taskId) {
        MessageObject mo = new MessageObject();
        PhaseTask phaseTask = new PhaseTask();
        phaseTask.setPhase(phase);
        phaseTask.setTask(taskId);
        taskRuleService.getMessageObject(phaseTask, mo);
        return mo;
    }

    @PostMapping("/fetchMessageBody")
    public @ResponseBody
    List<Map<String, String>> getMessageBody(@RequestBody PassengerMessageBodyRequests messageBodyRequests) {
        List<Map<String, String>> result = new ArrayList<>();
        messageBodyRequests.getPassengerLanguages().keySet().forEach(passengerLanguageKey -> {
            String assignedPathId = messageBodyRequests.getAssignedPathIDs().get(passengerLanguageKey);
            String messageCode = messageBodyRequests.getMessageCodes().get(passengerLanguageKey);
            String passengerLanguage = messageBodyRequests.getPassengerLanguages().get(passengerLanguageKey);
            String passengerAction = messageBodyRequests.getActions().get(passengerLanguageKey);
            String musterStation = messageBodyRequests.getMusterStation().get(passengerLanguageKey);

            if (assignedPathId != null && messageCode != null && passengerLanguage != null && passengerAction != null) {
                MessageBodyRequest requests = new MessageBodyRequest();
                requests.setBlockedGeofences(messageBodyRequests.getBlockedGeofences());
                requests.setMessageCode(messageCode);
                requests.setPathId(assignedPathId);
                requests.setAction(passengerAction);
                requests.setLanguage(passengerLanguage);
                requests.setMusteringStation(musterStation);
                MessageBodyResponse mbResponse = new MessageBodyResponse();
                messageBodyService.getMessageBody(requests, mbResponse);
                Map<String, String> resultEntry = new HashMap<>();
                resultEntry.put("hashedMacAddress", passengerLanguageKey);
                resultEntry.put("content", mbResponse.getContent());
                resultEntry.put("visualAid", mbResponse.getVisualAid());
                result.add(resultEntry);
            }
        });
        return result;
    }

    @PostMapping("/fetchMusteringActions")
    public @ResponseBody
    List<PassengerAssignmentResponse> fetchMusteringActions(@RequestBody PassengerAssignmentRequest request) {

        SetOfPaths availablePaths = pathReaderService.getAllAvailablePaths();
        List<PassengerAssignmentResponse> assignments = new ArrayList<>();
        request.getPassengers().forEach(passenger -> {
            PassengerAssignment passengerAssignment = musterAssignmentService.getAssignment(passenger, request.getBlocked(), availablePaths);
            PassengerAssignmentResponse singleResponse = new PassengerAssignmentResponse();
            singleResponse.setMusterStation(passengerAssignment.getMusterStation());
            singleResponse.setHashedMacAddress(passenger.getHashedMacAddress());
            singleResponse.setAction(passengerAssignment.getAction());
            singleResponse.setPathId(passengerAssignment.getPathId());
            assignments.add(singleResponse);
        });

        return assignments;
    }


}
