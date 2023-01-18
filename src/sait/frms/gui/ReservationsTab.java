package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frms.gui.FlightsTab.*;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	private DefaultListModel<Reservation> reservationsModel;
	private JList<Reservation> reservationsList ;
	
	JLabel flightLable;
	JTextField flightTextF;
	JLabel codeLable;
	JTextField codeTextF;
	JLabel airlineLable;
	JTextField airlineTextF;
	JLabel costLable;
	JTextField costTextF;
	JLabel nameLable;
	JTextField nameTextF;
	JLabel citizenshipLable;
	JTextField citizenshipTextF;
	JLabel isActiveLable;
	JComboBox  isActiveComboBox;
	
	
	JTextField codeSouthTextF;
	JTextField airlineSouthTextF;
	JTextField nameSouthTextF;
	JLabel codeLabel;
	JLabel airlineLabel;
	JLabel nameLabel;
		
	JButton findReservationsButton = new JButton("Find Reservations");
	
	
	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
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
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		
		panel.setSize(600,700);
		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);
		
		//User can only select one item at a time.
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Wrap Jlist in JScrollPane so it is scrollable
		JScrollPane scrollPane = new JScrollPane(this.reservationsList);
		scrollPane.setPreferredSize(new Dimension(500,250));
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		
		panel.add(scrollPane);
		return panel;
	}
	/**
	 * Create the east panel.
	 * @return JPanel that goes in east.
	 * @author Tara
	 * @author Jeramie
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(60,60));
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 24));
		panel.add(title,BorderLayout.NORTH);
		
		
		
		JPanel panel2 = new JPanel();
		buildEastGridLayoutReserve(panel2);
		panel.add(panel2, BorderLayout.CENTER);
		
		JButton update = new JButton("Update");
		panel.add(update, BorderLayout.SOUTH);
		update.addActionListener(new MyButtonReserveListener());
		return panel;
	}
	/**
	 * This builds the GridBagLayout inside of the East panel.
	 *  
	 * @param panel
	 * @author Jeramie
	 */
	private void buildEastGridLayoutReserve (JPanel panel) {

			panel.setLayout(new GridBagLayout());
			
			flightLable = new JLabel("Flight:");
			flightTextF = new JTextField(10);
			flightTextF.setEditable(false);
			//label and text for flight
			
			codeLable = new JLabel("Code:");
			codeTextF = new JTextField(10);
			codeTextF.setEditable(false);
			//label and text for code
			
			airlineLable = new JLabel("Airline:");
			airlineTextF = new JTextField(10);
			airlineTextF.setEditable(false);
			//label and text for airline
			
			costLable = new JLabel("Cost:");
			costTextF = new JTextField(10);
			costTextF.setEditable(false);
			//label and text for cost
			
			nameLable = new JLabel("Name:");
			nameTextF = new JTextField(10);
			//label and text for name
			
			citizenshipLable = new JLabel("Citizenship:");
			citizenshipTextF = new JTextField(10);
			//label and text for citizenship
			
			isActiveLable = new JLabel("Status:");
			String[] isActiveComboBoxSelection = {"Active","Inactive"};
			isActiveComboBox = new JComboBox(isActiveComboBoxSelection);
			GridBagConstraints gridBagReservationsReserve = new GridBagConstraints();
			//label and comboBox for Status of Reservation
			
			gridBagReservationsReserve.gridx = 0;
			gridBagReservationsReserve.gridy = 0;
			
			panel.add(codeLable,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(flightLable,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(airlineLable,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(costLable,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(nameLable,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(citizenshipLable,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(isActiveLable,gridBagReservationsReserve);
			//this builds the left side with all the lables.
			
			
			gridBagReservationsReserve.anchor = GridBagConstraints.WEST;
			gridBagReservationsReserve.fill = GridBagConstraints.HORIZONTAL;
			//this turns the gridBagLayout to be build on the other side.
			
			gridBagReservationsReserve.gridx++;
			gridBagReservationsReserve.gridy = 0;
			
			panel.add(codeTextF,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(flightTextF,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(airlineTextF,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(costTextF,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(nameTextF,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(citizenshipTextF,gridBagReservationsReserve);
			gridBagReservationsReserve.gridy++;
			panel.add(isActiveComboBox,gridBagReservationsReserve);
			//this builds the right side with all the TextFields and comboBoxes.
			
	}
	/**
	 * Create the south panel.
	 * @return JPanel that goes in south.
	 * @author Jeramie
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));		
		panel.add(buildSouthGridLayoutReserve());
		
		return panel;
	}
	/**
	 * This builds the GridBagLayout inside of the South panel.
	 * 
	 * 
	 * To be clear,
	 * I took Tara's code off of FlightTab and just ctrl+c ctrl+v her work,
	 * and then based my work off of hers.
	 * She deserves full credit.
	 * 
	 * @return JPanel panel
	 * @author Tara
	 * @author Jeramie
	 */
	private JPanel buildSouthGridLayoutReserve() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridBag = new GridBagConstraints();
		
		
		codeSouthTextF = new JTextField(10);
		airlineSouthTextF = new JTextField(10);
		nameSouthTextF = new JTextField(10);
		codeLabel = new JLabel("Code:");
		airlineLabel = new JLabel("Airline:");
		nameLabel = new JLabel("Name:");
			
		findReservationsButton = new JButton("Find Reservations");
		findReservationsButton.addActionListener(new MyButtonFindReserveListener());
		JLabel SearchText = new JLabel("Search");
		SearchText.setFont(new Font("serif", Font.PLAIN, 29));
		//button
		gridBag.gridx = 3;
		gridBag.gridy = 0;
		panel.add(SearchText, gridBag);
		//code text
		gridBag.gridx = 0;
		gridBag.gridy = 1;
		panel.add(codeLabel, gridBag);
		//
		gridBag.gridx = 2;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 1;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(codeSouthTextF, gridBag);
		
		gridBag.gridx = 0;
		gridBag.gridy = 2;
		panel.add(airlineLabel, gridBag);
		
		gridBag.gridx = 2;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 2;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(airlineSouthTextF, gridBag);
		
		gridBag.gridx = 0;
		gridBag.gridy = 3;
		panel.add(nameLabel, gridBag);
		
		gridBag.gridx = 2;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 3;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(nameSouthTextF, gridBag);
		
		gridBag.gridx = 0;
		gridBag.gridwidth=GridBagConstraints.REMAINDER;
		gridBag.weightx = 1.0;
		gridBag.gridy = 4;
		gridBag.fill =GridBagConstraints.HORIZONTAL;
		panel.add(findReservationsButton, gridBag);
			   	    
	        return panel;
	}
	/**
	 * This is an List Selection listener for the lists.
	 * It is used for displaying the code, flight, airline and cost
	 * inside the east panels, for the TextFields
	 * @author Jeramie 
	 *
	 */
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			Reservation temp = reservationsList.getSelectedValue();
			try {
			codeTextF.setText("" + temp.getCode());
			flightTextF.setText("" + temp.getFlightCode());
			airlineTextF.setText("" + temp.getAirline());
			costTextF.setText("" + temp.getCost());
			}
			catch(Exception d) {
				reservationsList.clearSelection();
			}
		}
		
	}
	/**
	 * 
	 * @author Jeramie
	 *
	 */
	private class MyButtonReserveListener implements ActionListener 
	{
		/**
		 * Called when user clicks the "Update" button.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		
			String code = "" + codeSouthTextF.getText();
			String airline = "" + airlineSouthTextF.getText();
			String name = "" + nameSouthTextF.getText();
			ArrayList<Reservation> temp = reservationManager.findReservation(code, airline, name);
			reservationsModel.removeAllElements();
			reservationsModel.setSize(temp.size());
			for(int i =0;i < temp.size(); i++ ) {
				reservationsModel.addElement(temp.get(i));
			
			}
	
		}
	}
	/**
	 * 
	 * @author 
	 *
	 */
	private class MyButtonFindReserveListener implements ActionListener 
	{
		/**
		 * Called when user clicks the "Find Reservations" button.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = codeTextF.getText();
			String airline = airlineTextF.getText();
			String name = nameTextF.getText();
			ArrayList <Reservation> temp = reservationManager.findReservation(code, airline, name);
			reservationsModel.removeAllElements();
			reservationsModel.setSize(temp.size());
			for(int i =0;i < temp.size(); i++ ) {
				reservationsModel.addElement(temp.get(i));
			
			}
			
		
			
			
		
	
		}
	}
}
