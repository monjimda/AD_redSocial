package com.example.dao;

import java.util.Iterator;

import org.jongo.MongoCollection;

import com.example.models.Usuario;

public class UsuarioDAO {

	private static UsuarioDAO singleton;
	private static MongoCollection dao;
	private static final String COLLECTION_NAME_MONGO = "usuarios";

	private UsuarioDAO() throws Exception {
		dao = DataBase.getInstance().getCollection(COLLECTION_NAME_MONGO);
	}

	public static UsuarioDAO getInstance() throws Exception {
		if (singleton == null) {
			singleton = new UsuarioDAO();
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
	public Usuario getUsuario(String key) {
		return dao.findOne("{'_id':#}", key).as(Usuario.class);
	}

	public void clearStore() {
		dao.drop();
	}
}
