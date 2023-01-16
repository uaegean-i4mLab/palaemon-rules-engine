package gr.aegean.palaemon.rulesengine.utils;

import gr.aegean.palaemon.rulesengine.model.Path;

import java.util.Comparator;

public class PathSorter implements Comparator<Path> {
    @Override
    public int compare(Path set1, Path set2) {
        return set2.getGeofences().size() - set1.getGeofences().size();
    }
}
