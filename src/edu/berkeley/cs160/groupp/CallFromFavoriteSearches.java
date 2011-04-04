package edu.berkeley.cs160.groupp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CallFromFavoriteSearches extends Activity {
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.callfromfavoritesearches);
	        
	        Intent launchingIntent = getIntent();
	        final String content = launchingIntent.getData().toString();
	        final String number = content.substring(0, 16);
	        final String info = content.substring(16) + "\n\n";
	        
	        
	        if (content.startsWith("h")) {
		        TextView screenInfo = (TextView) findViewById(R.id.callinginfo);
		        screenInfo.setText("Powell Apple Store: Address (Web)");
		        
		        TextView calling = (TextView) findViewById(R.id.calling);
		        calling.setText(" ");
		        
		        Button button = (Button) findViewById(R.id.callingbutton);
		        button.setText("Click here to load the website.");
		        button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
				    		WebView webviewer = (WebView) findViewById(R.id.webviewer);
				    		webviewer.loadUrl(content);
					}});
	        } else {
		        TextView screenInfo = (TextView) findViewById(R.id.callinginfo);
		        screenInfo.setText(info);
	        Button button = (Button) findViewById(R.id.callingbutton);
	        button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					try {
			    		Intent callIntent = new Intent(Intent.ACTION_CALL);
			    		callIntent.setData(Uri.parse(number));
			    		startActivity(callIntent);
			    	} catch (ActivityNotFoundException e) {
			    		e.printStackTrace();
			    	}
				}});
	        }
	    }
	}

