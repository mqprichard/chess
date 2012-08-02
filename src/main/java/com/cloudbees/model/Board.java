package com.cloudbees.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Board {
	long id;
	String row1;
	String row2;
	String row3;
	String row4;
	String row5;
	String row6;
	String row7;
	String row8;
	long game;	

    public Board() {
		super();
		this.row1 = "";
		this.row2 = "";
		this.row3 = "";
		this.row4 = "";
		this.row5 = "";
		this.row6 = "";
		this.row7 = "";
		this.row8 = "";
		this.game = 0;
	}
        
	public long getGame() {
		return game;
	}
	public void setGame(long game) {
		this.game = game;
	}
	public String getRow1() {
		return row1;
	}
	public void setRow1(String row1) {
		this.row1 = row1;
	}
	public String getRow2() {
		return row2;
	}
	public void setRow2(String row2) {
		this.row2 = row2;
	}
	public String getRow3() {
		return row3;
	}
	public void setRow3(String row3) {
		this.row3 = row3;
	}
	public String getRow4() {
		return row4;
	}
	public void setRow4(String row4) {
		this.row4 = row4;
	}
	public String getRow5() {
		return row5;
	}
	public void setRow5(String row5) {
		this.row5 = row5;
	}
	public String getRow6() {
		return row6;
	}
	public void setRow6(String row6) {
		this.row6 = row6;
	}
	public String getRow7() {
		return row7;
	}
	public void setRow7(String row7) {
		this.row7 = row7;
	}
	public String getRow8() {
		return row8;
	}
	public void setRow8(String row8) {
		this.row8 = row8;
	}
	public long getId() {
		return id;
	}
}
