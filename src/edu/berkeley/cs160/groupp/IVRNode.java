package edu.berkeley.cs160.groupp;

import java.net.URL;
import java.util.ArrayList;

public class IVRNode {
	private IVRNode parent;
	private ArrayList<IVRNode> children = new ArrayList<IVRNode> ();
	
	String title;
	private String type;
	String number;
	private URL src;

	public IVRNode(IVRNode p, String t, String n) {
		parent = p;
		title = t;
		type = "number";
		number = n;
	}
	
	public IVRNode(IVRNode p, String t, URL s) {
		parent = p;
		title = t;
		type = "link";
		src = s;
	}
	
	public IVRNode(IVRNode p, String t) {
		parent = p;
		title = t;
		type = "menu";
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
		IVRNode child = new IVRNode(this, t, n);
		this.children.add(child);
		return child;
	}
	
	public IVRNode addChildMenu(String t) {
		IVRNode child = new IVRNode(this, t);
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