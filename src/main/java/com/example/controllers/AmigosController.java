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

import com.example.dao.AmigosDAO;
import com.example.dao.UsuarioDAO;
import com.example.models.Usuario;
import com.example.utils.Config;

public class AmigosController {

	// Singleton instances
		private AmigosDAO dao;
		private static AmigosController singleton;

		private AmigosController() throws Exception {
			dao = AmigosDAO.getInstance();
		}

		public static AmigosController getInstance() throws Exception {
			if (singleton == null) {
				singleton = new AmigosController();
			}
			return singleton;
		}

		
		public Usuario createPeticionAmistad(String idUsuario) throws Exception {
			
			Usuario user = dao.getUser(idUsuario);
			String[] amigosPendientesUser = user.getAmigosPendientes();
			amigosPendientesUser[user.getAmigosPendientes().length] = SecurityContextHolder.getContext().getAuthentication().getName();
			user.setAmigosPendientes(amigosPendientesUser);
			return user;
		}

		/**
		 * Get all users
		 */
		public List<Usuario> getUsers() throws Exception {
			List<Usuario> list = new ArrayList<Usuario>();
			Iterator<Usuario> i = dao.getUsers();
			while (i.hasNext()) {
				list.add(i.next());
			}
			return list;
		}
		
		public Usuario updateUsuario(Usuario resource) throws Exception {
			
			if(resource.getNick().equals(SecurityContextHolder.getContext().getAuthentication().getName()) || resource.getRole().equals("ROLE_ADMIN")){
//				resource.setPassword(this.makePasswordHash(resource.getPassword(), this.generateSalting()));
				dao.updateUsuario(resource);
			}else{
				throw new Exception("No puedes modificar ese usuario");
			}
			return resource;
		}
		
		public String deleteUsuario(Map<String,String> datos) throws Exception {
			
			String idUser = datos.get("nick");
			String autor = datos.get("autor");
			if(idUser.equals(SecurityContextHolder.getContext().getAuthentication().getName()) || dao.getUsuario(autor).getRole().equals("ROLE_ADMIN")){
				dao.deleteUsuario(idUser);
			}else{
				throw new Exception("No puedes borrar ese usuario");
			}
			return idUser;
		}
		
		public Usuario getUsuario(String key) throws Exception {
					
			return 	dao.getUsuario(key);
			
		}
	

}
