package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.controllers.UsuarioController;
import com.example.dao.TecnologiaDAO;
import com.example.dao.UsuarioDAO;
import com.example.models.Tecnologia;
import com.example.models.Usuario;


public class InitDB {

	private static final Logger LOG = Logger.getLogger(InitDB.class.getName());

	public static void loadTecnologias() throws Exception {
		
		// este metodo es neceasario para preparar la base de datos frente a la gfestion de tecnologias
		// inserta una tecnologia raiz a partir de la cual trabajara la capa controladora
		TecnologiaDAO tecnologiaDAO = TecnologiaDAO.getInstance();
		if(tecnologiaDAO.getTecnologia("Tecnologias")== null){
			
			List<Tecnologia> lista = new ArrayList<Tecnologia>();
			Tecnologia t = new Tecnologia("hojaInvalida1",null,false,null,"hojaInvalida");
			lista.add(t);
			t = new Tecnologia("hojaInvalida2",null,false,null,"hojaInvalida");
			lista.add(t);
			t = new Tecnologia("hojaInvalida3",null,false,null,"hojaInvalida");
			lista.add(t);
			Tecnologia raiz = new Tecnologia("Tecnologias",lista,false,null,"raiz");
			tecnologiaDAO.insertTecnologia(raiz);	
		}	
	}

	public static void loadUsers() throws Exception {
		UsuarioDAO userDAO = UsuarioDAO.getInstance();
		userDAO.clearStore();
		UsuarioController controller = UsuarioController.getInstance();
		Usuario carga = new Usuario("a", "ROLE_USER", "a");
		controller.createUsuario(carga);
		carga = new Usuario("b", "ROLE_USER", "b");
		controller.createUsuario(carga);
		carga = new Usuario("c", "ROLE_USER", "c");
		controller.createUsuario(carga);
		carga = new Usuario("d", "ROLE_USER", "d");
		controller.createUsuario(carga);
		carga = new Usuario("test", "ROLE_ADMIN", "test");
		controller.createUsuario(carga);
		LOG.info("Users inserted in DB");
	}

	public static void main(String[] args) throws Exception {
		InitDB.loadUsers();
		InitDB.loadTecnologias();
	}
}