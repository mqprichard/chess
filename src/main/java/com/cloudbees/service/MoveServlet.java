package com.cloudbees.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/move")
public class MoveServlet {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getMove() {
		return "Move";
	}
}