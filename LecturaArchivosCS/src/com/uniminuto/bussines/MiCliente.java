package com.uniminuto.bussines;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.uniminuto.commons.ReadFile;
import com.uniminuto.objects.OLinesFile;

public class MiCliente implements Runnable {

	Socket clientSocket;
	ObjectInputStream input;
	ObjectOutputStream data;
	OLinesFile oLinesFile;

	public MiCliente(Socket clientSocket, OLinesFile oLinesFile) {

		this.clientSocket = clientSocket;
		this.oLinesFile = oLinesFile;
	}


	@Override
	public void run() {
		try {

			ObjectOutputStream data = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Carga lineas");

			data.writeObject(oLinesFile);

			OLinesFile response = (OLinesFile) input.readObject();
			
			//System.out.println("lee objeto");
			ArrayList<String> dataResponse = response.getDetailsFile();
			
			for (String string : dataResponse) {
				System.out.println(string);
			}
			data.flush();

			data.close();
			input.close();
		} catch (Exception e) {
			System.out.println("Error de Envio mensaje a servidor " + e.getMessage());
		}
	}

}
