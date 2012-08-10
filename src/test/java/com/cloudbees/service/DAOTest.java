package com.cloudbees.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.cloudbees.model.Game;
import com.cloudbees.model.Move;

import static org.junit.Assert.*;
import org.junit.Test;

public class DAOTest {
	Connection conn;
	long key;
	
	DAO dao = new DAO() {
		public void connect() {
			  String url = "jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com:3306/mp_test";
			  String driver = "com.mysql.jdbc.Driver";
			  String userName = "mp_test"; 
			  String password = "welcome1";
			  
			  try {
				  Class.forName(driver).newInstance();
				  conn = DriverManager.getConnection(url,userName,password);
				  super.setConnection(conn);
			  }
			  catch(Exception e) {
				  e.printStackTrace();
			  }
			  finally {			  
			  }
		}
	};
	
	@Test
	public void testNewGame() {
		try {	
			Game game = new Game();
			
			String testWhite = "Player White";
			String testBlack = "Player Black";
			String testDescription = "Match Description";
		    String testResult = null;
		    String testNext = "W";
		    long testMove = 1;  
			
		    dao.connect();
		    game.setWhite(testWhite);
		    game.setBlack(testBlack);
		    game.setDescription(testDescription);
		    
		    // Create a new game (key = game id)
			key = dao.newGame( game.getWhite(),
					           		game.getBlack(),
					           		game.getDescription() );
			assertFalse(key == 0);

			ResultSet rst = dao.getGame(key);
		    assertFalse(rst == null);

		    rst.first();
			assertEquals(key, rst.getInt(1));
			assertEquals(testWhite, rst.getString(2));	            	
			assertEquals(testBlack, rst.getString(3));
			assertEquals(testDescription, rst.getString(4));
			assertEquals(testResult, rst.getString(5));
			assertEquals(testNext, rst.getString(6));
			assertEquals(testMove, rst.getInt(7));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dao.disconnect();
		}
	}
	
	@Test
	public void testMoves() {
		try {
			Move move = new Move();

			// Create a new game: key is game id
			testNewGame();
			
			String testWhite = "e2-e4";
			String testBlack = "e7-e5";
			long testMove = 1;
			long rows = 0;
		    			
		    dao.connect();

		    // Add white move #1
		    move.setWhite(testWhite);
		    move.setBlack("");
		    move.setMove(testMove);
		    move.setGame(key);
		    
		    rows = dao.newWhiteMove( move.getMove(), 
					  		  		 move.getWhite(), 
					  		  		 move.getGame());
		    assertFalse(rows == 0);
		    
		    // Add black move #1
		    move.setWhite("");
		    move.setBlack(testBlack);
		    
		    rows = dao.newBlackMove( move.getMove(), 
			  		  		  		 move.getBlack(), 
			  		  		  		 move.getGame());
		    assertFalse(rows == 0);
		    
		    // Check the row for move #1
		    ResultSet rst = dao.getMoves(key);		   
		    assertFalse(rst == null);

		    rst.first();
			assertEquals(testMove, rst.getInt(2));
			assertEquals(testWhite, rst.getString(3));	            	
			assertEquals(testBlack, rst.getString(4));	
			assertEquals(key, rst.getInt(5));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			dao.disconnect();
		}
	}	
}
