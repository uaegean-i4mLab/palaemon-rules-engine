package gr.aegean.palaemon.rulesengine.service;

import gr.aegean.palaemon.rulesengine.model.*;

import java.util.List;

public interface MusterAssignmentService {

    public PassengerAssignment getAssignment(Passenger passenger, List<String> blockedGeofences, SetOfPaths availablePaths);
}
