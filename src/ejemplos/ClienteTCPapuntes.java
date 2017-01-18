package ejemplos;


import java.io.*;
import java.net.*;

class ClienteTCPapuntes {
	static final String HOST = "localhost";
	static final int PUERTO = 6003;

	public ClienteTCPapuntes() {
		try {
			Socket skCliente = new Socket(HOST, PUERTO);
			// recepción de datos del servidor
			InputStream aux = skCliente.getInputStream();
			DataInputStream flujo = new DataInputStream(aux);
			System.out.println(flujo.readUTF());
			skCliente.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		new ClienteTCPapuntes();
	}
}