package gr.aegean.palaemon.rulesengine.service.impl;

import gr.aegean.palaemon.rulesengine.model.Path;
import gr.aegean.palaemon.rulesengine.model.SetOfPaths;
import gr.aegean.palaemon.rulesengine.service.PathReaderService;
import gr.aegean.palaemon.rulesengine.utils.PathSorter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class PathReaderServiceImpl implements PathReaderService {

    private final static String COMMA_DELIMITER = ",";

    @Override
    public SetOfPaths getAllAvailablePaths() {
        AtomicBoolean isPreferred = new AtomicBoolean(false);
        List<List<String>> records = new ArrayList<>();

        String pathsCSVPath = System.getenv("PATHS_CSV");

        SetOfPaths availablePaths = new SetOfPaths();
        availablePaths.setPaths(new ArrayList<>());

        try (BufferedReader br = new BufferedReader(new FileReader(pathsCSVPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Path p = new Path();
                List<String> geofences = new ArrayList<>();
                Arrays.stream(values).forEach(s -> {
                    switch (s) {
                        case "preferred":
                            isPreferred.set(true);
                            p.setPreferred(isPreferred.get());
                            break;
                        case "notPreferred":
                            isPreferred.set(false);
                            p.setPreferred(isPreferred.get());
                            break;
                        default:
                            if (s.contains("PATH")) {
                                p.setPathId(s);
                            } else {
                                geofences.add(s);
                            }
                            break;
                    }
                });
                p.setGeofences(geofences);
                availablePaths.getPaths().add(p);
            }
        } catch (IOException ex) {
            log.error(ex.getLocalizedMessage());
        }

        Collections.sort(availablePaths.getPaths(), new PathSorter());
        return availablePaths;
    }
}
