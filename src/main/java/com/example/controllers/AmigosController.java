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
			String[] amigosPendientesUser = user.getAmigosPendientes();
			amigosPendientesUser[user.getAmigosPendientes().length] = SecurityContextHolder.getContext().getAuthentication().getName();
			user.setAmigosPendientes(amigosPendientesUser);
			dao.updateUsuario(user);
			return user;
		}
		
		public Usuario createAmistad(String idUsuario) throws Exception {
			
			Usuario user = dao.getUser(idUsuario);
			Usuario user2 = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			singleton.deleteAmigoPendiente(idUsuario);
			user.setAmigos( Arrays.asList(user.getAmigos()).add(SecurityContextHolder.getContext().getAuthentication().getName()) );
			String[] amigosPendientesUser = user.getAmigosPendientes();
			amigosPendientesUser[user.getAmigosPendientes().length] = SecurityContextHolder.getContext().getAuthentication().getName();
			user.setAmigosPendientes(amigosPendientesUser);
			dao.updateUsuario(user);
			return user;
		}

		/**
		 * Get all users
		 */
		public String[] getAmigosPendientes() throws Exception {
			String[] list = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getAmigosPendientes();
			return list;
		}
		
		public List<String> updateAmigosPendientes(List<String> amigos) throws Exception {
			
			Usuario user = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			user.setAmigos((String[]) amigos.toArray());
			dao.updateUsuario(user);
			return amigos;
		}
		public String deleteAmigoPendiente(String id) throws Exception {
			
			Usuario user = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			List<String> amigosPendientes = Arrays.asList(user.getAmigosPendientes());
			amigosPendientes.remove(id);
			user.setAmigos((String[]) amigosPendientes.toArray());
			dao.updateUsuario(user);
			return id;
		}

}
