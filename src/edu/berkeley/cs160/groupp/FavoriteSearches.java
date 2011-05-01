package edu.berkeley.cs160.groupp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import android.widget.AdapterView.OnItemLongClickListener;

public class FavoriteSearches extends IVRNodeBranchView {
	private static int numFav;
	private static LinkedHashMap<String, String> favorites = new LinkedHashMap();
	public static IVRNode fav = new IVRNode(null, "menu", "Favorite Searches", "This is a list of searches you have marked as your favorite. " 
			+ "Click any search to be transported directly to the end page." + "  Hold any search to delete from Favorites.");
	
		public void onCreate(Bundle savedInstanceState) {
			//IVRApp.initializeFavorites();
			
			load_favorites();
			
			IVRApp.setRootNode(fav);
			IVRApp.setCurrentBranch(IVRApp.getRootNode());
			
			super.onCreate(savedInstanceState);
			getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					remove_fav(currentBranch.getChild(position).getTitle());
					//TODO not everything is updating correctly, and there's a ghost entry?
					return true;
				}
			});
			
		}
		
		protected void onStop() {
			super.onStop();
			save_fav();
		}
		
		private void load_favorites() {
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			
			int numTemp = prefs.getInt("numFav", 0);
			for(int i=0; i<numTemp; i++) {
				favorites.put(prefs.getString("favName"+i, ""), prefs.getString("favNum"+i, ""));
			}
			numFav += numTemp;
			
			refresh_list();
		}
		
		private static void refresh_list() {
			fav.getChildren().clear();
			
			for(Iterator<Map.Entry<String, String>> i = favorites.entrySet().iterator(); i.hasNext() ; ) {
				Map.Entry<String, String> kvEntry = (Entry<String, String>) i.next();
				fav.addChildNumber(kvEntry.getKey(), kvEntry.getValue());
			}
		}
		
		private void save_fav() {
			int sav_index = 0;
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
	    	SharedPreferences.Editor editor = prefs.edit();
			
	    	editor.putInt("numFav", numFav);
	    	
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
			numFav++;
			refresh_list();
		}
		
		public static void remove_fav(String remName) {
			favorites.remove(remName);
			numFav--;
			refresh_list();
		}
		
		private static void clear_list() {
			numFav = 0;
		}
	}

