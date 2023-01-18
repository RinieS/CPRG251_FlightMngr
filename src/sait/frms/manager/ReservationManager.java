package sait.frms.manager;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import sait.frms.problemdomain.*;


public class ReservationManager {
    private ArrayList <Reservation> reservations;
    
    
    
    public ReservationManager() throws IOException, ClassNotFoundException{
    	reservations = new ArrayList<>();
    	populateFromBinary();
    	//persist();

    }
    
    public Reservation makeReservation
    (Flight flight,String name,String citizenship) throws IOException{
    	String code = generateReservationCode(flight);
        Reservation created = new Reservation(code,flight.getCode(),
        		flight.getAirlineName(),name,citizenship,flight.getCostPerSeat(),true);
        reservations.add(created);
       persist();
        return created;
    }
/**
 * find reservations 
 * @param code
 * @param airline
 * @param name
 * @return
 */
    public ArrayList<Reservation> findReservation(String code,String airline,String name){
        ArrayList<Reservation > reservation = new ArrayList<>();
        Reservation search = null;
    	for (int i = 0; i < reservations.size();i++) {
    		search = reservations.get(i);
            if (search.getCode().equals(code) || search.getAirline().equals(airline) || search.getName().equals(name)) {
            	reservation.add(search);
            }
        }
        return reservation;
    }
    
    public Reservation findReservationByCode(String code) {
    	Reservation fr = null;
		Reservation found = null;
		if(Pattern.matches("[D,L]{1}[0-9]{4}", code)) {
			for (int i = 0; i < reservations.size(); i++) {
				fr = reservations.get(i);
				if(fr.getCode().equals(code)) {
					return fr;
				}
			}
		}
		return found;
    }
    
    public void persist() throws IOException {
    	RandomAccessFile reserved = new RandomAccessFile("reservations.bin", "rw");
     //reserved.seek(0);
          
    	  reserved.seek(reserved.length());
        	   
                String resCode =String.format("%-5s", reservations.get(reservations.size()-1).getCode());
                reserved.writeUTF(resCode);
                String fc = String.format ("%-7s", reservations.get(reservations.size()-1).getFlightCode());
                reserved.writeUTF(fc);
                String al =String.format("%-100s", reservations.get(reservations.size()-1).getAirline());
                reserved.writeUTF(al);
                String customer = String.format ("%-100s",reservations.get(reservations.size()-1).getName());
                reserved.writeUTF(customer);
                String citizen =String.format("%-100s", reservations.get(reservations.size()-1).getCitizenship());
                reserved.writeUTF(citizen);
                double cost = reservations.get(reservations.size()-1).getCost();
                reserved.writeDouble(cost);
                boolean active = true;
                reserved.writeBoolean(active);
           
           reserved.close();
    	
    }
    
    public int getAvailableSeats(Flight flight) {
    	int seats;
    	seats = flight.getSeats();
    	String flightCodeSelectedCharacters = "" + flight.getCode().charAt(0) + flight.getCode().charAt(1);
    	for (int i = 0; i < reservations.size(); i++) {
    		Reservation temp = reservations.get(i);
    		String reservationFlightCodeSelectedCharacters = ""  + temp.getFlightCode().charAt(0) + temp.getFlightCode().charAt(1);
    		  
    		if (flightCodeSelectedCharacters == reservationFlightCodeSelectedCharacters && temp.isActive()){
    			seats--;
    		}
    	} 
    	return seats;
    }
    
    public String generateReservationCode(Flight flight) {
    	String code = ""; 
    	code = flight.getTo().charAt(0) == 'Y' ? "L" : "D";
    	for(int i = 0;i < 4; i++) {
    		code += (char) ((int)Math.round(Math.random()* 9.0)+48);
    		
    	}
    	return code;
    }
    
    public void populateFromBinary() throws IOException, ClassNotFoundException {
    	RandomAccessFile reserved = new RandomAccessFile("reservations.bin", "rw");
    	
    	if (reserved.length()!= 0) {
    		for (long position = 0; position < reserved.length(); position += 331) {
    			reserved.seek(position);
    	String resCode = reserved.readUTF().trim();
    	String fc = reserved.readUTF().trim();
    	String al = reserved.readUTF().trim();
    	String customer = reserved.readUTF().trim();
    	String citizen = reserved.readUTF().trim();
    	double cost = reserved.readDouble();
    	boolean active = reserved.readBoolean();
    	Reservation made = new Reservation (resCode, fc, al, customer, citizen, cost, active);
    	reservations.add(made);
    	}
    	}
    		
    	}
    	
       
       
    }
