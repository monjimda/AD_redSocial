package com.example.models;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.jongo.marshall.jackson.oid.Id;

import com.example.utils.ObjectIdJsonSerializer;


public class Tablon {
	
	@Id
	@JsonSerialize(using = ObjectIdJsonSerializer.class)
	private ObjectId _id;
	private String contenido;
	private List<Tablon> nodosHijos ;
	private String propietario;
	
	
	public Tablon(){
		
	}
	public Tablon(String contenido,List<Tablon> nodosHijos,String propietario){
		
		this.contenido = contenido;
		this.nodosHijos = nodosHijos;
		this.propietario = propietario;
		
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public List<Tablon> getNodosHijos() {
		return nodosHijos;
	}
	public void setNodosHijos(List<Tablon> nodosHijos) {
		this.nodosHijos = nodosHijos;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	@Override
	public String toString() {
		return "Tablon [_id=" + _id + ", contenido=" + contenido
				+ ", nodosHijos=" + nodosHijos + ", propietario=" + propietario
				+ "]";
	}
	
	
}
