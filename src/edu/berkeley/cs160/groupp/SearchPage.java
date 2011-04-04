package edu.berkeley.cs160.groupp;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SearchPage extends ListActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context c = this;
        setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.demo_search_links, R.layout.list_item));
        setContentView(R.layout.search);
        getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				//assume that its gonna be dmv
				IVRApp.initializeDMV();
				IVRApp.setCurrentBranch(IVRApp.getRootNode());
				Intent i = new Intent(SearchPage.this, IVRNodeBranchView.class);
				startActivity(i);
			}
        });
        
        
    }

}
