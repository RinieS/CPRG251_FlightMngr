package sait.frms.application;

import java.io.*;
import java.util.ArrayList;

import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.gui.*;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 * @throws IOException 
	 * @throws InvalidFlightCodeException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws InvalidFlightCodeException, IOException, ClassNotFoundException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	ReservationManager man = new ReservationManager ();
	RandomAccessFile reserved = new RandomAccessFile("reservations.bin", "rw");
	
	}

}
