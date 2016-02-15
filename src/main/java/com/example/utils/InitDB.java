package com.example.utils;

import org.apache.log4j.Logger;

import com.example.controllers.UsuarioController;
import com.example.dao.UsuarioDAO;


public class InitDB {

	private static final Logger LOG = Logger.getLogger(InitDB.class.getName());



	public static void loadUsers() throws Exception {
		UsuarioDAO userDAO = UsuarioDAO.getInstance();
		userDAO.clearStore();
		UsuarioController controller = UsuarioController.getInstance();
		controller.createUser("a", "ROLE_USER", "a");
		controller.createUser("test", "ROLE_ADMIN", "test");
		LOG.info("Users inserted in DB");
	}

	public static void main(String[] args) throws Exception {
		InitDB.loadUsers();
	}
}