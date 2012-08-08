package com.cloudbees.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Move {	
	long id;
	long move;
	String white;
	String black;
	long game;

	public Move() {
		super();
		this.id = 0;
		this.move = 0;
		this.white = "";
		this.black = "";
		this.game = 0;
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
	public long getId() {
		return id;
	}
	public long getGame() {
		return game;
	}
	public void setGame(long game) {
		this.game = game;
	}
	public long getMove() {
		return move;
	}
	public void setMove(long move) {
		this.move = move;
	}

	public boolean isLegal(String move) {
		// if move [1] == a-h
		// and move [2] == 1-8
		// and move [3] == - or x
		// and move [4] == a-h
		// and move [5] == 1-8
		return true;
	}
}
