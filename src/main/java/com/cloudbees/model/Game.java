package com.cloudbees.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game {
	long id;
	String white;
	String black;
	String description;
	
	public Game() {
		super();
		this.id = 0;
		this.white = null;
		this.black = null;
		this.description = null;
	}
	public String getWhite() {
		return white;
	}
	public void setWhite(String white) {
		this.white = white;
	}
	public String getBlack() {
		return black;
	}
	public void setBlack(String black) {
		this.black = black;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
}
