package edu.berkeley.cs160.groupp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

public class IVRNodeBranchView extends ListActivity {
	IVRNode currentBranch;
	Activity a;
	public void onCreate(Bundle savedInstanceState) {
		//ViewFlipper vf = new ViewFlipper(this);
		//vf.addView(this.getListView());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listing);
		a = this;
		currentBranch = IVRApp.getCurrentBranch();
		final TextView description = (TextView) findViewById(R.id.description);
		description.setText(currentBranch.getDescription());
		this.setTitle(currentBranch.getTitle());
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, currentBranch.getMenuString()));
		
		getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				IVRNode childNode = currentBranch.getChild(position);
				String type = childNode.getType();
				if (type.equals("menu")) {
					//Intent childIntent = new Intent(IVRNodeBranchView.this, IVRNodeBranchView.class);
					IVRApp.setCurrentBranch(childNode);
					currentBranch=childNode;
					//startActivityForResult(childIntent, 0);
					description.setText(currentBranch.getDescription());
					a.setTitle(currentBranch.getTitle());
					setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, currentBranch.getMenuString()));
				} else {
					Intent childIntent = new Intent(getApplicationContext(), IVRNodeLeafView.class);
					childIntent.putExtra("title", childNode.getTitle());
					childIntent.putExtra("type", childNode.getType());
					if (type.equals("link")) {
						childIntent.putExtra("src", childNode.getSrc());
					}
					if (type.equals("number")) {
						childIntent.putExtra("number", childNode.getNumber());
					}
					if (type.equals("text")) {
						childIntent.putExtra("number", childNode.getNumber());
						childIntent.putExtra("text", childNode.getDescription());
					}
					
					startActivity(childIntent);
				}
			}
			
		});
	}
	
	public void onBackPressed() {
		goBack();
	}
		
	public void goBack() {
		if (IVRApp.canGoBack()) {
			currentBranch = IVRApp.goBack();
			setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, currentBranch.getMenuString()));
		} else {
			IVRApp.clearHistory();
			super.onBackPressed();
		}
	}
	
	public void goForward() {
		if (IVRApp.canGoForward()) {
			currentBranch = IVRApp.goForward();
			setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, currentBranch.getMenuString()));
		}
	}
	
	public void goHome() {
		IVRApp.clearHistory();
		Intent i = new Intent(getApplicationContext(), IntroPage.class);
		startActivityForResult(i, 1);
	}
	
	static final int backBtnId = Menu.FIRST;
	static final int forwardBtnId = backBtnId + 1;
	static final int homeBtnId = forwardBtnId + 1;
	static final int groupId = 1;
	public boolean onCreateOptionsMenu(Menu menu){
		int groupId = 1;
		menu.add(groupId, backBtnId, backBtnId, "Back");
		menu.add(groupId, forwardBtnId, forwardBtnId, "Forward");
		menu.add(groupId, homeBtnId, homeBtnId, "Home");
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem backOption = menu.findItem(backBtnId);
		backOption.setEnabled(IVRApp.canGoBack());
		MenuItem forwardOption = menu.findItem(forwardBtnId);
		forwardOption.setEnabled(IVRApp.canGoForward());
		return super.onPrepareOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case backBtnId:
			goBack();
			return true;
		case forwardBtnId:
			goForward();
			return true;
		case homeBtnId:
			goHome();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
