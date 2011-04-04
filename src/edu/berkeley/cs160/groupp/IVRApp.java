package edu.berkeley.cs160.groupp;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Application;

public class IVRApp extends Application {
	private static IVRNode root;

	public void onCreate() {
		try {
			initialize();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() throws MalformedURLException {
		IVRNode r = new IVRNode(null, "Main Menu");
		r.addChildNumber("Some number", "1234567890");
		r.addChildNumber("some other number", "9876543210");
		r.addChildURL("some url", new URL("http://google.com"));
		r.addChildMenu("some unfinished menu");
		setRootNode(r);
	}
	
	public static IVRNode getRootNode() {
		return root;
	}
	
	public static void setRootNode(IVRNode r) {
		root = r;
	}

}
