package com.example.services;

import java.util.HashMap;
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

import com.example.controllers.UsuarioController;
import com.example.models.Usuario;
import com.example.utils.Message;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


@Path("/usuario/")
@Api(value = "/usuario", description = "Login operations")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService extends Service{

	private static final Logger log = Logger.getLogger(LoginService.class.getName());

	@POST
	@ApiOperation(value = "creacion de usuarios", notes = "recibe un usuario y lo guarda en la BDD")
	public Response postUsuario(Usuario resource) {
		
		try{
			UsuarioController usuarioController = UsuarioController.getInstance();
		    out = usuarioController.createUsuario(resource);
			log.info("Create user : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@PUT
	@ApiOperation(value = "actualizar usuario", notes = "recibe un usuario modificado")
	public Response putUsuario(Usuario resource) {
		
		try{
			UsuarioController usuarioController = UsuarioController.getInstance();
		    out = usuarioController.updateUsuario(resource);
			log.info("Update user : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@GET
	@Path("/{key}")
	@ApiOperation(value = "actualizar usuario", notes = "recibe un usuario modificado")
	public Response getUsuario(@PathParam("key")String key) {
		
		try{
			UsuarioController usuarioController = UsuarioController.getInstance();
		    out = usuarioController.getUsuario(key);
			log.info("Get user : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
	@DELETE
	@Path("/{key}")
	@ApiOperation(value = "actualizar usuario", notes = "recibe un usuario modificado")
	public Response deleteUsuario(@PathParam("key")Map<String,String> datos) {
		
		try{
			UsuarioController usuarioController = UsuarioController.getInstance();
		    out = usuarioController.deleteUsuario(datos);
			log.info("delete user : Operation successful");
			status = Response.Status.ACCEPTED;
		}catch(Exception e){
			status = Response.Status.BAD_REQUEST;
			log.error("Error detected: ", e);
			out = new Message(e.getMessage());
		}
		return Response.status(status).entity(out).build();
	}
}