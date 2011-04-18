package edu.berkeley.cs160.groupp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
		if (currentBranch.getParent() == null) {
			super.onBackPressed();
		} else {
			currentBranch = currentBranch.getParent();
			final TextView description = (TextView) findViewById(R.id.description);
			description.setText(currentBranch.getDescription());
			this.setTitle(currentBranch.getTitle());
			setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, currentBranch.getMenuString()));
		}
	}
}
