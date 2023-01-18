package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase 
{
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;
	
	private DefaultListModel<Flight> flightsModel;
	
	private JLabel flightLable;
	private JTextField flightTextF;
	private JLabel airlineLable;
	private JTextField airlineTextF;
	private JLabel dayLable;
	private JTextField dayTextF;
	private JLabel timeLable;
	private JTextField timeTextF;
	private JLabel costLable;
	private JTextField costTextF;
	private JLabel nameLable;
	private JTextField nameTextF;
	private JLabel citizenshipLable;
	private JTextField citizenshipTextF;
	private JButton reserve;
	//East panel parts
	
	private JComboBox facenameCombo;
	private JComboBox facenameCombo2;
	private JComboBox facenameCombo3;
	private JLabel fromLabel;
	private JLabel toLabel;
	private JLabel dayLabel;
	private JButton findFlightsButton;
	private JLabel flightFinderText;
	//South Panel parts
	
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;
		
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 * @author Tara
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setSize(600,700);
		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);
		scrollPane.setPreferredSize(new Dimension(500,250));
		flightsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		return panel;
	}
	/**
	 * Creates the east panel.
	 * @return JPanel that goes around the east.
	 * @author Jeramie
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(60,60));
		JLabel title = new JLabel("Reserve");
		title.setFont(new Font("serif", Font.PLAIN, 24));
		panel.add(title,BorderLayout.NORTH);
		JPanel panel2 = new JPanel();
		buildEastGridLayoutReserve(panel2);
		panel.add(panel2, BorderLayout.CENTER);
		
		reserve = new JButton("Reserve");
		reserve.addActionListener(new MyReserveButtonSelectionListener());
		panel.add(reserve, BorderLayout.SOUTH);
		
		return panel;
	}
	/**
	 * This builds the GridBagLayout inside of the East panel.
	 * @param panel2
	 * @author Jeramie
	 */
	public void buildEastGridLayoutReserve (JPanel panel) {

		panel.setLayout(new GridBagLayout());
		
		flightLable = new JLabel("Flight:");
		flightTextF = new JTextField(10);
		flightTextF.setEditable(false);
		
		airlineLable = new JLabel("Airline:");
		airlineTextF = new JTextField(10);
		airlineTextF.setEditable(false);
		
		dayLable = new JLabel("Day:");
		dayTextF = new JTextField(10);
		dayTextF.setEditable(false);

		timeLable = new JLabel("Time:");
		timeTextF = new JTextField(10);
		timeTextF.setEditable(false);

		costLable = new JLabel("Cost:");
		costTextF = new JTextField(10);
		costTextF.setEditable(false);
		
		nameLable = new JLabel("Name:");
		nameTextF = new JTextField(10);
		
		citizenshipLable = new JLabel("Citizenship:");
		citizenshipTextF = new JTextField(10);
		
		GridBagConstraints grid = new GridBagConstraints();
		
		grid.gridx = 0;
		grid.gridy = 0;
		
		panel.add(flightLable,grid);
		grid.gridy++;
		panel.add(airlineLable,grid);
		grid.gridy++;
		panel.add(dayLable,grid);
		grid.gridy++;
		panel.add(timeLable,grid);
		grid.gridy++;
		panel.add(costLable,grid);
		grid.gridy++;
		panel.add(nameLable,grid);
		grid.gridy++;
		panel.add(citizenshipLable,grid);
		
		grid.anchor = GridBagConstraints.WEST;
		grid.fill = GridBagConstraints.HORIZONTAL;
		
		grid.gridx++;
		grid.gridy = 0;
		
		panel.add(flightTextF,grid);
		grid.gridy++;
		panel.add(airlineTextF,grid);
		grid.gridy++;
		panel.add(dayTextF,grid);
		grid.gridy++;
		panel.add(timeTextF,grid);
		grid.gridy++;
		panel.add(costTextF,grid);
		grid.gridy++;
		panel.add(nameTextF,grid);
		grid.gridy++;
		panel.add(citizenshipTextF,grid);
}
/**
 * 
 * @return
 * @author Tara
 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));		
		panel.add(workingSouthPanel());
		
		return panel;
	}

/**
 * 
 * @return
 * @author Tara
 * @author Jeramie
 */
	private JPanel workingSouthPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBag = new GridBagConstraints();
		
		ArrayList<String>fromFlights=flightManager.getAirports();
		String[]fromfl= new String[fromFlights.size()];
		String[]toFlight= new String[fromFlights.size()];
		for (int i = 0; i < fromFlights.size(); i++) {
		String[] frm = fromFlights.get(i).split(",");
		fromfl[i]= frm[0];
		toFlight[i]= frm[0];
		}
		String[]dayss = {flightManager.WEEKDAY_MONDAY, flightManager.WEEKDAY_TUESDAY, flightManager.WEEKDAY_WEDNESDAY,
				flightManager.WEEKDAY_THURSDAY, flightManager.WEEKDAY_FRIDAY, flightManager.WEEKDAY_SATURDAY,
				flightManager.WEEKDAY_SUNDAY, flightManager.WEEKDAY_ANY};
		facenameCombo = new JComboBox(fromfl);
		facenameCombo2 = new JComboBox(toFlight);
		facenameCombo3 = new JComboBox(dayss);
				
		fromLabel = new JLabel("From:");
		toLabel = new JLabel("To:");
		dayLabel = new JLabel("Day:");
			
		findFlightsButton = new JButton("Find Flights");
		findFlightsButton.addActionListener(new MyFindFlightButtonSelectionListener());
		flightFinderText = new JLabel("Flight Finder");
		flightFinderText.setFont(new Font("serif", Font.PLAIN, 29));
		
		gridBag.gridx = 3;
		gridBag.gridy = 0;
		panel.add(flightFinderText, gridBag);
		
		gridBag.gridx = 0;
		gridBag.gridy = 1;
		panel.add(fromLabel, gridBag);
		
		gridBag.gridx = 2;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 1;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(facenameCombo, gridBag);
		
		gridBag.gridx = 0;
		gridBag.gridy = 2;
		panel.add(toLabel, gridBag);
		
		gridBag.gridx = 2;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 2;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(facenameCombo2, gridBag);
		
		gridBag.gridx = 0;
		gridBag.gridy = 3;
		panel.add(dayLabel, gridBag);
		
		gridBag.gridx = 2;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 3;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(facenameCombo3, gridBag);
		
		gridBag.gridx = 0;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 4;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(findFlightsButton, gridBag);
			   	    
	        return panel;
	}
	
	/**
	 * This is an List Selection listener for the lists.
	 * It is used for displaying the selected flight, airline, day, time and cost
	 * inside the east panels, for the TextFields
	 * @author Jeramie 
	 *
	 */
	
	
	private class MyListSelectionListener implements ListSelectionListener{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			Flight temp = flightsList.getSelectedValue();
			try {
			flightTextF.setText("" + temp.getCode());
			airlineTextF.setText("" + temp.getAirlineName());
			dayTextF.setText("" + temp.getWeekday());
			timeTextF.setText("" + temp.getTime());
			costTextF.setText("" + temp.getCostPerSeat());
			}
			catch(Exception d) {
				flightsList.clearSelection();
			}
		}
	}
	/**
	 * action listener for "Reserve" button - creates a new reservation, outputs message 
	 * dialog with reservation code 
	 * @author Irina
	 *
	 */
	private class MyReserveButtonSelectionListener implements ActionListener {
		/**
		 * Called when the user clicks on the button called "Reserve"
		 */
		@Override
		
		public void actionPerformed(ActionEvent e)  {
			Flight temp = flightManager.findFlightByCode(flightTextF.getText());
			String customerName = nameTextF.getText();
			String customerCitizenship = citizenshipTextF.getText();
			
			
			Reservation newres = null;
			
				try {
					newres = reservationManager.makeReservation(temp, customerName, customerCitizenship);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			
			String reservCode = newres.getCode();
			
			JOptionPane.showMessageDialog(null, "Reservation created. Your code is " + reservCode);
		
	}
	}
	/**
	 * 
	 * @author Jeramie
	 *
	 */
			
	private class MyFindFlightButtonSelectionListener implements ActionListener {
		/**
		 * Called when the user clicks on the button called "Find Flight"
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String selectedFrom = (String) facenameCombo.getSelectedItem();
			String selectedTo = (String) facenameCombo2.getSelectedItem();
			String selectedDay = (String) facenameCombo3.getSelectedItem();
			ArrayList<Flight> temp = flightManager.findFlights(selectedFrom, selectedTo, selectedDay);
			flightsModel.removeAllElements();
			flightsModel.setSize(temp.size());
			for(int i =0;i < temp.size(); i++ ) {
				flightsModel.addElement(temp.get(i));
			}
			
		}
		
	}
	
	
}