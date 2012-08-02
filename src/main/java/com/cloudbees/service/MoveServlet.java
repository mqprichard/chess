package com.cloudbees.service;

import java.io.StringWriter;
import java.sql.ResultSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.stream.JsonWriter;

@Path("/moves")
public class MoveServlet {
	@GET
    @Path("{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	public String getMoves(@PathParam("id") long id ) {
		
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);

		try {
	       DAO dao = new DAO();
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
	       
		   writer.endArray();
		   writer.endObject();
		   writer.close();
	       
		   dao.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	   return sw.toString();
	}
}