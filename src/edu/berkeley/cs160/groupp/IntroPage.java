package edu.berkeley.cs160.groupp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntroPage extends Activity {
	Button searchName, searchNum, recentSearches, tutorial;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tutorial = (Button) findViewById(R.id.tutorialButton);
        recentSearches = (Button) findViewById(R.id.recentButton);
        searchName = (Button) findViewById(R.id.searchNameButton);
        searchNum = (Button) findViewById(R.id.searchNumberButton);
        
        //TODO: move all these on click listeners somewhere more elegant
        tutorial.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Bring up Dialog containing necessary overview + help for application.
			}});
        
        recentSearches.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Bring up Recent Searches page (STATIC for this prototype).
			}});
        
        searchName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Bring up the search by name page by calling new activity intent
			}});
        
        searchNum.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Bring up the search by numbers page by calling new activity intent
			}});
    }
    
    /**
     * This doesn't actually belong here right now but I wanted to
     * note the code for later. JS
     * 
     * When called, starts a phone call with the provided argument
     * as the phone number to call.
     * 
     * @param A string representing a telephone number, in the format
     * 		  "tel:[number]", e.g. "tel:1234567890".
     */
    private void call(String number) {
    	try {
    		Intent callIntent = new Intent(Intent.ACTION_CALL);
    		callIntent.setData(Uri.parse(number));
    		startActivity(callIntent);
    	} catch (ActivityNotFoundException e) {
    		e.printStackTrace();
    	}
    }

}
