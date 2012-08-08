package com.cloudbees.service;

import java.io.StringWriter;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.cloudbees.model.Move;
import com.google.gson.stream.JsonWriter;

@Path("/moves")
public class MoveServlet {
	@GET
    @Path("{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMoves(@PathParam("id") long id ) {
		StatusType statusCode = Response.Status.OK;
		
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);
	    DAO dao = new DAO();

		try {
	       dao.connect();

		   writer.beginObject();
		   writer.name("moves"); 
		   writer.beginArray();	
			  
	       ResultSet rst = dao.getMoves(id);		   
	       if (rst != null && rst.first()) {
		    	do { 
			          writer.beginObject();
			          writer.name("move").value(rst.getInt(2));
			          writer.name("white").value(rst.getString(3));	            	
			          writer.name("black").value(rst.getString(4));
		              writer.endObject();
			        } while (rst.next());  
	       }
	       else {
   				// Return 400 Bad Request
	    		statusCode = Response.Status.BAD_REQUEST;
	       }
	       
		   writer.endArray();
		   writer.endObject();
		   writer.close();

		   // Return 200 OK
		   statusCode = Response.Status.OK;	       
		} 
		catch (Exception e) {
			e.printStackTrace();
			
			// Return 500 Server Error
    		statusCode = Response.Status.INTERNAL_SERVER_ERROR;
		}
		finally {
			dao.disconnect();			
		}
		
		if (statusCode != Response.Status.OK)
			return Response.status(statusCode).build();
		else
			return Response.status(statusCode).entity(sw.toString()).build();
	}
	
	@POST
    @Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newMove( Move move ) {
		StatusType statusCode = null;
		String msg = null;

		DAO dao = new DAO();
		
		try {
		    dao.connect();
		    
		    ResultSet rst = dao.getGame(move.getGame());		   
		    if (rst != null && rst.first()) {
		    	long nextMove = rst.getInt(7);
		    	String nextPlayer = rst.getString(6);
		    	
		    	// White to move
		    	if (nextPlayer.equalsIgnoreCase("w")) {
		    		if (! move.isLegal( move.getWhite() )) {
		    			// Bad syntax: Return 400 Bad Request
			    		statusCode = Response.Status.BAD_REQUEST;
		    		} else {
		    			if (dao.newWhiteMove( move.getMove(), 
		    								  move.getWhite(), 
		    					              move.getGame()) == 0 ) 
			    			// Bad Move/Game: Return 400 Bad Request
				    		statusCode = Response.Status.BAD_REQUEST;
		    			else if (dao.updateGame( move.getGame(), nextMove, "B", "") == 0)
			    			// Cannot update Game: Return 400 Bad Request
		    				statusCode = Response.Status.BAD_REQUEST;
		    			else {
		    				msg = "{\"move\":" + move.getMove() 
		    					+ ",\"white\":" + move.getWhite() + "}";
			    			// Return 200 OK
				    		statusCode = Response.Status.OK;
		    			}
		    		}
		    	}
		    	// Black to move
		    	else {
		    		if (! move.isLegal( move.getBlack() )) {
		    			// Bad syntax: Return 400 Bad Request
			    		statusCode = Response.Status.BAD_REQUEST;
		    		} else { 
		    			if (dao.newBlackMove( move.getMove(), 
		    								  move.getBlack(), 
		    								  move.getGame()) == 0 )
		    				// Bad Move/Game: Return 400 Bad Request
				    		statusCode = Response.Status.BAD_REQUEST;
		    			else if (dao.updateGame( move.getGame(), nextMove+1, "W", "") == 0)
		    				// Cannot update Game: Return 400 Bad Request
		    				statusCode = Response.Status.BAD_REQUEST;		    			
		    			else {
		    				msg = "{\"move\":" + move.getMove() 
		    					+ ",\"black\":" + move.getBlack() + "}";
		    				// Return 200 OK
		    				statusCode = Response.Status.OK;
		    			}
		    		}
		    	}
		    }		    
		} 
		catch (Exception e) {
			e.printStackTrace();
			
			// Return 500 Internal Server Error
    		statusCode = Response.Status.INTERNAL_SERVER_ERROR;
		}
		finally {
		    dao.disconnect();
		}

		if (statusCode != Response.Status.OK)
			return Response.status(statusCode).build();
		else
			return Response.status(statusCode).entity(msg).build();		
	}
}

