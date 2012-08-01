package com.cloudbees.service;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/board")
public class BoardServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getBoard() {
		return "Board";
	}
}