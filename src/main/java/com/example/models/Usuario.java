package com.example.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jongo.marshall.jackson.oid.Id;

/**
 * The Class Usuario.
 */
public class Usuario {
	
	@Id
	private String nick;
	private String password;
	private String role;
	private String name;
	private String apellidos;
	private String direccion;
	private String email;
	private String trabajo;
	private String educacionInferior;
	private String educacionSuperior;
	private String carrera;
	private String fechaCumpleanios;
	private String ciudad;
	private List<String> amigos;
	private List<String> amigosPendientes;
	private List<String> fotos;
	private Tablon tablon;

	public Tablon getTablon() {
		return tablon;
	}

	public void setTablon(Tablon tablon) {
		this.tablon = tablon;
	}

	public Usuario() {
		amigos = new ArrayList<String>();
		amigosPendientes = new ArrayList<String>();
	}

	public Usuario(String nick, String role, String password) {
		this.nick = nick;
		this.role = role;
		this.password = password;
		amigos = new ArrayList<String>();
		amigosPendientes = new ArrayList<String>();
	}

	public Usuario(Usuario u) {
		this.name = u.getName();
		this.role = u.getRole();
		amigos = new ArrayList<String>();
		amigosPendientes = new ArrayList<String>();
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}

	public String getEducacionInferior() {
		return educacionInferior;
	}

	public void setEducacionInferior(String educacionInferior) {
		this.educacionInferior = educacionInferior;
	}

	public String getEducacionSuperior() {
		return educacionSuperior;
	}

	public void setEducacionSuperior(String educacionSuperior) {
		this.educacionSuperior = educacionSuperior;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getFechaCumpleanios() {
		return fechaCumpleanios;
	}

	public void setFechaCumpleanios(String fechaCumpleanios) {
		this.fechaCumpleanios = fechaCumpleanios;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<String> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<String> amigos) {
		this.amigos = amigos;
	}

	public List<String> getAmigosPendientes() {
		return amigosPendientes;
	}

	public void setAmigosPendientes(List<String> amigosPendientes) {
		this.amigosPendientes = amigosPendientes;
	}

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", password=" + password + ", role="
				+ role + ", name=" + name + ", apellidos=" + apellidos
				+ ", direccion=" + direccion + ", email=" + email
				+ ", trabajo=" + trabajo + ", educacionInferior="
				+ educacionInferior + ", educacionSuperior="
				+ educacionSuperior + ", carrera=" + carrera
				+ ", fechaCumpleanios=" + fechaCumpleanios + ", ciudad="
				+ ciudad + ", amigos=" + amigos + ", amigosPendientes="
				+ amigosPendientes + ", fotos=" + fotos + ", tablon=" + tablon
				+ "]";
	}

	
	
}