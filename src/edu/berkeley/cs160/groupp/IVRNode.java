package edu.berkeley.cs160.groupp;

import java.net.URL;
import java.util.ArrayList;

public class IVRNode{
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
		if (typ.equals("text")) number = content;
		if (typ.equals("menu")) description = content;
		if (typ.equals("link")) src = content;
	}
	
	public IVRNode(IVRNode p, String typ, String t, String d, String content) {
		parent = p;
		title = t;
		type = typ;
		number = content;
		description = d;
	}
	
	public String getTitle() {
		return this.title;
	}

	public ArrayList<IVRNode> getChildren() {
		return this.children;
	}
	
	public IVRNode getParent() {
		return this.parent;
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
	
	public IVRNode addChildTextInput(String t, String d, String n) {
		IVRNode child = new IVRNode(this, "text", t, d, n);
		this.children.add(child);
		return child;
	}
	
	
	public ArrayList<String> getMenuString() {
		//String [] rtn = new String [this.children.size()];
		ArrayList<String> rtn = new ArrayList<String>();
		
		for (int i = 0; i < this.children.size(); i++) {
			rtn.add(this.children.get(i).title);
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