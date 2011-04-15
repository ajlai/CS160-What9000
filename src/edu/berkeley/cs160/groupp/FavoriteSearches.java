package edu.berkeley.cs160.groupp;

import java.util.ArrayList;

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

public class FavoriteSearches extends IVRNodeBranchView {
	
		public void onCreate(Bundle savedInstanceState) {
			IVRApp.initializeFavorites();
			IVRApp.setCurrentBranch(IVRApp.getRootNode());
			super.onCreate(savedInstanceState);
		}
	}

