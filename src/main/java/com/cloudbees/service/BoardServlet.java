package com.cloudbees.service;

import java.io.StringWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.stream.JsonWriter;

@Path("/board")
public class BoardServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@GET
    @Path("{id}")	
	@Produces(MediaType.APPLICATION_JSON)
	public String getBoard(@PathParam("id") long id ) {
		
		StringWriter sw = new StringWriter();
		JsonWriter writer = new JsonWriter(sw);

		try {
	       DAO dao = new DAO();
	       dao.connect();

	       ResultSet rst = dao.getBoard(id);		   
	       if (rst != null && rst.first()) {
			   writer.beginObject();
		       writer.name("game").value(rst.getInt(10));			   
		       writer.name("rank1").value(rst.getString(2));
		       writer.name("rank2").value(rst.getString(3));	            	
		       writer.name("rank3").value(rst.getString(4));
		       writer.name("rank4").value(rst.getString(5));
		       writer.name("rank5").value(rst.getString(6));
		       writer.name("rank6").value(rst.getString(7));	            	
		       writer.name("rank7").value(rst.getString(8));
		       writer.name("rank8").value(rst.getString(9));
			   writer.endObject();
			   writer.close();	       
	       }  
	       
		   dao.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	   return sw.toString();
	}
}