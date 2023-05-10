package org.example;

import java.util.Random;

public class Person extends Thread {
	private static final Random rnd = new Random();

	private final int id;
	private final Building b;

	public Person(int i, Building b) {
		this.id = i;
		this.b = b;
	}

	@Override
	public void run() {
		boolean broken = false; // To use for further functionalities
		try {
			int liftId = b.boardOnLift(id);
			sleep(rnd.nextInt(500));
			b.boardOffLift(id, liftId);
		} catch (InterruptedException e) {
			broken = true;
		}
	}
}
