package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.problemdomain.Flight;

public class FlightManager {
	public final String WEEKDAY_ANY ="Any";
	public final String WEEKDAY_MONDAY = "Monday";
	public final String WEEKDAY_TUESDAY = "Tuesday";
	public final String WEEKDAY_WEDNESDAY = "Wednesday";
	public final String WEEKDAY_THURSDAY = "Thursday";
	public final String WEEKDAY_FRIDAY = "Friday";
	public final String WEEKDAY_SATURDAY = "Saturday";
	public final String WEEKDAY_SUNDAY = "Sunday";
	private ArrayList <Flight> flights;
	private ArrayList <String> airports;
	private Scanner input;
	
	public FlightManager() throws FileNotFoundException, InvalidFlightCodeException {
		flights = new ArrayList<>();
		airports = new ArrayList<>();
		populateFlights();
		populateAirports();
		
	}
	
	
	
	
	
/**
 * Populates String ArrayList with airports codes and names from airports.csv
 * @throws FileNotFoundException
 * @author Irina
 */
	private void populateAirports() throws FileNotFoundException {
		 input = new Scanner(new File("res/airports.csv"));
		while(input.hasNext()) {
			String inFile = input.nextLine();
			
			airports.add(inFile);
		}
	}



/**
 * Populates Flight ArrayList from flights.csv
 * 
 * If the code is not valid then it would not add to the flight ArrayList
 * @throws FileNotFoundException
 * @throws InvalidFlightCodeException 
 * 
 * @author Irina
 * @author Jeramie
 * 
 */


	private void populateFlights() throws FileNotFoundException, InvalidFlightCodeException {
		input = new Scanner(new File("res/flights.csv"));
		while(input.hasNextLine()) {
			String inFile = input.nextLine();
			String[]fields = inFile.split(",");
			String code = fields[0];
			
			String airlineName = fields[1];
			String from = fields[2];
			String to = fields[3];
			String weekday = fields[4];
			String time = fields [5];
			int seats = Integer.parseInt(fields[6]);
			double costPerSeat = Double.parseDouble(fields[7]);
			
			try {
			Flight createdFlight = new Flight (code, airlineName, from, to, weekday, time, seats, costPerSeat);
			flights.add(createdFlight);
			}
			catch(InvalidFlightCodeException e) {
				
			}
			
		}
		input.close();
	}

/**
 * Finds flight from ArrayList flights by "from" "to" and "weekday"
 * @param from
 * @param to
 * @param weekday
 * @return flight
 * @author Irina
 * @author Jeramie
 */

	public ArrayList<Flight>  findFlights(String from, String to, String weekday){
		ArrayList<Flight> flight = new ArrayList<>();
		Flight search =null;
		for (int i = 0; i < flights.size(); i++) {
			search= flights.get(i);
			if(weekday.equals(WEEKDAY_ANY)) {
				if (search.getFrom().equals(from) && search.getTo().equals(to)) {
					flight.add(search);
				}
			}
			else {
				if (search.getFrom().equals(from) && search.getTo().equals(to) && search.getWeekday().equals(weekday)) {
					flight.add(search);
				}
			}
		}
		return flight ; 
	}

/**
 * 
 * @return
 * @author Irina
 */
	public ArrayList <String> getAirports(){
		return airports;
	}
	
/**
 * 
 * @return
 * @author Irina
 */
	public ArrayList <Flight> getFlights(){
		return flights;
	}
	

/**
 * Method to find an airport by a code (ex.YYC)
 * @param code
 * @return
 * @author Irina
 */
	public String findAirportByCode(String code) {
		
		String airport = "";
		if (code.length() == 3) {
		for (int i = 0; i < airports.size(); i++) {
			
		String a = airports.get(i);
		if (a.equals(code)) {
			 airport = airports.get(i+1);
		}
		}
		}
		return airport;
	}
	
	
/**
 * Finds flight object from ArrayList flights by provided flight code in format LL-DDDD. L-letter, D-digit.
 * @param code
 * @return object Flight
 * @author Irina
 */
	public Flight findFlightByCode(String code){
		Flight ff = null;
			for (int i = 0; i < flights.size(); i++) {
				ff = flights.get(i);
				if(ff.getCode().equals(code)) {
					return ff;
				}
			}
		return null;
	}
}

