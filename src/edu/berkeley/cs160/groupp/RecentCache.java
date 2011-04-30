package edu.berkeley.cs160.groupp;

import java.util.LinkedHashMap;

public class RecentCache extends LinkedHashMap{
	
	public static final int ITEM_LIMIT = 5;
	
	public RecentCache() {
		super(ITEM_LIMIT+1, 1.0F, true);
	}
	
	protected boolean removeEldestEntry(Entry eldest) {
		return size() > ITEM_LIMIT;
	}
}