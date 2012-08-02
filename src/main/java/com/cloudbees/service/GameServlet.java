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
	public String getGame(@PathParam("id") long id ) {
		
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);

		try {
	       DAO dao = new DAO();
	       dao.connect();

	       ResultSet rst = dao.getGame(id);		   
	       if (rst != null && rst.first()) {
			   writer.beginObject();
		       writer.name("id").value(rst.getInt(1));
		       writer.name("white").value(rst.getString(2));	            	
		       writer.name("black").value(rst.getString(3));
		       writer.name("description").value(rst.getString(4));
			   writer.endObject();
			   writer.close();	       
	       }  
	       
		   dao.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	   return sw.toString();
	}	

	@POST
    @Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String newGame(Game game) {
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);
		try {
			DAO dao = new DAO();
		    dao.connect();
			long key = dao.newGame(game.getWhite(),
					                 game.getBlack(),
					                 game.getDescription());
			writer.beginObject();
			writer.name("id").value(key);
			writer.endObject();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();		
	}
}
