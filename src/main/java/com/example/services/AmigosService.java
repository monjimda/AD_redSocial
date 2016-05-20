package com.example.services;

import java.util.List;
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

import com.example.controllers.AmigosController;
import com.example.controllers.UsuarioController;
import com.example.models.Usuario;
import com.example.utils.Message;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/amigos/")
@Api(value = "/amigos", description = "amigos del usuario")
@Produces(MediaType.APPLICATION_JSON)
public class AmigosService extends Service{

	private static final Logger log = Logger.getLogger(LoginService.class.getName());

	@POST
	@Path("/pendientes/")
	@ApiOperation(value = "creacion de usuarios", notes = "recibe un usuario y lo guarda en la BDD")
	public Response postPeticionAmistad(String idUsuario) {
		
		try{
			AmigosController amigosController = AmigosController.getInstance();
		    out = amigosController.createPeticionAmistad(idUsuario);
			log.info("envio peticion amistad : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@POST
	@ApiOperation(value = "creacion de usuarios", notes = "recibe un usuario y lo guarda en la BDD")
	public Response postAmigo(String idUsuario) {
		
		try{
			AmigosController amigosController = AmigosController.getInstance();
		    out = amigosController.createAmistad(idUsuario);
			log.info("envio peticion amistad : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@PUT
	@Path("/pendientes/")
	@ApiOperation(value = "actualizar lista amigos", notes = "recibe un usuario modificado")
	public Response putAmigosPendientes(List<String> amigos) {
		
		try{
			AmigosController amigosController = AmigosController.getInstance();
		    out = amigosController.updateAmigosPendientes(amigos);
			log.info("Update lista amigos: Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@GET
	@Path("/pendientes/")
	@ApiOperation(value = "actualizar usuario", notes = "recibe un usuario modificado")
	public Response getAmigosPendientes() {
		
		try{
			AmigosController amigosController = AmigosController.getInstance();
		    out = amigosController.getAmigosPendientes();
			log.info("Get lista amigos : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@DELETE
	@Path("/pendientes/{key}")
	@ApiOperation(value = "actualizar usuario", notes = "recibe un usuario modificado")
	public Response deleteAmigoPendiente(@PathParam("key")String idUsuario) {
		
		try{
			AmigosController amigosController = AmigosController.getInstance();
		    out = amigosController.deleteAmigoPendiente(idUsuario);
			log.info("delete lista amigos : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
}
