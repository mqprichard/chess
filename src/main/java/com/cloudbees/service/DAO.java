package com.cloudbees.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

    String url = "java:comp/env/jdbc/mp_chess";
    Connection conn;
    Statement stmt;

    public DAO() {
    }

    public void connect() {

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(url);
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet getGame(long game) {
        ResultSet rst = null; 
        try{
        	String query = "select * from GAME where id = " + game;
            stmt = conn.createStatement();
            rst = stmt.executeQuery(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst;
    }
    
    public ResultSet getBoard (long game){
        ResultSet rst = null; 
        try{
         	String query = "select * from BOARD where game = " + game;     	
            stmt = conn.createStatement();
            rst = stmt.executeQuery(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst; 
    }
 
    public ResultSet getMoves (long game){
        ResultSet rst = null; 
        try{
         	String query = "select * from MOVES where game = " + game;     	
            stmt = conn.createStatement();
            rst = stmt.executeQuery(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst; 
    }
    
    public String newGame (String white, String black, String description){
        String key = "";
        String sql = "insert into GAME (white, black, description)"
                   + "values (" + white +"," + black + "," + description + ")";
        try{
        	int rows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
         	ResultSet rs = stmt.getGeneratedKeys();
         	if (rs.next()) {
         		key = rs.getString(1);
         	}
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return key; 
    }  
    
    public long newWhiteMove (long move, String white, long game){
        long result = 0; 
        try{
         	String query = "insert into MOVES (move, white, game)"
                         + "values (" + move + "," + white + "" + game + ")";
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return result; 
    }  
    
    public long newBlackMove (long move, String black, long game) {
    	long result = 0;
        try{
         	String query = "update MOVES set " + black
                         + "where move = " + move + " and game = " + game;
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return result; 
    }      	
       
    public void disconnect (){
        try{
            if (conn != null){
                if (stmt != null) stmt.close ();
                conn.close ();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
