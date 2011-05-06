package edu.berkeley.cs160.groupp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class FavoriteSearches extends IVRNodeBranchView {
	private static LinkedHashMap<String, String> favorites = new LinkedHashMap();
	public static IVRNode fav = new IVRNode(null, "menu", "Favorite Searches", "This is a list of searches you have marked as your favorite. " 
			+ "Click any search to be transported directly to the end page." + "  Hold any search to delete from Favorites.");
	Context c;
		public void onCreate(Bundle savedInstanceState) {
			c = this;
			load_favorites();
			
			
			IVRApp.setRootNode(fav);
			IVRApp.setCurrentBranch(IVRApp.getRootNode());
			
			super.onCreate(savedInstanceState);
			getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					//TODO more complete testing for correctness
					remove_fav(currentBranch.getChild(position).getTitle());
					//Favorites list won't update when I longclick an item to delete
					//must navigate away and back to see changes
					//need to find the right thing to invalidate?
					//parent.refreshDrawableState();
					ArrayAdapter<String> tempAdapter = ((ArrayAdapter<String>)getListAdapter());
					String asdf = tempAdapter.getItem(position);
					tempAdapter.remove(asdf);
					tempAdapter.notifyDataSetChanged();
					return true;
					
				}
			});
			
		}
		
		protected void onStop() {
			super.onStop();
			save_fav();
		}
		
		//gets preferences from the preferences file
		private void load_favorites() {
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			
			//"numFav" represents how many favorites are stored in the file
			//favNameX and favNumX are the title and phone number of favorite #X
			int numTemp = prefs.getInt("numFav", 0);
			for(int i=0; i<numTemp; i++) {
				favorites.put(prefs.getString("favName"+i, ""), prefs.getString("favNum"+i, ""));
			}
			
			refresh_list();
		}
		
		//called after loading from file, or any add/remove of favorites
		private static void refresh_list() {
			fav.getChildren().clear();
			
			for(Iterator<Map.Entry<String, String>> i = favorites.entrySet().iterator(); i.hasNext() ; ) {
				Map.Entry<String, String> kvEntry = (Entry<String, String>) i.next();
				fav.addChildNumber(kvEntry.getKey(), kvEntry.getValue());
			}
		}
		
		//writes favorites to the preference file
		private void save_fav() {
			int sav_index = 0;
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
	    	SharedPreferences.Editor editor = prefs.edit();
			
	    	editor.putInt("numFav", favorites.size());
	    	
			for(Iterator<Map.Entry<String, String>> i = favorites.entrySet().iterator(); i.hasNext() ; ) {
				Map.Entry<String, String> kvEntry = (Entry<String, String>) i.next();
				editor.putString("favName"+sav_index, kvEntry.getKey());
				editor.putString("favNum"+sav_index, kvEntry.getValue());
				sav_index++;
			}
			editor.commit();
		}
		
		public static void add_fav(String newName, String newNumber) {
			favorites.put(newName, newNumber);
			refresh_list();
		}
		
		public static void remove_fav(String remName) {
			favorites.remove(remName);
			refresh_list();
		}
		
		//never used, debug stuff
		private void clear_list() {
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
	    	SharedPreferences.Editor editor = prefs.edit();
			
	    	editor.putInt("numFav", 0);
			editor.commit();
		}
	}

