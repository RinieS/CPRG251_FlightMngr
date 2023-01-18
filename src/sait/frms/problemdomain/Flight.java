package sait.frms.problemdomain;

import java.util.regex.Pattern;

import sait.frms.exception.InvalidFlightCodeException;

public class Flight {
	
private String code;
private String airlineName;
private String from;
private String to;
private String weekday;
private String time;
private int seats;
private double costPerSeat;

public Flight (String code, String airlineName, String from, String to, String weekday, 
		String time, int seats, double costPerSeat) throws InvalidFlightCodeException {
	this.code = code;
	this.airlineName = airlineName;
	this.from = from;
	this.to = to; 
	this.weekday = weekday;
	this.time = time;
	this.seats = seats;
	this.costPerSeat = costPerSeat;
	parseCode(code);
}

public String getCode() {
	return code;
}

public String getAirlineName() {
	return airlineName;
}

public String getFrom() {
	return from;
}

public String getTo() {
	return to;
}

public String getWeekday() {
	return weekday;
}

public String getTime() {
	return time;
}

public int getSeats() {
	return seats;
}

public double getCostPerSeat() {
	return costPerSeat;
}

public boolean isDomestic() {
	//this is to check if the flight is local
	return true; 
}

private void parseCode (String code) throws InvalidFlightCodeException {
	if(Pattern.matches("[A-Z]{2}[-][0-9]{4}", code)) {
	}
	else {
		throw new InvalidFlightCodeException();
	}
}
public String toString() {
	return code +", From:"+ from + ", To:"+ to + ", Day:"+ weekday + ", Cost:"+ costPerSeat;
	
}

}
