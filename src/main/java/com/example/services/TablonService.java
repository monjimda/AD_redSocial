package com.example.services;


import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.example.controllers.TablonController;
import com.example.models.Tablon;
import com.example.utils.Message;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/tablon")
@Api(value = "/tablon", description = "Gestion del arbol de tablon")
@Produces(MediaType.APPLICATION_JSON)
public class TablonService extends Service{

	private static final Logger log = Logger.getLogger(TablonService.class.getName());

	public TablonService() {
		super();
	}
	
	
	@GET
	@ApiOperation(value = "arbol tablon", notes = "Devuelve todo el arbol de tablon")
	public Response getTablon(String propietario) {
		try {
			TablonController tecnologiaController = TablonController.getInstance();
			out = tecnologiaController.getTablon(propietario);
			log.info("Get All tecnologias: Operation successful");
		} catch (Exception e) {
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}

	@DELETE
	@ApiOperation(value = "Devuelve una tecnologia por su nombre", notes = "Devuelve una tecnologia por su nombre")
	public Response deleteComentario(Map<String,Object> resource) {
		try {
			TablonController tecnologiaController = TablonController.getInstance();
			out = tecnologiaController.deleteComentario(resource);
			log.info("Get Tecnologia by key: Operation successful");
		} catch (Exception e) {
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@POST
	@ApiOperation(value = "Devuelve una tecnologia por su nombre", notes = "Devuelve una tecnologia por su nombre")
	public Response postComentario(Map<String,String> resource) {
		try {
			TablonController tecnologiaController = TablonController.getInstance();
			out = tecnologiaController.createComentario(resource);
			log.info("Get Tecnologia by key: Operation successful");
		} catch (Exception e) {
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	
}