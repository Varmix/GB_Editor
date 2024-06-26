package org.helmo.gbeditor.infrastructures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.helmo.gbeditor.infrastructures.Mapper.Mapping;
import org.helmo.gbeditor.infrastructures.dto.BookDTO;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.repositories.GameBookRepository;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


public class JSONrepository implements GameBookRepository {
	
	
	private static String currentUsersHomeDir = System.getProperty("user.home");
	private static Path dossier = Paths.get(currentUsersHomeDir, "ue36");
	private static Path jsonFile = Paths.get(dossier.toString(), "q210054.json");
	private Mapping mapper = new Mapping();
	private List<BookDTO> bookDTOlist = new ArrayList<BookDTO>();
	//private File folder = new File(dossier.toString());
	

	/**
	 * Constructeur vide
	 */
	public JSONrepository() {
		
	}
	/**
	 * Permet de créer le dossier UE36 s'il n'existe pas
	 */
	private void createFolder() {
		try {
			if(!Files.exists(dossier)) {
				Files.createDirectories(dossier);
			}
		} catch (IOException e) {
			System.out.println("Error while creating a folder" + e.getMessage());
		}
	}
	
//	private void createFolder() {
//	if(!folder.exists()) {
//		folder.mkdirs();
//	}
//}
	
//	private void createJsonFile() {
//		try {
//			if(!Files.exists(jsonFile)) {
//				Files.createFile(jsonFile);
//			}
//		} catch (IOException e) {
//			System.out.println("Error while creating a json file" + e.getMessage());
//		}
//	}
	
	/**
	 * Permet d'enregistrer un book dans un fichier json
	 * @param book reçoit un livre qui se voit transformer en DtoBook.
	 */
	@Override
	public void save(Book book) {
		//Transformation de mon book en bookDTO
		BookDTO bookDTO = mapper.toBookDto(book);
		//Création du fichier s'il n'existe pas
		createFolder();
		//Création du fichier Json s'il n'existe pas
		//createJsonFile();
		//Ecriture du bookDTO dans le Json
		try(BufferedWriter bufferedWriter = Files.newBufferedWriter(jsonFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
			//load les données dans le fichier s'il existe.
			load();
			//Ajouter mon livre DTO à ma liste DTO
			bookDTOlist.add(bookDTO);
			//créer une instance de Gson
			Gson gson = new Gson();
			
			//convertir mon bookDTO objet au fichier json
			gson.toJson(bookDTOlist, bufferedWriter);
		} catch (IOException e) {
			System.out.println("Error writing data to json file" + e.getMessage());
		}
	}
	
	/**
	 * Permet d'aller lire la liste de livres contenue dans mon fichier JSON
	 */
	@Override
	public void load() {
		try(BufferedReader reader = Files.newBufferedReader(jsonFile, StandardCharsets.UTF_8)) {
			
			Gson gson = new Gson();
			
			List<BookDTO> listBookFromJson = gson.fromJson(reader, new TypeToken<ArrayList<BookDTO>>() {
			}.getType());
			if (listBookFromJson != null) {
				bookDTOlist.clear();
				bookDTOlist.addAll(listBookFromJson);
			}
			
		} catch (IOException e) {
			System.out.println("Error reading data from JSON file" + e.getMessage());
		}
	}
}
