package com.uniminuto.bussines;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente {

	private static int PUERTO = 6789;
	private static String HOST = "localhost";
	Socket clientSocket;

	public static void main(String[] args) {

		Socket clientSocket = null;

		try {
			Scanner scn = new Scanner(System.in);		
			String tosend;
			do {
				System.out.println("Ingrese comando ");
				tosend = scn.nextLine();
				if (!tosend.equals("")) {
					
					clientSocket = new Socket(HOST, PUERTO);
										
					MiCliente miServer = new MiCliente(clientSocket, tosend);
					Thread t = new Thread(miServer);
					t.start();
					t.join();
				} else {
					System.out.println("Aplicacion finalizada");
					scn.close();
					clientSocket.close();
				}
			} while (!tosend.equals(""));

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


