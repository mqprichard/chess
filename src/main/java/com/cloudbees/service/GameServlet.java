package com.cloudbees.service;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.cloudbees.model.Game;
import com.google.gson.stream.JsonWriter;


@Path("/game")
public class GameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
//	@PersistenceContext(unitName="chess", type=PersistenceContextType.TRANSACTION)
//	EntityManager entityManager;
	
	@GET
    @Path("{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGame(@PathParam("id") long id ) {
		StatusType statusCode = null;
		String msg = null;
		
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);
	    DAO dao = new DAO();
		
		try {
	       dao.connect();
	       ResultSet rst = dao.getGame(id);		   
	       if (rst != null && rst.first()) {
			   writer.beginObject();
		       writer.name("id").value(rst.getInt(1));
		       writer.name("white").value(rst.getString(2));	            	
		       writer.name("black").value(rst.getString(3));
		       writer.name("description").value(rst.getString(4));
		       writer.name("result").value(rst.getString(5));
		       writer.name("next").value(rst.getString(6));
		       writer.name("move").value(rst.getInt(7));
			   writer.endObject();
			   writer.close();	  
			   
			   msg = sw.toString();
			   statusCode = Response.Status.OK;
	       }
	       else {
   			// Return 400 Bad Request
	    	   statusCode = Response.Status.BAD_REQUEST;
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

	@POST
    @Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newGame(Game game) {
		StatusType statusCode = null;
		String msg = null;
		long key = 0;
		
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);
		DAO dao = new DAO();
		
		try {			
		    dao.connect();
		    
		    // Create a new game (key = game id)
			key = dao.newGame( game.getWhite(),
					           game.getBlack(),
					           game.getDescription() );
			if (key == 0) {
    			// Return 400 Bad Request
	    		statusCode = Response.Status.BAD_REQUEST;
			} else {	
				writer.beginObject();
				writer.name("id").value(key);
				writer.endObject();
				writer.close();
				
				statusCode = Response.Status.OK;
				msg = sw.toString();
			}
		} 
		catch (IOException e) {
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
