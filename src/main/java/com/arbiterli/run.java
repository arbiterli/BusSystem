package com.arbiterli;

import com.arbiterli.map.CommandCenter;

public class run {
    public static void main(String[] args) {
        
        try {
            CommandCenter cc = new CommandCenter();
            cc.load("D:/chain.txt");
            cc.addIngoreChain("222(ҹ����)");
            cc.find("��վ��վ(��վ·)", "����̲");
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
