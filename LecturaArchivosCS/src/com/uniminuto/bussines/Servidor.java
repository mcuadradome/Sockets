package com.uniminuto.bussines;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.uniminuto.commons.ReadFile;
import com.uniminuto.objects.OLinesFile;

public class Servidor {

	private static int PUERTO = 6789;
	static Socket clientsocket;
	static ServerSocket serverSocket;

	public static void main(String args[]) throws IOException {

		System.out.println("Servidor iniciado ");

		while (true) {
			try {

				serverSocket = new ServerSocket(PUERTO);
				clientsocket = serverSocket.accept();
				System.out.println("### Nuevo cliente conectado : " + clientsocket.getPort() + " ###");

				MiServidor s = new MiServidor(clientsocket);
				Thread thread = new Thread(s);
				thread.start();
				System.out.println("Hilos servidor " + thread.currentThread());
			} catch (Exception e) {
				serverSocket.close();
			}

		}

	}

}


