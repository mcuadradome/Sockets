package com.uniminuto.commons;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadCommands {

	public ArrayList<String> ReadCommand(String command) {
		ArrayList<String> response = new ArrayList<>();

		try {

			Process process = new ProcessBuilder("cmd.exe", "/c", command).start();
			process.waitFor();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				response.add(line);
			}

			is.close();
			isr.close();
			br.close();

		} catch (Exception e) {
			System.err.println("Error al leer comando " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	public ArrayList<String> ShowError(String command) {
		ArrayList<String> response = new ArrayList<>();
		try {

			Process process = new ProcessBuilder("cmd.exe", "/c", command).start();
			process.waitFor();
			
			InputStream is = process.getErrorStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				response.add(line);
			}

			is.close();
			isr.close();
			br.close();

		} catch (Exception e) {
			System.err.println("Error al leer errores " + e.getMessage());
		}
		return response;
	}

}
