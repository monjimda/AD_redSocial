package com.example.controllers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.example.dao.UsuarioDAO;
import com.example.models.Tablon;
import com.example.models.Usuario;


public class TablonController {
	
	private static UsuarioDAO dao;
	private static TablonController singleton;
	private static boolean encontrado = false;

	private TablonController() throws Exception {
		dao = UsuarioDAO.getInstance();
	}

	public static TablonController getInstance() throws Exception {
		if (singleton == null) {
			singleton = new TablonController();
		}
		return singleton;
	}

	public Object getTablon(String propietario) {
		Usuario user = dao.getUser(propietario);
		
		return user.getTablon();
	}

	public Object updateTablon(String propietario, Tablon tablon) {
		
		Usuario user = dao.getUser(propietario);
		user.setTablon(tablon);
		dao.updateUsuario(user);
		return user.getTablon();
	}
}
