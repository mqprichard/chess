package com.cloudbees.chess;

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

    public ResultSet getGame(long id) {
        ResultSet rst = null; 
        try{
        	String query = "select * from GAME where id = " + id;
            stmt = conn.createStatement();
            rst = stmt.executeQuery(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst;
    }
    
    public ResultSet getBoard (long game_id){
        ResultSet rst = null; 
        try{
         	String query = "select * from BOARD where game_id = " + game_id;     	
            stmt = conn.createStatement();
            rst = stmt.executeQuery(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst; 
    }
 
    public ResultSet getMoves (long game_id){
        ResultSet rst = null; 
        try{
         	String query = "select * from MOVE where game_id = " + game_id;     	
            stmt = conn.createStatement();
            rst = stmt.executeQuery(query);
        } catch (Exception e){
            e.printStackTrace ();
        } 
        return rst; 
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
