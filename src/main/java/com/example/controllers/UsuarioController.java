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

public class UsuarioController {

	// Singleton instances
	private UsuarioDAO dao;
	private static UsuarioController singleton;

	private UsuarioController() throws Exception {
		dao = UsuarioDAO.getInstance();
	}

	public static UsuarioController getInstance() throws Exception {
		if (singleton == null) {
			singleton = new UsuarioController();
		}
		return singleton;
	}

	/**
	 * Check user/password and return the role
	 */
	public String loginUser(String idUser, String pass) throws Exception {
		// Get the user hashed and salted password
		Usuario user = dao.getUser(idUser);
		if (user == null) {
			throw new Exception("User not found");
		}
		String hashedAndSalted = user.getPassword();
		String salt = hashedAndSalted.split(",")[1];
		String newHashedAndSalted = makePasswordHash(pass, salt);
		// Takes both hashed and salted passwords and compare it
		if (!hashedAndSalted.equals(newHashedAndSalted)) {
			throw new Exception("Password wrong");
		}
		// If its all right return the role
		return user.getRole();
	}

	/**
	 * Create new user
	 */
	public Usuario createUsuario(Usuario resource) throws Exception {
		
		resource.setPassword(this.makePasswordHash(resource.getPassword(), this.generateSalting()));
		dao.createUsuario(resource);
		return resource;
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
			resource.setPassword(this.makePasswordHash(resource.getPassword(), this.generateSalting()));
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
	

	// PRIVATE METHODS TO GENERATE PASSWORDS

	/**
	 * Generate a Hash for given Password.
	 */
	private String makePasswordHash(String password, String salt) throws Exception {

		String saltedAndHashed = password + "," + salt;
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(saltedAndHashed.getBytes("UTF-8"));
		Base64 encoder = new Base64();
		byte[] hashedBytes = (new String(digest.digest(), "UTF-8")).getBytes("UTF-8");
		return new String(encoder.encode(hashedBytes), "UTF-8") + "," + salt;
	}

	/**
	 * Generate a random salting.
	 */
	private String generateSalting() {
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	public void aniadirImagen(Map<String,Object> datosImagen) throws Exception {
		
		String imagen = (String) datosImagen.get("imagen");
		boolean perfil = (boolean) datosImagen.get("perfil");
		Calendar fecha = Calendar.getInstance();
		Date actual = new Date(fecha.getTimeInMillis());
		String nFichero = null;
		try{
			
			byte[] imagenByte = DatatypeConverter.parseBase64Binary(imagen);
			//guardamos en disco la imagen usando como nombre el id de su referenciaSecurityContextHolder.getContext().getAuthentication().getName()
			File directorio = new File(Config.getInstance().getProperty(Config.PATH_IMAGENES)+"/"+SecurityContextHolder.getContext().getAuthentication().getName());
			directorio.mkdirs();
			File archivo = null;
			if(perfil){
				archivo = new File(Config.getInstance().getProperty(Config.PATH_IMAGENES)+"/"+SecurityContextHolder.getContext().getAuthentication().getName()+"/perfil.png");
			}
			else{
				nFichero = actual.toString().replaceAll(":", "-");
				archivo = new File(Config.getInstance().getProperty(Config.PATH_IMAGENES)+"/"+SecurityContextHolder.getContext().getAuthentication().getName()+"/"+nFichero+".png");	
			}
			FileUtils.writeByteArrayToFile(archivo,imagenByte);
			
		}
		catch(Exception e){
			
			throw new Exception("Error en la escritura de la imagen:"+e.getMessage());
		}
	
			try{
			
				Usuario modListImagenes = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
				String[] imagenes = modListImagenes.getFotos();
				List<String> listImagenes = Arrays.asList(imagenes);
				if(!listImagenes.contains("perfil")){
					imagenes[imagenes.length] = nFichero;
					modListImagenes.setFotos(imagenes);
					dao.updateUsuario(modListImagenes);
				}
			
			}catch(Exception e){
			
			throw new Exception("Error al guardar la imagen en disco: "+ e.getMessage());
			}
		System.out.println("chupipupipopi");
		
		
	}
	public String[] cogerImagenes() throws IOException {
		
		Usuario modListImagenes = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		String[] imagenes = modListImagenes.getFotos();
		for(int i=0;i<imagenes.length;i++){
			
			byte[] imagenByte = null;
			try{
			imagenByte = Files.readAllBytes(Paths.get(Config.getInstance().getProperty(Config.PATH_IMAGENES)+imagenes[i]+".png"));
			}catch(Exception e){
			imagenByte = Files.readAllBytes(Paths.get(Config.getInstance().getProperty(Config.PATH_IMAGENES)+"error.png"));	
			}
			imagenes[i] = DatatypeConverter.printBase64Binary(imagenByte);
			
		}
		
		return imagenes;
		
	}
	
	public void borrarImagen(String key) throws Exception {
		
		try{
			
			Usuario modListImagenes = dao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			String[] imagenes = modListImagenes.getFotos();
			List<String> listImagenes = Arrays.asList(imagenes);
			listImagenes.remove(key);
			imagenes = (String[]) listImagenes.toArray();
			modListImagenes.setFotos(imagenes);
			dao.updateUsuario(modListImagenes);
			
		}catch(Exception e){
			
			throw new Exception("Error al borrar la imagen en disco: "+ e.getMessage());
		}
		
		try{
			
		File archivo = new File(Config.getInstance().getProperty(Config.PATH_IMAGENES)+"/"+SecurityContextHolder.getContext().getAuthentication().getName()+"/"+key+".png");
		if(!archivo.delete()){
			throw new Exception("La imagen no puede ser borrada");
		}
		
		}catch(Exception e){
			
			throw new Exception("Error al eliminar la imagen: "+ e.getMessage());
		}
		
		
	}

}
