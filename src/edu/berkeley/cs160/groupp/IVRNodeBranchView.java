package edu.berkeley.cs160.groupp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

public class IVRNodeBranchView extends ListActivity {
	IVRNode currentBranch;
	public void onCreate(Bundle savedInstanceState) {
		//ViewFlipper vf = new ViewFlipper(this);
		//vf.addView(this.getListView());
		super.onCreate(savedInstanceState);
		currentBranch = IVRApp.getCurrentBranch();
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
			setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, currentBranch.getMenuString()));
		}
	}
}
