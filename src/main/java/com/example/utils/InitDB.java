package com.example.utils;

import org.apache.log4j.Logger;

import com.example.controllers.UsuarioController;
import com.example.dao.UsuarioDAO;
import com.example.models.Usuario;


public class InitDB {

	private static final Logger LOG = Logger.getLogger(InitDB.class.getName());



	public static void loadUsers() throws Exception {
		UsuarioDAO userDAO = UsuarioDAO.getInstance();
		userDAO.clearStore();
		UsuarioController controller = UsuarioController.getInstance();
		Usuario carga = new Usuario("a", "ROLE_USER", "a");
		controller.createUsuario(carga);
		carga = new Usuario("test", "ROLE_ADMIN", "test");
		controller.createUsuario(carga);
		LOG.info("Users inserted in DB");
	}

	public static void main(String[] args) throws Exception {
		InitDB.loadUsers();
	}
}