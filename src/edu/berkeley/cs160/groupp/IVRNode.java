package edu.berkeley.cs160.groupp;

import java.net.URL;
import java.util.ArrayList;

public class IVRNode {
	private IVRNode parent;
	private ArrayList<IVRNode> children = new ArrayList<IVRNode> ();
	
	private String title;
	private String description;
	private String number;
	private String src;
	private String type;

	public IVRNode(IVRNode p, String typ, String t, String content) {
		parent = p;
		title = t;
		type = typ;
		if (typ.equals("number")) number = content;
		if (typ.equals("menu")) description = content;
		if (typ.equals("link")) src = content;
	}
	
	public String getTitle() {
		return this.title;
	}

	public ArrayList<IVRNode> getChildren() {
		return this.children;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getSrc() {
		return this.src;
	}
	
	public IVRNode addChildURL(String t, String s) {
		IVRNode child = new IVRNode(this, "link", t, s);
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
	
	public IVRNode getChild(int i){
		return this.children.get(i);
	}
}