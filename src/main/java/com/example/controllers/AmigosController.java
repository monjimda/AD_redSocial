package com.example.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.dao.UsuarioDAO;
import com.example.models.Usuario;
import com.example.utils.Config;

public class AmigosController {

	// Singleton instances
		private UsuarioDAO dao;
		private static AmigosController singleton;

		private AmigosController() throws Exception {
			dao = UsuarioDAO.getInstance();
		}

		public static AmigosController getInstance() throws Exception {
			if (singleton == null) {
				singleton = new AmigosController();
			}
			return singleton;
		}

		
		public Usuario createPeticionAmistad(String idUsuario) throws Exception {
			
			Usuario user = dao.getUser(idUsuario);
			System.out.println(user);
			List<String> amigosPendientesUser = user.getAmigosPendientes();
			amigosPendientesUser.add(SecurityContextHolder.getContext().getAuthentication().getName());
			user.setAmigosPendientes(amigosPendientesUser);
			dao.updateUsuario(user);
			user = dao.getUser(idUsuario);
			System.out.println(user);
			return user;
		}
		
		public boolean createAmistad(String idUsuario) throws Exception {
			
			Usuario user = dao.getUser(idUsuario);
			Usuario user2 = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			List<String> amigosPendientes = user2.getAmigosPendientes();
			amigosPendientes.remove(idUsuario);
			user2.setAmigosPendientes(amigosPendientes);
			List<String> amigos = user2.getAmigos();
			amigos.add(idUsuario);
			user2.setAmigos(amigos);
			dao.updateUsuario(user2);
			
			amigos = user.getAmigos();
			amigos.add(SecurityContextHolder.getContext().getAuthentication().getName());
			user.setAmigos(amigos);
			dao.updateUsuario(user);
			
			return true;
		}

		/**
		 * Get all users
		 */
		public List<String> getAmigosPendientes() throws Exception {
			List<String> list = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getAmigosPendientes();
			return list;
		}
		
		public List<String> updateAmigosPendientes(List<String> amigos) throws Exception {
			
			Usuario user = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			user.setAmigos(amigos);
			dao.updateUsuario(user);
			return amigos;
		}
		public String deleteAmigoPendiente(String id) throws Exception {
			
			Usuario user = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			List<String> amigosPendientes = user.getAmigosPendientes();
			amigosPendientes.remove(id);
			user.setAmigos(amigosPendientes);
			dao.updateUsuario(user);
			return id;
		}

		public Usuario getAmigo(String idUsuario) {
			
			Usuario user = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			if((user.getAmigos()!=null && user.getAmigos().contains(idUsuario)) || user.getRole().equals("ROLE_ADMIN")){
			return dao.getUser(idUsuario);
			}
			return null;
		}

		public int getEsAmigo(String idUsuario) {
			
			Usuario user = dao.getUser(idUsuario);
			if(user.getAmigos()==null && user.getAmigosPendientes()==null){
			return 0;
			}else if(user.getAmigos()!=null && user.getAmigos().contains(idUsuario)){
			return 2;
			}else if(user.getAmigosPendientes()!=null && user.getAmigosPendientes().contains(SecurityContextHolder.getContext().getAuthentication().getName())){
			return 1;
			}
			return 0;
		}

}
