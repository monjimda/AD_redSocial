package com.example.services;

import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.example.controllers.UsuarioController;
import com.example.models.Usuario;
import com.example.utils.Message;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/usuario/")
@Api(value = "/usuario", description = "Login operations")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService {

	private static final Logger log = Logger.getLogger(LoginService.class.getName());

	@POST
	@ApiOperation(value = "creacion de usuarios", notes = "recibe un usuario y lo guarda en la BDD")
	public Response postUsuario(Usuario resource) {
		try{
			UsuarioController usuarioController = UsuarioController.getInstance();
//			out = new Message(usuarioController.deleteUsuario(key));
//			log.info("Delete usuario : Operation successful");
//			status = Response.Status.ACCEPTED;
		}catch(Exception e){
//			status = Response.Status.BAD_REQUEST;
//			log.error("Error detected: ", e);
//			out = new Message(e.getMessage());
		}
//		return Response.status(status).entity(out).build();
		return null;
	}
}