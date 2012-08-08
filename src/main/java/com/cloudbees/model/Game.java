package com.cloudbees.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game {
	long id;
	String white;
	String black;
	String next;
	long move;
	String result;
	String description;
	
	public Game() {
		super();
		this.id = 0;
		this.white = null;
		this.black = null;
		this.description = null;
		this.next = "W";
		this.move = 1;
		this.result = null;
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
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public long getMove() {
		return move;
	}
	public void setMove(long move) {
		this.move = move;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getId() {
		return id;
	}
}
