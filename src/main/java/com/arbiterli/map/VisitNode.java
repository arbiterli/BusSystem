package com.arbiterli.map;

import java.util.List;

import com.arbiterli.map.model.Station;

public class VisitNode {
    public Station station;
    public List<Path> paths;
    public int level;
    
    public VisitNode(Station station, List<Path> paths) {
        this.station = station;
        this.paths = paths;
    }
    
    public VisitNode(Station station, List<Path> paths, int level) {
        this.station = station;
        this.paths = paths;
        this.level = level;
    }
    
    public int calDistance() {
        int sum = 0;
        for (Path p : paths) {
            sum += p.distance;
        }
        return sum;
    }
}
