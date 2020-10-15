package com.uniminuto.bussines;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.uniminuto.commons.ReadFile;
import com.uniminuto.objects.OLinesFile;

public class MiServidor implements Runnable {

	final Socket clientsocket;
//	final ObjectInputStream dis;
//	final ObjectOutputStream dos;

	public MiServidor(Socket clientsocket) {

		this.clientsocket = clientsocket;
	}

	public void run() {

		OLinesFile oLinesFile;
		try {

			ObjectInputStream dis = new ObjectInputStream(clientsocket.getInputStream());
			ObjectOutputStream dos = new ObjectOutputStream(clientsocket.getOutputStream());

			oLinesFile = (OLinesFile) dis.readObject();
			System.out.println("Datos " + oLinesFile.getLinesFile().size());

			// if (oLinesFile != null) {

			ReadFile file = new ReadFile();
			ArrayList<String> detailsFile = file.ShowDetailsFromFile(oLinesFile.getBytes(), oLinesFile.getLinesFile());
			oLinesFile.setDetailsFile(detailsFile);

			for (String string : detailsFile) {
				System.out.println(string);
			}

			dos.writeObject(oLinesFile);
			
			dis.close();
			dos.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error de entrada/salida servidor " + e.getMessage());
		}

	}
}