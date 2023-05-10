package org.example;

import java.util.LinkedList;
import java.util.List;

public class BuildingMoniotrs implements Building {
    private final int numLifts;
    private List<Integer> liftAvail = new LinkedList<>();
    private int liftUsage[];

    public BuildingMoniotrs(int numLifts) {
        this.numLifts = numLifts;
        for(int i=0; i<numLifts; i++)
            this.liftAvail.add(i);
        this.liftUsage = new int[numLifts]; // Initialized to 0 by default
    }

    @Override
    public synchronized int boardOnLift(int id) throws InterruptedException {
        while (liftAvail.size() == 0)
            wait();

        int liftId = liftAvail.remove(0);
        liftUsage[liftId]++;
        System.out.println("Person "+id+" takes the lift "+liftId);

        return liftId;
    }

    @Override
    public synchronized void boardOffLift(int id, int liftId) throws InterruptedException {
        System.out.println("Person "+id+" releases the lift "+liftId);
        liftAvail.add(liftId);
        notify();
    }

    @Override
    public void showUsage() {
        for(int i=0; i<numLifts; i++)
            System.out.println("Lift "+i+" used "+liftUsage[i]);
    }

}
