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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;

public class FavoriteSearches extends ListActivity {
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	 
	        setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
	                R.array.tut_titles, R.layout.list_item));
	        setContentView(R.layout.savedsearches);
	        
	        getListView().setOnItemClickListener(new OnItemClickListener() {

	        	final String[] links = getResources().getStringArray(R.array.tut_links);
				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int position, long id) {
					String content = links[position];
				    Intent showContent = new Intent(getApplicationContext(),
				            CallFromFavoriteSearches.class);
				    showContent.setData(Uri.parse(content));
				    startActivity(showContent);
					
				}
	        });
	    }
	}

