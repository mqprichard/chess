package com.cloudbees.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.servlet.http.HttpServlet;

import com.cloudbees.model.Game;


@Path("/game")
public class GameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	@PersistenceContext(unitName="chess", type=PersistenceContextType.TRANSACTION)
	EntityManager entityManager;
	Game game;
	
	@GET
    @Path("{id}")	
	@Produces(MediaType.APPLICATION_JSON)
//	public String getGame(@PathParam("id") long id ) {
//		return "{Game: }";

	public Game getGame(@PathParam("id") long id ) {
		try {
		game = entityManager.find(Game.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return game;
	}	
}
