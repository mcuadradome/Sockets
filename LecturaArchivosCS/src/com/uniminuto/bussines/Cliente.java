package com.uniminuto.bussines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.uniminuto.commons.ReadFile;
import com.uniminuto.objects.OLinesFile;

public class Cliente {

	private static int PUERTO = 6789;
	private static String HOST = "localhost";
	static Socket clientSocket;


	public static void main(String args[]) {

		try {
			
		
		Socket clientSocket = null;	
		BufferedReader messageServer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingreso Texto para el servidor: ");
		String mensajeToServer = messageServer.readLine();
		clientSocket = new Socket(HOST, PUERTO);
		System.out.println("### conexion exitosa hacia servidor, puerto " + clientSocket.getPort() + " host "
				+ clientSocket.getLocalAddress() + ". ###");
		
		while (!mensajeToServer.equals("")) {
						
		
			File rfile = new File(mensajeToServer);
			//System.out.println("File " + rfile.length());
			if (rfile.exists()) {
				OLinesFile oLinesFile;
				
				Map<String, ArrayList<String>> contendLinesFile;
				if(rfile.length() > 10000) {
														
							
					int cont=0;
					//int aux=0;
					
					contendLinesFile =ReadFile.ReadContentFromFile(mensajeToServer);
					
				
				//	int linesFromFile= linesFile.size()/10;
					//System.out.println(linesFile.size() + " Lines From file" + linesFromFile);
					
//					for (Map.Entry<String, ArrayList<String>> entry : linesFile.entrySet()) {
//						
//						contendLinesFile.put(entry.getKey(), entry.getValue());
//						cont++;
//						
//						if(cont== linesFromFile) {
//							
//							System.out.println("Entro if");
//							cont=0;
//							oLinesFile = new OLinesFile();
//							
//							clientSocket = new Socket(HOST, PUERTO);
					        oLinesFile = new OLinesFile();
							
							
							oLinesFile.setBytes(String.valueOf(rfile.length()));
							oLinesFile.setLinesFile(contendLinesFile);
							oLinesFile.setPath(mensajeToServer);

							MiCliente miCliente = new MiCliente(clientSocket, oLinesFile);
							Thread t = new Thread(miCliente);
							t.start();		
							//System.out.println("Thread Cliente" + t.currentThread());
							t.join();
							
						
					//	}
					//}
	
					//	System.out.println("Aux " + aux  + " cont " + cont);
						
				}else {
					
					contendLinesFile = ReadFile.ReadContentFromFile(mensajeToServer);
				
					oLinesFile = new OLinesFile();
					
					oLinesFile.setBytes(String.valueOf(rfile.length()));
					oLinesFile.setLinesFile(contendLinesFile);
					oLinesFile.setPath(mensajeToServer);
												
				
					MiCliente miCliente = new MiCliente(clientSocket, oLinesFile);
					Thread t = new Thread(miCliente);
					t.start();
					t.join();
					
					
				}
				
				
			}
		
			messageServer = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Ingreso Texto para el servidor: ");
			mensajeToServer = messageServer.readLine();
			
		}
		
		
			clientSocket.close();
			messageServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}

