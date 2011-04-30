package edu.berkeley.cs160.groupp;

import java.util.Iterator;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;

public class RecentSearches extends IVRNodeBranchView {
	//this is a LRU cache that holds a limited number of past calls	
	public static RecentCache recents = new RecentCache();
	static IVRNode recentNode = new IVRNode(null, "menu", "Recent Searches", "This is a list of recent searches you have done.");
    @Override
    public void onCreate(Bundle savedInstanceState) {
		//IVRApp.initializeRecents();
    	//preferences must be accessed from inside an activity

		load_list();
		IVRApp.setRootNode(recentNode);
		IVRApp.setCurrentBranch(IVRApp.getRootNode());
		super.onCreate(savedInstanceState);
	}

    protected void onStop() {
    	super.onStop();
    	save_list();
    }
    
    //load recent data from SharedPreferences into the recents cache
    //called during onCreate
    //because update_recent may be called before onCreate, recents may already be populated, and existing entries should be preserved
    //TODO does not seem to consistently order in most-recent fashion, and I'm not completely certain if it really is strictly LRU
    private void load_list() {
    	recentNode.getChildren().clear();
    	SharedPreferences prefs = getPreferences(MODE_PRIVATE);

    	for(int i=0; i<(RecentCache.ITEM_LIMIT-recents.size()); i++){
    		if (prefs.getString("recentName"+i, "") != ""){
    			recents.put(prefs.getString("recentName"+i, ""), prefs.getString("recentNumber"+i, ""));
    		}
    	}
    	
    	RecentCache recentsCopy = (RecentCache) recents.clone();
    	Iterator<String> recentIter = recentsCopy.keySet().iterator();
    	while(recentIter.hasNext()) {
    		String itemName = recentIter.next();
    		recentNode.addChildNumber(itemName, (String)recents.get(itemName));
    	}
    }
    
    //save recent data into the SharedPreferences file
    //called during onStop
    private void save_list() {
    	SharedPreferences prefs = getPreferences(MODE_PRIVATE);
    	SharedPreferences.Editor editor = prefs.edit();
    	RecentCache recentsCopy = (RecentCache) recents.clone();
    	Iterator<String> recentIter = recentsCopy.keySet().iterator();
    	int pref_index = 0;
    	while(recentIter.hasNext()) {
    		String name = recentIter.next();
    		editor.putString("recentName"+pref_index, name);
    		editor.putString("recentNumber"+pref_index, (String)recents.get(name));
    		pref_index++;
    	}
    	editor.commit();
    }
    
    //adds entry to the recent cache
    //called whenever a phone call is made
	public static void update_recent(String name, String number) {
    	recents.put(name, number);
    	recentNode.getChildren().clear();
    	RecentCache recentsCopy = (RecentCache) recents.clone();
    	Iterator<String> recentIter = recentsCopy.keySet().iterator();
    	while(recentIter.hasNext()) {
    		String itemName = recentIter.next();
    		recentNode.addChildNumber(itemName, (String)recents.get(itemName));
    	}
    }
}
