package edu.berkeley.cs160.groupp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CompanyListing extends Activity {
	
	LinearLayout menuBar, listing;
	Button back, home, help;
	CompanyInfo company;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companylisting);
        
        menuBar = (LinearLayout) findViewById(R.id.sidebar);
        listing = (LinearLayout) findViewById(R.id.listings);
        
        back = (Button) findViewById(R.id.back);
        home = (Button) findViewById(R.id.home);
        help = (Button) findViewById(R.id.help);
        
        createCompany();
        
        TextView title = new TextView(this);
        title.setText(company.name);
        listing.addView(title);
        
        for (int i = 0; i < company.options.size(); i++) {
        	Button option = new Button(this);
        	option.setText(company.options.get(i));
        	listing.addView(option);
        }
    }
    
    public void createCompany() {
        company = new CompanyInfo();
        company.name = "Dell Computers";
        company.options.add(new String("Account information"));
        company.options.add(new String("Speak to an operator"));
        company.options.add(new String("Obtain product information"));
    }

}

class CompanyInfo {
	String name;
	ArrayList<String> options;
}