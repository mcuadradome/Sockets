package com.uniminuto.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class ReadFile {

	
	public ReadFile() {

	}

	public static Map<String, ArrayList<String>> ReadContentFromFile(String path) throws IOException {
		
		Map<String, ArrayList<String>> contendLinesFile = new HashMap<String, ArrayList<String>>();		
		ArrayList<String> lines;
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String st;
			while ((st = br.readLine()) != null) {
				String[] split = st.split(" ");

				for (int i = 0; i < split.length; i++) {
					String string = split[i];
					if(!string.trim().equals("") || !string.trim().equals(".")) {
						if (!contendLinesFile.containsKey(string)) {
							lines = new ArrayList<>();
							lines.add(string);
							contendLinesFile.put(string, lines);
						} else {
							ArrayList<String> listLines = contendLinesFile.get(string);
							listLines.add(string);
							contendLinesFile.replace(string, listLines);
						}
					}
				}

			}
			
			br.close();
		} catch (Exception e) {
			System.err.println("Error al leer archivo " + e.getMessage());
		}
	

		return contendLinesFile;

	}

	public ArrayList<String> ShowDetailsFromFile(String bytes, Map<String, ArrayList<String>> map) {
		ArrayList<String> details = new ArrayList<String>();
		try {
		//	if(!contendLinesFile.isEmpty()) {
			
			//File file = new File(pathD);
			
			details.add("Tamaño " + bytes +" Bytes");
			int contador=1;
			int contador2=1;
			for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
				
				if(entry.getValue().size() == 1) {
					contador++;
				}
				
				details.add("Palabra [" + entry.getKey() + "] esta= " + entry.getValue().size() + " veces");
				contador2 += entry.getValue().size();		    
			}
			
			details.add("Palabras unicas " + contador);
			details.add("Total palabras " + contador2);
			//}

		} catch (Exception e) {
			System.err.println("Error al leer archivo" + e.getMessage());
		}
		return details;
	}

}
