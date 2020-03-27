package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	Set<String> dictionary = new TreeSet<>(); // non utilizzabile per ricerca dicotomica
	//List<String> dictionary = new LinkedList<>();
	//List<String> dictionary = new ArrayList<>();
	/**
	 * Popola il dizionario di parole italiane o inglesi a seconda della
	 * scelta dell'utente, utilizzando i file
	 * @param language lingua scelta
	 */
	public void loadDictionary (String language) {
		
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("C:\\Users\\um_bi\\git\\Lab03\\Lab03_SpellChecker\\src\\main\\resources\\English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine())!=null) {
					dictionary.add(word.toLowerCase());
				}
				br.close();
			} catch (IOException ioe){
				System.out.println("Errore nella lettura del file");
			}
		}
		else if(language.equals("Italiano")) {
			try {
				FileReader fr = new FileReader("C:\\Users\\um_bi\\git\\Lab03\\Lab03_SpellChecker\\src\\main\\resources\\Italian.txt");
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
	
	//PROCEDIMENTO TROPPO LUNGO!
	/*public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		List<RichWord> richWordList = new ArrayList<>();
		List<String> d1 = new ArrayList<>();
		for(String s : dictionary) {
			String ss = new String(s);
			d1.add(ss);
		}
		
		for(String s : inputTextList){
			RichWord rw = new RichWord(s);
			boolean nonTrovato = true;
			while(rw.isCorrect()==false && nonTrovato==true) {
				List<String> d2 = new ArrayList<>();
				if(d1.size()==2) {
					if(d1.get(0).equals(s)) {
						rw.setCorrect(true);
					}
					else if(d1.get(1).equals(s)) {
						rw.setCorrect(true);
					}else
						nonTrovato=false;;
				}else {
					int x = d1.get(d1.size()/2).compareTo(s);
					if(x==0)
						rw.setCorrect(true);
					else if(x<0)
						for(int i=0; i<d1.size()/2; i++)
							d2.add(d1.get(i));
					else if(x>0)
						for(int i=(d1.size()/2)+1; i<d1.size(); i++)
							d2.add(d1.get(i));
					d1.retainAll(d2);
				}
			}
			richWordList.add(rw);
		}
		return richWordList;
		
	}*/
}
