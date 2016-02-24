package com.example.models;

import java.util.Arrays;

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
	private String fechaCumpleaños;
	private String ciudad;
	private String[] amigos;
	private String[] amigosPendientes;
	private String[] fotos;

	public Usuario() {
	}

	public Usuario(String nick, String role, String password) {
		this.nick = nick;
		this.role = role;
		this.password = password;
	}

	public Usuario(Usuario u) {
		this.name = u.getName();
		this.role = u.getRole();
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

	public String getFechaCumpleaños() {
		return fechaCumpleaños;
	}

	public void setFechaCumpleaños(String fechaCumpleaños) {
		this.fechaCumpleaños = fechaCumpleaños;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String[] getAmigos() {
		return amigos;
	}

	public void setAmigos(String[] amigos) {
		this.amigos = amigos;
	}

	public String[] getAmigosPendientes() {
		return amigosPendientes;
	}

	public void setAmigosPendientes(String[] amigosPendientes) {
		this.amigosPendientes = amigosPendientes;
	}

	public String[] getFotos() {
		return fotos;
	}

	public void setFotos(String[] fotos) {
		this.fotos = fotos;
	}

	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", password=" + password + ", role="
				+ role + ", name=" + name + ", apellidos=" + apellidos
				+ ", direccion=" + direccion + ", trabajo=" + trabajo
				+ ", educacionInferior=" + educacionInferior
				+ ", educacionSuperior=" + educacionSuperior + ", carrera="
				+ carrera + ", fechaCumpleaños=" + fechaCumpleaños
				+ ", ciudad=" + ciudad + ", amigos=" + Arrays.toString(amigos)
				+ ", amigosPendientes=" + Arrays.toString(amigosPendientes)
				+ ", fotos=" + Arrays.toString(fotos) + "]";
	}

}