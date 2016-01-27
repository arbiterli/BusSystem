package com.arbiterli;

import com.arbiterli.map.CommandCenter;

public class run {
    public static void main(String[] args) {
        
        try {
            CommandCenter cc = new CommandCenter();
            cc.load("D:/chain.txt");
            cc.addIngoreChain("222(夜间线)");
            cc.find("城站火车站(城站路)", "西麻滩");
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
