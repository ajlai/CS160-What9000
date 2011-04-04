package edu.berkeley.cs160.groupp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PhoneListView extends ListActivity {
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IVRNode root = ((IVRApp) getApplicationContext()).getRootNode();
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, root.getMenuString()));
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
	}
	static final String[] COUNTRIES = new String[] {"anthony", "bob", "carl", "david", "eddie" };
}
