package org.example;

public interface Building {

	int boardOnLift(int id) throws InterruptedException;

	void boardOffLift(int id, int liftId) throws InterruptedException;

	void showUsage();

}
