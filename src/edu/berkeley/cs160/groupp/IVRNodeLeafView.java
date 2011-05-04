	package edu.berkeley.cs160.groupp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IVRNodeLeafView extends Activity {
	String title = "NONE";
	String number = "0000";
	String src = "http://www.google.com";
	String type = "number";
	String text = "null";
	Context c;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nodeleafview);
		
		c = this;
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			title = extras.getString("title");
			number = extras.getString("number");
			src = extras.getString("src");
			type = extras.getString("type");
			text = extras.getString("text");
		}
		

		
		if (type.equals("text")) {
			TextView callingInfo = (TextView) findViewById(R.id.calling);
			callingInfo.setText("Please enter in the required info, then press the button to call. Your call will not be completed properly if you do not enter the info.");
			callingInfo.setTextSize(15);
			
			final EditText enterInfo = (EditText) findViewById(R.id.enterinfoplz);
			enterInfo.setHint(text);
			enterInfo.setText("");
			
			Button connect = (Button) findViewById(R.id.callingbutton);
	        connect.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View arg0) {
					try {
						String editInfoText = enterInfo.getText().toString();
						editInfoText = editInfoText.replaceAll("[^0-9,p]", "");
						Intent callIntent;
						if (editInfoText.startsWith("Please")) {//TODO this isn't possible now with the replaceAll line
							callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number + "4089648650"));
						} else {
							if (editInfoText == "4089648650") 
								callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number + editInfoText + ",,1"));
							else callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number + editInfoText));
						}
						RecentSearches.update_recent(title, number+editInfoText);
						
						int pauseCount = 0;
						for (int i = 0; i < number.length(); i++) {
							if (number.charAt(i) == ',') {
								pauseCount += 2;
							}
						}
			    		Toast.makeText(c, "Please wait while Beat The System navigates menus for you", pauseCount).show();

			   			startActivity(callIntent);
			    		Toast.makeText(c, "Navigating Menus...", Toast.LENGTH_LONG).show();

						
			    	} catch (ActivityNotFoundException e) {
			    		e.printStackTrace();
			    	}
				}}); 
	        
	        //For adding favorites
	        connect.setOnLongClickListener(new OnLongClickListener() {
	        	public boolean onLongClick(View v){
	        		String editInfoText = enterInfo.getText().toString();
					editInfoText = editInfoText.replaceAll("[^0-9,p]", "");
	        		FavoriteSearches.add_fav(title, number+editInfoText);
	        		Toast.makeText(c, "Call added to Favorites!", Toast.LENGTH_SHORT).show();
	        		return true;
	        	}
	        });
		}
		
		else if (type.equals("number")) {
			
			TextView callingInfo = (TextView) findViewById(R.id.callinginfo);
			callingInfo.setText(title);
			
			TextView status = (TextView) findViewById(R.id.calling);
			status.setVisibility(4);
			
			EditText enterInfo = (EditText) findViewById(R.id.enterinfoplz);
			enterInfo.setVisibility(4);
			
			Button connect = (Button) findViewById(R.id.callingbutton);
	        connect.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View arg0) {
					RecentSearches.update_recent(title, number);
					try {
			    		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
			    		
			    		int pauseCount = 0;
			    		for (int i = 0; i < number.length(); i++) {
			    			if (number.charAt(i) == ',') {
			    				pauseCount += 2;
			    			}
			    		}
			    		startActivity(callIntent);
			    		Toast.makeText(c, "Please wait while Beat The System navigates menus for you", pauseCount).show();
			    	} catch (ActivityNotFoundException e) {
			    		e.printStackTrace();
			    	}
				}});
	        
	        connect.setOnLongClickListener(new OnLongClickListener() {
	        	public boolean onLongClick(View v){
	        		FavoriteSearches.add_fav(title, number);
	        		Toast.makeText(c, "Call added to Favorites!", Toast.LENGTH_SHORT).show();
	        		return true;
	        	}
	        });
		} else if (type.equals("link")) {
			TextView linkInfo = (TextView) findViewById(R.id.callinginfo);
			linkInfo.setText(title);
			
			TextView status = (TextView) findViewById(R.id.calling);
			status.setVisibility(4);
			
			EditText enterInfo = (EditText) findViewById(R.id.enterinfoplz);
			enterInfo.setVisibility(4);
			
			Button connect = (Button) findViewById(R.id.callingbutton);
			connect.setText("Connect to web page");
			connect.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
		    		WebView webviewer = (WebView) findViewById(R.id.webviewer);
		    		webviewer.loadUrl(src);
				}
				
			});
			
			//TODO add longclick listener for link favorites?
		}
			
	}
}
