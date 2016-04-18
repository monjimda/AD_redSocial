package com.example.dao;

import java.util.Iterator;

import org.jongo.MongoCollection;

import com.example.models.Usuario;

public class AmigosDAO {
	
	private static AmigosDAO singleton;
	private static MongoCollection dao;
	private static final String COLLECTION_NAME_MONGO = "amigos";

	private AmigosDAO() throws Exception {
		dao = DataBase.getInstance().getCollection(COLLECTION_NAME_MONGO);
	}

	public static AmigosDAO getInstance() throws Exception {
		if (singleton == null) {
			singleton = new AmigosDAO();
		}
		return singleton;
	}

	public Iterator<Usuario> getUsers() {
		return dao.find().as(Usuario.class).iterator();
	}

	public Usuario getUser(String idUser) {
		return dao.findOne("{'_id':#}", idUser).as(Usuario.class);
	}

	public void createUsuario(Usuario user) {
		dao.insert(user);
	}
	public void updateUsuario(Usuario user) {
		dao.update("{_id:'"+user.getNick()+"'}").with(user);
	}
	public void deleteUsuario(String idUser) {
		dao.remove("{_id:'"+idUser+"'}");
	}
	public Usuario getUsuario(String key) {
		return dao.findOne("{'_id':#}", key).as(Usuario.class);
	}

	public void clearStore() {
		dao.drop();
	}
}
