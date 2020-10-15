package com.uniminuto.bussines;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.uniminuto.commons.ReadCommands;
import com.uniminuto.objects.oResponse;

public class MiServidor implements Runnable {

//	final ObjectInputStream input;
//	final ObjectOutputStream output;
	final Socket socket;

	public MiServidor(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		oResponse client;

		try {
			ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());

			client = (oResponse) dis.readObject();
			System.out.println("Comando cliente a servidor " + client.getCommand());

			ReadCommands commands = new ReadCommands();

			ArrayList<String> response = commands.ReadCommand(client.getCommand());
			ArrayList<String> errors = commands.ShowError(client.getCommand());

			client.setResponse(response);
			client.setErrors(errors);

			dos.writeObject(client);
			
			dis.close();
			dos.close();
		} catch (Exception e) {
			System.out.println("Error run servidor " + e.getMessage());
			e.printStackTrace();
		}

	}

}
