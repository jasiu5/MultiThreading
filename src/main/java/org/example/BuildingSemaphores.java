package org.example;

import java.util.concurrent.Semaphore;

public class BuildingSemaphores implements Building{

    private final int lifts;
    private final Semaphore mutex = new Semaphore(1, true);
    private final Semaphore waitForLift = new Semaphore(1, true);
    private int liftUsage[];
    private boolean liftAvail[];
    private int numLiftsAvail;
    public BuildingSemaphores(int numLifts) {
        this.lifts = numLifts;
        this.liftUsage = new int[numLifts];
        this.liftAvail = new boolean[numLifts];
        for (int i = 0; i < numLifts; i++){
            this.liftAvail[i] = true;
        }
        this.numLiftsAvail = numLifts;
    }

    private int getFirstFreeLift(){
        int i = 0;
        while (!this.liftAvail[i]){
            i++;
        }
        if (i > lifts){
            throw new IllegalStateException("No lift available");
        }
        return i;
    }
    public int boardOnLift(int id) throws InterruptedException{
        waitForLift.acquire();
        mutex.acquire();
        int availLif = getFirstFreeLift();
        liftAvail[availLif] = false;
        liftUsage[availLif]++;
        numLiftsAvail--;
        System.out.println("Person " + id + " takes the lift " + availLif);
        mutex.release();
        if (numLiftsAvail != 0 ){
            waitForLift.release();
        }
        return availLif;
    }

    public void boardOffLift(int id, int liftId) throws InterruptedException{
        mutex.acquire();
        liftAvail[liftId] = true;
        numLiftsAvail++;
        System.out.println("Person " + id + " releases the lift " + liftId);
        mutex.release();
        if (numLiftsAvail == 1){
            waitForLift.release();
        }
    }

    public void showUsage(){
       for (int i = 0; i < lifts; i++){
           System.out.println("Lift " + i + " used " + liftUsage[i] + " times.");
       }
    }
}
