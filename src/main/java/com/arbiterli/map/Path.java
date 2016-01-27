package com.arbiterli.map;

import com.arbiterli.map.model.Chain;
import com.arbiterli.map.model.Station;

public class Path {
    public Chain chain;
    public Station station;
    public int distance;
    
    public Path(Chain c, Station s) {
        this.chain = c;
        this.station = s;
    }
    
    public Path(Chain c, Station s, int distance) {
        this(c, s);
        this.distance = distance;
    }
    
    public String show() {
        return chain.getName() + "  " + station.getName() + "  " + distance;
    }
}
