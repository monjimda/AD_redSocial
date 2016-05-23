package com.example.controllers;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.dao.TablonDAO;
import com.example.dao.UsuarioDAO;
import com.example.models.Tablon;
import com.example.models.Usuario;

import de.undercouch.bson4jackson.types.ObjectId;


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

	public Object createComentario(Map<String, String> resource) {
		
		Tablon comentario = new Tablon(resource.get("contenido") ,null, resource.get("propietario"));
		Usuario user = dao.getUser(resource.get("duenio"));
		if(user.getTablon().get_id().equals(resource.get("idPadre"))){
			user.getTablon().getNodosHijos().add(comentario);
		}else{
		user.setTablon(colocar(comentario, user.getTablon(), resource.get("idPadre")));
		}
		dao.updateUsuario(user);
		return user.getTablon();
	}
	
	private Tablon colocar(Tablon comentario, Tablon tablon, String idPadre) {
		
		Iterator<Tablon> iterador = tablon.getNodosHijos().iterator();
		while(iterador.hasNext()){
			
			Tablon padre = iterador.next();
			if(padre.get_id().equals(idPadre)){
				
				padre.getNodosHijos().add(comentario);
			}else{
				colocar(comentario, padre , idPadre);
			}
			
		}
		
		return tablon;
		
	}

	

	public Object deleteComentario(Map<String, Object> resource) {
		
		Usuario user = dao.getUser((String)resource.get("duenio"));
		user.setTablon(borrar((Tablon)user.getTablon(), (String)resource.get("id")));
		dao.updateUsuario(user);
		return user.getTablon();
	}
	
	private Tablon borrar(Tablon tablon, String id) {
		
		Iterator<Tablon> iterador = tablon.getNodosHijos().iterator();
		while(iterador.hasNext()){
			
			Tablon actual = iterador.next();
			if(actual.get_id().equals(id)){
				
				tablon.getNodosHijos().remove(actual);
			}else{
				borrar( actual , id);
			}
			
		}
		
		return tablon;
		
	}
	public static void main(String[] args) throws Exception {
		
		Calendar fecha = Calendar.getInstance();
		Date actual = new Date(fecha.getTimeInMillis());
		TablonDAO dao = TablonDAO.getInstance();
		Tablon prueba = new Tablon("tablon prueba",null,"pepito");
		System.out.println(prueba);
		System.out.println(dao.createTablon(prueba));
		
		
//		ObjectId pru = new ObjectId();
//		System.out.println(pru.toString());
	}
}
