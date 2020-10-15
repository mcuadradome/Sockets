package com.uniminuto.bussines;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.uniminuto.commons.ReadCommands;
import com.uniminuto.objects.oResponse;

public class Servidor {

	private static final int PUERTO = 6789;
	static Socket socket;
	static ServerSocket server;

	public static void main(String[] args) throws IOException {

		System.out.println("Servidor iniciado ");
		
		while (true) {
			try {
				server = new ServerSocket(PUERTO);
				socket = server.accept();

				System.out.println("### Nuevo cliente conectado : " + socket.getPort() + " ###");			

				MiServidor miServer = new MiServidor(socket);
				Thread t = new Thread(miServer);
				t.start();
				t.join();
				System.out.println("Assigning new thread for this client " + t.getName());
			} catch (Exception e) {
				server.close();
			}
		}

	}
}

