package com.uniminuto.bussines;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.uniminuto.objects.oResponse;

public class MiCliente  implements Runnable {

		Socket socket;
		ObjectInputStream input;
		ObjectOutputStream data;
		String command;

		public MiCliente(Socket socket, String comand) {

			this.socket = socket;
			this.command = comand;
		}

		@Override
		public void run() {
		
		//	String toreturn;

			try {
							
			    data = new ObjectOutputStream(socket.getOutputStream());
			    input = new ObjectInputStream(socket.getInputStream());
			    
				oResponse received = new oResponse();
				
				received.setCommand(command);			
				
				data.writeObject(received);
				
				
				received = (oResponse) input.readObject();

				ArrayList<String> dataResponse = received.getResponse();
				if (!dataResponse.isEmpty()) {
					for (String string : dataResponse) {
						System.out.println(string);
					}
				} else {
					ArrayList<String> errorList = received.getErrors();
					for (String string : errorList) {
						System.out.println(string);
					}

				}
			} catch (IOException | ClassNotFoundException e) {		
				System.out.println("Error run cliente " + e.getMessage());
				e.printStackTrace();
			}

		}

	}
