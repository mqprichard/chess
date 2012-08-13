package com.cloudbees.service;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cloudbees.model.Move;

public class MoveServletTest {
	
	Move testMove = null;
	long testGameId = 0;

	// DAO sub-class for JUnit tests
	private Connection conn;
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

	// GameServlet sub-class for JUnit testing
	class TestMoveServlet extends MoveServlet {

		TestMoveServlet() {
			super.setDAO(dao);
		}			
	}

	@Test
	// Tests MoveServlet.newMove() and MoveServlet.getMoves()
	public void testMoveServlet() {
		
		// First run testNewGame() to set game id
		GameServletTest gameServlet = new GameServletTest();
		gameServlet.testNewGame();
		long testId = gameServlet.getId();
		assertFalse (testId == 0);
		
		// Make white/black moves
		TestMoveServlet moveServlet = new TestMoveServlet();
		Move move = new Move();
		Response response;
		String testMove = "1";
		String testWhite = "e2-e4";
		String testBlack = "e7-e5";
		
		// Move 1 (white)
		move.setGame( Long.toString(testId) );
		move.setMove( testMove );
		move.setWhite( testWhite );
		move.setBlack( "" );
		response = moveServlet.newMove( move );
		assertFalse( response == null );
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
		
		// Move 1 (black)
		move.setWhite( "" );
		move.setBlack( testBlack );
		response = moveServlet.newMove( move );
		assertFalse( response == null );
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

		response = moveServlet.getMoves( testId );
		
		// Iterate over JSON elements in response
		String reply = "{reply:" + response.getEntity().toString() + "}";
		JSONObject jObject;
		try {
			jObject = new JSONObject(reply);

			// Top-level JSON response object
			JSONObject json = jObject.getJSONObject("reply");
			assertFalse( json == null );

			// JSONArray of moves
			JSONArray jsonMoves = json.getJSONArray("moves");
			assertFalse( jsonMoves == null );
			assertEquals( jsonMoves.length(), 1 );
			
			// First move
			json = jsonMoves.getJSONObject(0);
			assertFalse( jsonMoves == null );
			
			Map<String,String> map = new HashMap<String,String>();
			Iterator<?> iter = json.keys();
			while(iter.hasNext()){
				String key = (String)iter.next();
				String value = json.getString(key);
				map.put(key,value);
			}
			
			assertEquals( map.get("white"), testWhite );
			assertEquals( map.get("black"), testBlack );
			assertEquals( map.get("move"), testMove );	
			
			gameServlet.getGame( testId );
			assertFalse (testId == 0);
			
			// Check Game is correct post-moves
			response = gameServlet.getGame( testId );
			assertFalse( response == null );
			assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

			reply = "{reply:" + response.getEntity().toString() + "}";
			jObject = new JSONObject(reply);
			json = jObject.getJSONObject("reply");
			assertFalse( json == null );

			assertEquals( json.getString("next"), "W" );
			assertEquals( json.getString("move"), "2" );			
			
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}			
	}
}
