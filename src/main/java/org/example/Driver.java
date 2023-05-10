package org.example;

public class Driver {
	public static final int NUM_LIFTS = 3;
	public static final int NUM_PERSONS = 20;
	public static void main(String[] args) throws InterruptedException {
		Building b = new BuildingMonitors(NUM_LIFTS);
		Person[] p = new Person[NUM_PERSONS];
		for (int i=0; i<NUM_PERSONS; i++) {
			p[i] = new Person(i, b);
			p[i].start();
		}
		for (Person per: p){
			per.join();
		}
		b.showUsage();
	}
}
