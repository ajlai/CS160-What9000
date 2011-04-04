package edu.berkeley.cs160.groupp;

public class IVR {

	private static final int START = 0;
	private static final int MAIN_MENU = 1;
	private static final int MAKE_APPT = 2;
	private static final int DRIVER_LICENSE = 3;
	private static final int ORDER_FORMS = 4;
	private static final int MAIN_MENU_ANNOUNCE  = 5;
	private static final int VEHICLES_AND_VESSELS = 6;
	private static final int MORE_DRIVER_LICENSE_OPTIONS = 7;
	private static final int FIND_OFFICE = 8;
	private static final int DMV_NEWS = 9;
	private static final int AGENT = 10;
	
	private static final int DRIVING_TEST = 11;
	private static final int OFFICE_VISIT = 12;
	private static final int VEHICLE_TYPE = 13;
	private static final int NUM_REGISTRATION_ITEMS = 14;
	private static final int ENTER_VEHICLE_NUM = 15;
	private static final int RE_ENTER_VEHICLE_NUM = 16;
	private static final int ENTER_DOB = 17;
	private static final int CONFIRM_VEHICLE_NUM = 18;
//	private static final int = 19;
	private static final int ENTER_ZIP_APPT = 20;
	
	private static final int ENTER_PHONE_NUM_APPT = 21;
	private static final int MOTORCYCLE_TRAINING = 22;
	private static final int BERKELEY_ZIP = 23;
	private static final int EL_CERRITO_DIRECTIONS = 24;
	private static final int NEED_DIRECTIONS_EL_C = 25;
	private static final int KEEP_OFFICE = 26;
	private static final int AVAILABLE_APPT = 27;
	private static final int CHANGE_APPT_DATE = 28;
	private static final int CHANGE_APPT_TIME = 29;
	private static final int CHANGE_APPT_DT = 30;
	private static final int AVAILABLE_APPT_APRIL_TWENTY = 31;
	private static final int CONFIRMATION = 32;
	private static final int WHAT_TO_BRING = 33;
	private static final int CONFIRMATION_TWO = 34;
	private static final int NEED_DIRECTIONS_O_C = 35;
	private static final int OTHER_APPT_APRIL_TWENTY= 36;
	private static final int OAKLAND_CLAREMONT_DIRECTIONS= 37;
	private static final int SEARCH_ANOTHER_APPT= 38;
	private static final int OFFICE_HOURS = 39;
	private static final int CLOSURES = 40;
	private static final int SYSTEM_UNAVAILABLE = 41;
//	private static final int = 42;
//	private static final int = 43;
//	private static final int = 44;
	
	IVRNode[] nodes;
	IVRNode myNode;
	
	public IVR(){
		String text;
		Option[] options;
		
		// START
		text = "Thank you for calling the California Department of Motor Vehicles.";
		options = new Option[]{new Option("1", "For English, press 1", MAIN_MENU_ANNOUNCE),
								new Option("2", "Para Espanol, oprima 2", MAIN_MENU_ANNOUNCE)};
		IVRNode start = new IVRNode(text, options);

		// MAIN_MENU_ANNOUNCE
		text = "For DMV hours and closures, please listen to DMV News. Please note: " +
				"due to heavy volume processing, Califormia's new, security enhanced " +
				"driver license, please allow up to six weeks for delivery. For license " +
				"renewals by mail or internet, payments are processed and your record is updated within one week.";
		// options = new Option[]{}; // automatically takes to MAIN_MENU in IVR
		options = new Option[]{new Option("1", "Continue", MAIN_MENU)};
		IVRNode mainMenuAnnounce = new IVRNode(text, options);
		
		//MAIN_MENU
		text = "Main Menu. Please select an option:";
		options = new Option[]{new Option("1", "Vehicles and Vessels", VEHICLES_AND_VESSELS), // not mapped
								new Option("2", "Driver License", DRIVER_LICENSE), // slightly mapped
								new Option("3", "Order Forms", ORDER_FORMS), //not mapped
								new Option("4", "Make an Appointment", MAKE_APPT), // mapped enough
								new Option("5", "Find an Office", FIND_OFFICE), // slightly
								new Option("6", "Latest DMV News", DMV_NEWS), //mapped enough
							//	new Option("7", "Repeat this information", MAIN_MENU), //unnecessary for GUI
								new Option("0", "DMV Agent", AGENT), // invisible in the actual IVR
									};
		IVRNode mainMenu = new IVRNode(text, options);		
		
		// AGENT
		//*** if it's during business hours, it tries to connect to a customer service human. Else:
		text = "A DMV customer service agent is unable to assist you at this time. " +
				"However, you can visit us at www.dmv.ca.gov, or try your call again later.";
		options = nodes[MAIN_MENU].options;
		IVRNode agent = new IVRNode(text, options);
		
		//MAKE_APPT
		text = "Is this a behind-the-wheel driving test, or an office visit?";
		options = new Option[]{new Option("1", "Driving Test", DRIVING_TEST),
								new Option("2", "Office Visit", OFFICE_VISIT),
								new Option("9", "Main Menu", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible
		IVRNode makeAppt = new IVRNode(text, options);

		//DRIVER_LICENSE
		text = "Driver license and I.D. information: please select one of the following:";
		options = new Option[]{new Option("1", "Apply for original driver license or I.D. card", SYSTEM_UNAVAILABLE),
								new Option("2", "Renew a driver license or I.D. card", SYSTEM_UNAVAILABLE),
								new Option("3", "Replace a lost or stolen one", SYSTEM_UNAVAILABLE),
								new Option("4", "Check the status of a request", SYSTEM_UNAVAILABLE),
								new Option("5", "Name or address change", SYSTEM_UNAVAILABLE),
								new Option("6", "more options", MORE_DRIVER_LICENSE_OPTIONS),
								new Option("9", "Main Menu", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible
		IVRNode driverLicense = new IVRNode(text, options);

		//MORE_DRIVER_LICENSE_OPTIONS
		text = "Here are some more options:";
		options = new Option[]{new Option("1", "Accidents", SYSTEM_UNAVAILABLE),
								new Option("2", "D.U.I.", SYSTEM_UNAVAILABLE),
								new Option("3", "Child Support", SYSTEM_UNAVAILABLE),
								new Option("4", "New to California", SYSTEM_UNAVAILABLE),
								new Option("9", "Main Menu", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible
		IVRNode moreDriverLicenseOptions = new IVRNode(text, options);
		
		//DRIVING_TEST
		text = "Do you already have a driver license or permit number?";
		options = new Option[]{new Option("1", "Yes", VEHICLE_TYPE),
								new Option("2", "No", OFFICE_VISIT),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode drivingTest = new IVRNode(text, options);
		
		//OFFICE_VISIT
		text = "We offer a number of services related to driver license or vehicle registration. Which would you like?";
		options = new Option[]{new Option("1", "Driver License", NUM_REGISTRATION_ITEMS),
								new Option("2", "Vehicle Registration", NUM_REGISTRATION_ITEMS),
								new Option("3", "Both", NUM_REGISTRATION_ITEMS),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode officeVisit = new IVRNode(text, options);

		//VEHICLE_TYPE
		text = "Is the driving test for an automobile, or for a motorcycle?";
		options = new Option[]{new Option("1", "Automobile", ENTER_VEHICLE_NUM),
								new Option("2", "Motorcycle", MOTORCYCLE_TRAINING),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode vehicleType = new IVRNode(text, options);		

		//MOTORCYCLE_TRAINING
		text = "Have you completed the California motorcycle safety course?";
		options = new Option[]{new Option("1", "Yes", ENTER_VEHICLE_NUM),
								new Option("2", "No", ENTER_VEHICLE_NUM),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode motorcycleTraining = new IVRNode(text, options);	
		
		//ENTER_VEHICLE_NUM
		text = "Please say your driver license or permit number.";
		// editText here?
		options = new Option[]{new Option("D5033872", "EDIT_TEXT", CONFIRM_VEHICLE_NUM),
								new Option("2" /* invalid entry*/, "" /*the same edit text*/, RE_ENTER_VEHICLE_NUM)};
		IVRNode enterVehicleNum = new IVRNode(text, options);
		
		//RE_ENTER_VEHICLE_NUM
		// or just constrain it in the editText
		text = "Your driver license or permit number begins with a letter, and is followed by seven digits.";
		options = nodes[ENTER_VEHICLE_NUM].options;
		IVRNode reEnterVehicleNum = new IVRNode(text, options);		
		
		//CONFIRM_VEHICLE_NUM
		text = "What I heard was: D as in delta, five, zero, three, three, eight, seven, two. Is that correct?";
		options = new Option[]{new Option("1", "Yes", ENTER_DOB),
								new Option("2", "No", RE_ENTER_VEHICLE_NUM),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode confirmVehicleNum = new IVRNode(text, options);	
		
		//ENTER_DOB
		text = "Now tell me your date of birth, like this: January twenty-four nineteen-twenty-six";
		// maybe use a date picker type widget
		options = new Option[]{new Option("01241926", "DATE_PICKER", ENTER_PHONE_NUM_APPT)};
		IVRNode enterDOB = new IVRNode(text, options);			
		
		//ENTER_PHONE_NUM_APPT
		text = "What's your phone number, starting with the area code?";
		// enter phone number somehow, editText?
		options = new Option[]{new Option("5551234567", "EditText", ENTER_ZIP_APPT)};
		IVRNode enterPhoneNumAppt = new IVRNode(text, options);		
		
		//ENTER_NUM_REGISTRATIONS
		text = "California DMV will process a maximum of 3 items per visit. " +
				"How many vehicle registrations or services do you need during this appt?";
		options = new Option[]{new Option("1", "1", ENTER_ZIP_APPT),
								new Option("2", "2", ENTER_ZIP_APPT),
								new Option("3", "3", ENTER_ZIP_APPT),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode enterNumRegistrations = new IVRNode(text, options);			
		
		//ENTER_ZIP_APPT
		text = "Please tell me your ZIP code.";
		// editText, unless there's something better
		options = new Option[]{new Option("94720", "EDIT_TEXT", BERKELEY_ZIP)};
		//maybe toast that the ZIP wasn't found if it's invalid?
		IVRNode enterZipAppt = new IVRNode(text, options);		
		
		//BERKELEY_ZIP
		text = "Please select an office from one of the following locations:";
		options = new Option[]{new Option("1", "Oakland Claremont", NEED_DIRECTIONS_O_C), //haven't mapped
								new Option("2", "El Cerrito", NEED_DIRECTIONS_EL_C),
								new Option("3", "Oakland Colliseum", BERKELEY_ZIP), //haven't mapped
								new Option("4", "Walnut Creek", BERKELEY_ZIP), //haven't mapped
								new Option("5", "San Francisco", BERKELEY_ZIP), //haven't mapped
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible
		IVRNode berkeleyZip = new IVRNode(text, options);			
		
		//NEED_DIRECTIONS_EL_C
		text = "Would you like the address and directions for this office?";
		options = new Option[]{new Option("1", "Yes", EL_CERRITO_DIRECTIONS),
								new Option("2", "No", KEEP_OFFICE),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode needDirectionsElC = new IVRNode(text, options);
		
		//NEED_DIRECTIONS_O_C
		text = "Would you like the address and directions for this office?";
		options = new Option[]{new Option("1", "Yes", OAKLAND_CLAREMONT_DIRECTIONS),
								new Option("2", "No", KEEP_OFFICE),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode needDirectionsOC = new IVRNode(text, options);	
		
		//OAKLAND_CLAREMONT_DIRECTIONS
		text = "Oakland Claremont is located at 5300 Claremont Avenue, Oakland California, 94618, " +
				"one block South of highway 24, off the Claremont Avenue exit, at the corner of Claremont and Cavoor.";
		options = new Option[]{new Option("1", "Keep this office", AVAILABLE_APPT), // dont pick this one
								new Option("2", "Find a different office", ENTER_ZIP_APPT),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode oaklandClaremontDirections = new IVRNode(text, options);
		
		//EL_CERRITO_DIRECTIONS
		text = "El Cerrito is located at 6450 Manilla Avenue, El Cerrito County 94530, one block South" +
				"of San Pablo Avenue. From Highway 80 West, exit on Cutting Boulevard, turn right on " +
				"San Pablo Avenue then left on Manilla Avenue. From Highway 80 East, exit on Patrero on " +
				"the right, turn right on San Pablo Avenue, then left on Manilla Avenue.";
		options = new Option[]{new Option("1", "Keep this office", AVAILABLE_APPT),
								new Option("2", "Find a different office", ENTER_ZIP_APPT),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode elCerritoDirections = new IVRNode(text, options);	
		
		
		//KEEP_OFFICE
		text = "Would you like to...";
		options = new Option[]{new Option("1", "Keep this office", AVAILABLE_APPT),
								new Option("2", "Find a different office", BERKELEY_ZIP),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode keepOffice = new IVRNode(text, options);	
		
		//AVAILABLE_APPT
		text = "An appointment is available at El Cerrito for April 14 at 9:30 AM. Would you like to...";
		options = new Option[]{new Option("1", "Keep this appointment", AVAILABLE_APPT),
								new Option("2", "Search for another", BERKELEY_ZIP),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode availableAppt = new IVRNode(text, options);	
		
		//SEARCH_ANOTHER_APPT
		text = "Which would you like changed?";
		options = new Option[]{new Option("1", "Office location", BERKELEY_ZIP),
								new Option("2", "Date", CHANGE_APPT_DATE),
								new Option("3", "Time of day", CHANGE_APPT_TIME),
								new Option("4", "Both date and time of day", CHANGE_APPT_DATE),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode searchAnotherAppt = new IVRNode(text, options);	
		
		//CHANGE_APPT_DATE
		text = "Appointments may be scheduled for no more than 45 days in advance. What day would you like?"; //format: mmdd ;
		options = new Option[]{new Option("0420", "EDIT_TEXT", AVAILABLE_APPT_APRIL_TWENTY),
				new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
				new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode changeApptDate = new IVRNode(text, options);	
		
		//AVAILABLE_APPT_APRIL_TWENTY
		text = "An appointment is available at El Cerrito for April 20 at 10:30 AM. Would you like to...";
		options = new Option[]{new Option("1", "Keep this appointment", AVAILABLE_APPT),
								new Option("2", "Search for another", BERKELEY_ZIP),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode availableApptAprilTwenty = new IVRNode(text, options);	
		
		//CHANGE_APPT_TIME
		text = "Appointmets are available on the hour and half-hour between 8 AM and 4:30 PM. " + 
				"Please enter the time you would like an appointment.";
		options = new Option[]{new Option("0230", "EDIT_TEXT", OTHER_APPT_APRIL_TWENTY),
				new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
				new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode changeApptTime = new IVRNode(text, options);	
		
		//OTHER_APPT_APRIL_TWENTY
		text = "An appointment is available at El Cerrito for April 20 at 4:30 AM. Would you like to...";
		options = new Option[]{new Option("1", "Keep this appointment", AVAILABLE_APPT),
								new Option("2", "Search for another", BERKELEY_ZIP),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode otherApptAprilTwenty = new IVRNode(text, options);	
		
		//CONFIRMATION
		text = "Your appointment has been confirmed for April 20 at El Cerrito at 4:30 AM. The confirmation number is 55604140030.";
		options = new Option[]{new Option("1", "Get directions", AVAILABLE_APPT),
								new Option("2", "Continue", WHAT_TO_BRING),
								new Option("9", "MAIN_MENU", MAIN_MENU), // invisible						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode confirmation = new IVRNode(text, options);		
		
		
		//WHAT_TO_BRING
		text = "One the day of your appointment, please bring all applicable forms, " +
				"paperwork, fees, and all other items associated with your appointment, " +
				"including vehicle notices and bills of sale.";
		options = new Option[]{new Option("9", "MAIN_MENU", MAIN_MENU),						
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode whatToBring = new IVRNode(text, options);	
		
		//DMV_NEWS
		text = "Latest DMV news:";
		options = new Option[]{new Option("1", "DMV office hours", OFFICE_HOURS),						
								new Option("2", "DMV office closure information", DMV_NEWS), // don't pick this						
								new Option("9", "MAIN_MENU", MAIN_MENU), //invisble				
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode dmvNews = new IVRNode(text, options);	

		//OFFICE_HOURS
		text = "Offices are open Monday, Tuesday, Thursday, and Friday from 8 AM to 5 PM, " +
				"and Wednesdays from 9 AM to 5 PM.";						
		options = new Option[]{new Option("9", "MAIN_MENU", MAIN_MENU),			
								new Option("0", "DMV Agent", AGENT)}; // invisible};
		IVRNode officeHours = new IVRNode(text, options);	
			
		// CLOSURES
		
		//SYSTEM_UNAVAILABLE
		text = "Our system is not available at this time. If you are calling about a renewal, please " +
				"mail the appropriate forms before the expiration date. Thank you for calling the " +
				"California Department of Motor Vehicles. Goodbye.";						
		options = new Option[]{}; // it just hangs up, there are no options.
		IVRNode systemUnavailable = new IVRNode(text, options);	
		
		nodes = new IVRNode[40];
		nodes[START]					= start;
		nodes[MAIN_MENU] 				= mainMenu;
		nodes[MAKE_APPT] 				= makeAppt;
	//	nodes[DRIVER_LICENSE];
	//	nodes[ORDER_FORMS] = 4;
	//	nodes[START_NEWS] = ;
	//	nodes[VEHICLES_AND_VESSELS] = 6;
	//	nodes[LATEST_DMV_NEWS] = 7;
	//	nodes[FIND_OFFICE] = ;
		nodes[DMV_NEWS] 				= dmvNews;
		nodes[AGENT] 					= agent;
		nodes[DRIVING_TEST] 			= drivingTest;
		nodes[OFFICE_VISIT] 			= officeVisit;
		nodes[VEHICLE_TYPE] 			= vehicleType;
		nodes[NUM_REGISTRATION_ITEMS] 	= enterNumRegistrations;
		nodes[ENTER_VEHICLE_NUM] 		= enterVehicleNum;
		nodes[RE_ENTER_VEHICLE_NUM] 	= reEnterVehicleNum;
		nodes[ENTER_DOB] 				= enterDOB;
		nodes[CONFIRM_VEHICLE_NUM] 		= confirmVehicleNum;
		nodes[MAIN_MENU_ANNOUNCE] 		= mainMenuAnnounce;
		nodes[ENTER_ZIP_APPT] 			= enterZipAppt;
		nodes[ENTER_PHONE_NUM_APPT] 	= enterPhoneNumAppt;
		nodes[MOTORCYCLE_TRAINING] 		= motorcycleTraining;
		nodes[BERKELEY_ZIP] 			= berkeleyZip;
		nodes[NEED_DIRECTIONS_EL_C] 	= needDirectionsElC;
		nodes[KEEP_OFFICE] 				= keepOffice;
		nodes[AVAILABLE_APPT] 			= availableAppt;
		nodes[CHANGE_APPT_DATE]			= changeApptDate;
		nodes[CHANGE_APPT_TIME] 		= changeApptTime;
//		nodes[CHANGE_APPT_DT] = changeApptDT;
		nodes[AVAILABLE_APPT_APRIL_TWENTY] = availableApptAprilTwenty; 
		nodes[CONFIRMATION] 			= confirmation;
		nodes[WHAT_TO_BRING] 			= whatToBring;
//		nodes[CONFIRMATION_TWO] = confirmationTwo;
		nodes[NEED_DIRECTIONS_O_C] 		= needDirectionsOC;
		nodes[OTHER_APPT_APRIL_TWENTY] 	= otherApptAprilTwenty;
		nodes[OAKLAND_CLAREMONT_DIRECTIONS] = oaklandClaremontDirections;	
		nodes[SEARCH_ANOTHER_APPT] 		= searchAnotherAppt;	
		nodes[EL_CERRITO_DIRECTIONS] 	= elCerritoDirections;	
		nodes[OFFICE_HOURS] 			= officeHours;	
		nodes[SYSTEM_UNAVAILABLE] 		= systemUnavailable;	
		nodes[DRIVER_LICENSE] 			= driverLicense;	
		nodes[MORE_DRIVER_LICENSE_OPTIONS] 	= moreDriverLicenseOptions;	
		
		myNode = nodes[START];
	}
	
}

