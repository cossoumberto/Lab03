package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	Set<String> dictionary = new TreeSet<>();
	/**
	 * Popola il dizionario di parole italiane o inglesi a seconda della
	 * scelta dell'utente, utilizzando i file
	 * @param language lingua scelta
	 */
	public void loadDictionary (String language) {
		
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine())!=null) {
					dictionary.add(word);
				}
				br.close();
			} catch (IOException ioe){
				System.out.println("Errore nella lettura del file");
			}
		}
		else if(language.equals("Italinao")) {
			try {
				FileReader fr = new FileReader("Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine())!=null) {
					dictionary.add(word);
				}
				br.close();
			} catch (IOException ioe){
				System.out.println("Errore nella lettura del file");
			}
		}
	}
	
	/**
	 * Controlla se le parole inserite sono corrette 
	 * @param inputTextList lista di parole inserite
	 * @return lista di parola inserite in input, specificate se corrette o no
	 */
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> richWordList = new ArrayList<>();
		
		for(String s : inputTextList) {
			RichWord rw = new RichWord(s);
			if(dictionary.contains(s))
				rw.setCorrect(true);
			richWordList.add(rw);
		}
		
		return richWordList;
	}
}
