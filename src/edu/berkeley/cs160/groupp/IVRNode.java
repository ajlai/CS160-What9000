package edu.berkeley.cs160.groupp;

import java.net.URL;
import java.util.ArrayList;

public class IVRNode {
	private IVRNode parent;
	private ArrayList<IVRNode> children = new ArrayList<IVRNode> ();
	
	String title;
	private String description;
	String number;
	private URL src;

	public IVRNode(IVRNode p, String type, String t, String content) {
		parent = p;
		title = t;
		if (type == "number") number = content;
		if (type == "menu") description = content;
	}
	public IVRNode(IVRNode p, String t, URL s) {
		parent = p;
		title = t;
		src = s;
	}

	public ArrayList<IVRNode> getChildren() {
		return this.children;
	}
	
	public IVRNode addChildURL(String t, URL s) {
		IVRNode child = new IVRNode(this, t, s);
		this.children.add(child);
		return child;
	}
	
	public IVRNode addChildNumber(String t, String n) {
		IVRNode child = new IVRNode(this, "number", t, n);
		this.children.add(child);
		return child;
	}
	
	public IVRNode addChildMenu(String t, String d) {
		IVRNode child = new IVRNode(this, "menu", t, d);
		this.children.add(child);
		return child;
	}
	
	
	public String[] getMenuString() {
		String [] rtn = new String [this.children.size()];
		
		for (int i = 0; i < this.children.size(); i++) {
			rtn[i] = this.children.get(i).title;
		}
		return rtn;
	}

	public String getMenuDescription() {
		return this.description;
	}
	
	public IVRNode getChildByTitle(String t) {
		IVRNode child;
		for (int i = 0; i < this.children.size(); i++) {
			child = this.children.get(i);
			if (child.title == t) {
				return child;
			}
		}
		return null;
	}
}