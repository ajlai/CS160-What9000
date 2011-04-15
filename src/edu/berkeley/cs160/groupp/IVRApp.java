package edu.berkeley.cs160.groupp;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Application;

public class IVRApp extends Application {
	private static IVRNode root;
	private static IVRNode current;
	public void onCreate() {
		initialize();
	}
	
	public static void initialize() {
		IVRNode r = new IVRNode(null, "menu", "Main menu", "relax");
		r.addChildNumber("Some number", "1234567890");
		r.addChildNumber("some other number", "9876543210");
		r.addChildURL("some url", "http://www.google.com");
		r.addChildMenu("some unfinished menu", "some unfinished menu descr");
		setRootNode(r);
	}
	
	public static IVRNode getRootNode() {
		return root;
	}
	
	public static void setRootNode(IVRNode r) {
		root = r;
	}
	
	public static IVRNode getCurrentBranch() {
		return current;
	}
	
	public static void setCurrentBranch(IVRNode c) {
		current = c;
	}

	public static void initializeDMV() {
		IVRNode dmv = new IVRNode(null, "menu", "California DMV", "Please select a language.");
		IVRNode announcement = dmv.addChildMenu("English", "For DMV hours and closures, please listen to DMV News. Please note: " +
				"due to heavy volume processing, for Califormia's new security enhanced " +
				"driver licenses, please allow up to six weeks for delivery. For license " +
				"renewals by mail or internet, payments are processed and your record is updated within one week.");
		IVRNode spanishAnnouncement = dmv.addChildMenu("Espanol", "No habla Espanol");
		IVRNode mainMenu = announcement.addChildMenu("Continue", "Main menu");
		IVRNode vehiclesVessels = mainMenu.addChildMenu("Vehicles and Vessels", "unimplemented");
		IVRNode driverLicenseOption = mainMenu.addChildMenu("Driver License", "Driver license and ID information: please select one of the following:");
		IVRNode orderForms = mainMenu.addChildMenu("Order Forms", "unimplemented");
		IVRNode makeAppt = mainMenu.addChildMenu("Make an Appointment", "Is this a behind-the-wheel driving test, or an office visit?");
		IVRNode findOffice = mainMenu.addChildMenu("Find an Office", "unimplemented");
		IVRNode dmvNews = mainMenu.addChildMenu("Latest DMV News", "Latest DMV news");

		//driver license branch
		IVRNode driverLicenseMenu1 = driverLicenseOption.addChildMenu("Apply for original driver license or ID card", "System unavailable.");
		IVRNode driverLicenseMenu2 = driverLicenseOption.addChildMenu("Renew a driver license or ID card", "System unavailable.");
		IVRNode driverLicenseMenu4 = driverLicenseOption.addChildMenu("Replace a lost or stolen one", "System unavailable.");
		IVRNode driverLicenseMenu3 = driverLicenseOption.addChildMenu("Check the status of a request", "System unavailable.");
		IVRNode driverLicenseMenu5 = driverLicenseOption.addChildMenu("Name or address change", "System unavailable.");
		IVRNode driverLicenseMenu6 = driverLicenseOption.addChildMenu("More options...", "System unavailable.");		
		IVRNode driverLicenseMenu61 = driverLicenseMenu6.addChildMenu("Accidents", "System unavailable.");
		IVRNode driverLicenseMenu62 = driverLicenseMenu6.addChildMenu("DUI", "System unavailable.");
		IVRNode driverLicenseMenu63 = driverLicenseMenu6.addChildMenu("Child Support", "System unavailable.");
		IVRNode driverLicenseMenu64 = driverLicenseMenu6.addChildMenu("New to California", "System unavailable.");

		//Make Appt branch
		IVRNode drivingTest = makeAppt.addChildMenu("Driving Test", "Do you already have a driver license or permit number?");
		IVRNode officeVisit = makeAppt.addChildMenu("Office Visit", "We offer a number of services related to driver license " +
				"or vehicle registration. Which would you like?");
		
		IVRNode vehicleType = drivingTest.addChildMenu("Yes", "Is the driving test for an automobile or a motorcycle?");
		IVRNode alsoofficeVisit = drivingTest.addChildMenu("No", "unimplemented");
		IVRNode enterVehicleNum = vehicleType.addChildNumber("Automobile", "1-800-777-0133");
		IVRNode motorcycleTraining = vehicleType.addChildMenu("Motorcycle", "Have you completed the California motorcycle safety course?");
		IVRNode enterVehicleNum2 = motorcycleTraining.addChildNumber("Yes", "1-800-777-0133");
		IVRNode enterVehicleNum3 = motorcycleTraining.addChildNumber("No", "1-800-777-0133");

		IVRNode enterNumRegistrations1 = makeAppt.addChildMenu("Driver License", "California DMV will process a maximum of 3 items per visit. " +
				"How many vehicle registrations or services do you need during this appointment?");
		IVRNode enterNumRegistrations2 = makeAppt.addChildMenu("Vehicle Registration", "California DMV will process a maximum of 3 items per visit. " +
				"How many vehicle registrations or services do you need during this appointment?");
		IVRNode enterNumRegistrations3 = makeAppt.addChildMenu("Both", "California DMV will process a maximum of 3 items per visit. " +
				"How many vehicle registrations or services do you need during this appointment?");
		
		IVRNode enterZip1 = enterNumRegistrations1.addChildNumber("1", "1-800-777-0133");
		IVRNode enterZip2 = enterNumRegistrations1.addChildNumber("2", "1-800-777-0133");
		IVRNode enterZip3 = enterNumRegistrations1.addChildNumber("3", "1-800-777-0133");

		//DMV news
		IVRNode officeHours = makeAppt.addChildMenu("DMV office hours", "Offices are open Monday, Tuesday, Thursday, and Friday from 8 AM to 5 PM, " +
				"and Wednesdays from 9 AM to 5 PM.");
		IVRNode closures = makeAppt.addChildMenu("DMV office closure information", "system unavailable");
		
		
		
		setRootNode(dmv);
	}
	
	public static void initializeFavorites() {
		IVRNode fav = new IVRNode(null, "menu", "Favorites", "favorites description here");
		fav.addChildNumber("ATT Customer Service: More Options", "180033105001");
		fav.addChildNumber("Oakland DMV: Directions", "180077701331");
		fav.addChildNumber("Oakland DMV: Hours", "180077701331");
		setRootNode(fav);
	}
	
	
}
