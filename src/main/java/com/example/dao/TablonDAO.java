package com.example.dao;

import java.util.Iterator;

import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.example.models.Tablon;


public class TablonDAO {

	private static TablonDAO singleton;
	private static MongoCollection dao;
	private static final String COLLECTION_NAME_MONGO = "Tablon";

	private TablonDAO() throws Exception {
		dao = DataBase.getInstance().getCollection(COLLECTION_NAME_MONGO);
	}

	public static TablonDAO getInstance() throws Exception {
		if (singleton == null) {
			singleton = new TablonDAO();
		}
		return singleton;
	}

	public Iterator<Tablon> getTablones() {
		return dao.find().as(Tablon.class).iterator();
	}

	public Tablon getTablon(ObjectId idTablon) {
		return dao.findOne("{'_id':#}", idTablon).as(Tablon.class);
	}

	public Tablon createTablon(Tablon tablon) {
		dao.insert(tablon);
		return dao.findOne("{$and:"+" [ { contenido: #},"+"{ propietario:#}]}",tablon.getContenido(),tablon.getPropietario()).as(Tablon.class);
	}
	public void updateTablon(Tablon tablon) {
		dao.update("{_id:'"+tablon.get_id()+"'}").with(tablon);
	}
	public void deleteTablon(ObjectId idTablon) {
		dao.remove("{_id:'"+idTablon+"'}");
	}
	public void clearStore() {
		dao.drop();
	}
}
