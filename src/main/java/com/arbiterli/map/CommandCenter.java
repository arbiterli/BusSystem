package com.arbiterli.map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.arbiterli.map.model.Chain;
import com.arbiterli.map.model.Station;

public class CommandCenter {
    public Map<String, Chain> chains = new HashMap<String, Chain>();
    public Map<String, Station> stations = new HashMap<String, Station>();
    public Set<String> visitedStations = new HashSet<String>();
    public List<VisitNode> queue = new LinkedList<VisitNode>();
    public List<List<Path>> availablePath = new ArrayList<List<Path>>();
    public Set<String> ingoreChains = new HashSet<String>();
    public Map<String, List<VisitNode>> nodes = new HashMap<String, List<VisitNode>>();

    public void load(String filePath) throws Exception {
        InputStream in = new FileInputStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "unicode"));
        String line = null;
        while ((line = br.readLine()) != null) {
            int index = line.indexOf("#");
            Chain chain = new Chain(line.substring(0, index));
            int pos = 0;
            for (String stationName : line.substring(index + 1).split(",")) {
                // System.out.println(stationName);
                pos++;
                Station station = stations.get(stationName);
                if (station == null) {
                    station = new Station(stationName);
                }
                chain.addStation(station, pos);
            }
            addNewChain(chain);
        }
        br.close();
    }

    public void find(String startName, String destinyName) {
        Station start = stations.get(startName);
        Station destiny = stations.get(destinyName);
        if (start == null || destiny == null) {
            System.err.println("no stations");
            return;
        }
        VisitNode startNode = new VisitNode(start, new ArrayList<Path>());
        record(startNode);
        System.out.println(start.getName());
        while (!queue.isEmpty()) {
            VisitNode node = queue.remove(0);
            if (node.level > 3) {
                break;
            }
            if (node.station.equals(destiny)) {
                logNode(node);
                availablePath.add(node.paths);
            }
            int level = node.level + 1;
            for (Chain c : node.station.getChains()) {
                if (ingoreChains.contains(c.getName()) || hasSameChain(node.paths, c)) {
                    continue;
                }
                for (Station s : c.getStations().keySet()) {
                    if (isSkip(s, c, node)) {
                        continue;
                    }
                    List<Path> cPath = new ArrayList<Path>(node.paths);
                    cPath.add(new Path(c, s, calDistance(node.station, s, c)));
                    VisitNode vNode = new VisitNode(s, cPath, level);
                    record(vNode);
                }
            }
        }
        System.out.println(nodes.size());
        System.out.println(visitedStations.size());
    }

    private boolean isSkip(Station s, Chain c, VisitNode node) {
        if (!visitedStations.contains(s.getName())) {
            return false;
        }
        List<VisitNode> sameNodes = nodes.get(s.getName());
        //System.out.println(sameNodes.size());
        for (VisitNode vn : sameNodes) {
            int newDistance = calDistance(s, node.station, c);
//            System.out.println(newDistance + node.calDistance());
//            System.out.println(vn.calDistance());
//            System.out.println("######################");
            if (newDistance + node.calDistance() >= vn.calDistance()) {
                return true;
            }
        }
        return false;
    }

    private int calDistance(Station s1, Station s2, Chain c) {
        int delta = c.getStations().get(s1) - c.getStations().get(s2);
        return delta > 0 ? delta : -delta;
    }

    private void record(VisitNode vNode) {
        queue.add(vNode);
        addToMap(vNode);
        visitedStations.add(vNode.station.getName());
    }

    private void addToMap(VisitNode node) {
        List<VisitNode> vNodes = nodes.get(node.station.getName());
        if (vNodes == null) {
            vNodes = new ArrayList<VisitNode>();
            vNodes.add(node);
        } else {
            vNodes.add(node);
        }
        nodes.put(node.station.getName(), vNodes);
    }

    private boolean hasSameChain(List<Path> paths, Chain chain) {
        for (Path p : paths) {
            if (p.chain.equals(chain)) {
                return true;
            }
        }
        return false;
    }

    public void logNode(VisitNode node) {
        System.out.println(node.station.getName());
        System.out.println(node.level);
        for (Path p : node.paths) {
            System.out.println(p.show());
        }
        System.out.println("======================================================");
    }

    public void clearEnv() {
        visitedStations.clear();
        queue.clear();
        availablePath.clear();
    }

    public void addNewChain(Chain chain) {
        Chain chainInMap = chains.get(chain.getName());
        if (chainInMap != null) {
            System.out.println("already in");
            return;
        }
        for (Station s : chain.getStations().keySet()) {
            updateStationSet(s, chain);
        }
        chains.put(chain.getName(), chain);
    }

    private void updateStationSet(Station s, Chain c) {
        Station station = stations.get(s.getName());
        if (station == null) {
            station = s;
            station.addChain(c);
        } else {
            station.addChain(c);
        }
        stations.put(s.getName(), station);
    }

    public void addIngoreChain(String chainName) {
        ingoreChains.add(chainName);
    }
}
