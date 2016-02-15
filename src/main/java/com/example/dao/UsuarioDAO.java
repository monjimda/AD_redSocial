package com.example.dao;

import java.util.Iterator;

import org.jongo.MongoCollection;

import com.example.models.User;

public class UsuarioDAO {

	private static UsuarioDAO singleton;
	private static MongoCollection dao;
	private static final String COLLECTION_NAME_MONGO = "users";

	private UsuarioDAO() throws Exception {
		dao = DataBase.getInstance().getCollection(COLLECTION_NAME_MONGO);
	}

	public static UsuarioDAO getInstance() throws Exception {
		if (singleton == null) {
			singleton = new UsuarioDAO();
		}
		return singleton;
	}

	public Iterator<User> getUsers() {
		return dao.find().as(User.class).iterator();
	}

	public User getUser(String idUser) {
		return dao.findOne("{'_id':#}", idUser).as(User.class);
	}

	public void insertUser(User user) {
		dao.insert(user);
	}

	public void clearStore() {
		dao.drop();
	}
}
