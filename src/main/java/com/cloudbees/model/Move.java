package com.cloudbees.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOVE")
public class Move {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)	
	long id;
	String white;
	String black;
	long game_id;

	public Move() {
		super();
	}
	
    public Move(String white, String black, long game_id) {
		super();
		this.white = white;
		this.black = black;
		this.game_id = game_id;
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
}
