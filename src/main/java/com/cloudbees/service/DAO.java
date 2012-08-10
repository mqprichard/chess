package com.cloudbees.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

    private Connection conn;
    private boolean isConnected;
    private Statement stmt;

    public DAO() {
    	isConnected = false;
    }

    public void connect() {
    	String url = "java:comp/env/jdbc/mp_chess";
        
    	try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(url);
            conn = ds.getConnection();
            isConnected = true;   
        } 
    	catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getGame(long game) {
        ResultSet rst = null; 
        try{
        	String query = "select * from GAME where id = " + game;
            stmt = getConnection().createStatement();
            rst = stmt.executeQuery(query);
        } 
        catch (Exception e){
            e.printStackTrace ();
        } 
        return rst;
    }
    
    public ResultSet getNext(long game) {
        ResultSet rst = null; 
        
        try{
        	String query = "select next from GAME where id = " + game;
            stmt = getConnection().createStatement();
            rst = stmt.executeQuery(query);
        } 
        catch (Exception e){
            e.printStackTrace ();
        } 
        return rst;
    }    
    
    public ResultSet getMoves (long game){
        ResultSet rst = null; 
        
        try{
         	String query = "select * from MOVES where game = " + game;     	
            stmt = getConnection().createStatement();
            rst = stmt.executeQuery(query);
        } 
        catch (Exception e){
            e.printStackTrace ();
        } 
        return rst; 
    }
    
    public long newGame (String white, String black, String description){
        long key = 0;
        String sql = "insert into GAME (white, black, description, next, move)"
                   + " values (\"" + white + "\",\"" + black + "\",\"" + description + "\",\"W\",\"1\")";
        
        try{
            stmt = getConnection().createStatement();
        	int rows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        	if (rows == 0) return key;
         	ResultSet rs = stmt.getGeneratedKeys();
         	if (rs.next()) {
         		key = rs.getLong(1);
         	}
        } 
        catch (Exception e){
            e.printStackTrace ();
        } 
        return key; 
    } 
    
    public long updateGame (long id, long move, String next, String result) {
    	long rows = 0;
    	String sql = "update GAME"
    			   + " set move = \"" + move + "\",next = \"" + next + "\",result = \"" + result
    			   + "\" where id = \"" + id +"\"";

    	try {
    		stmt = conn.createStatement();
    		rows = stmt.executeUpdate(sql);
    	} 
    	catch (Exception e){
    		e.printStackTrace ();
    	} 
    	return rows;     	
}    
    
    public long newWhiteMove (long move, String white, long game){
        long key = 0; 
        String sql = "insert into MOVES (move, white, game)"
                   + "values (\"" + move + "\",\"" + white + "\",\"" + game + "\")";
        
        try{
         	stmt = getConnection().createStatement();
            int rows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        	if (rows == 0) return key;
         	ResultSet rs = stmt.getGeneratedKeys();
         	if (rs.next()) {
         		key = rs.getLong(1);
         	}
        } 
        catch (Exception e){
            e.printStackTrace ();
        } 

        return key; 
    }  
    
    public long newBlackMove (long move, String black, long game) {
    	long rows = 0;
        
    	try{
         	String query = "update MOVES set black = \"" + black + "\"" 
                         + " where move = \"" + move + "\" and game = \"" + game + "\"";
            stmt = getConnection().createStatement();
            rows = stmt.executeUpdate(query);
        } 
        catch (Exception e){
            e.printStackTrace ();
        }

        return rows; 
    }      	
       
    public void disconnect (){
        try{
            if (conn != null) {
                if (stmt != null) stmt.close ();
                conn.close ();
                isConnected = false;
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }

	public Connection getConnection() {
        if (isConnected == false)
        	connect();
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
