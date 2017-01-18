package ejemplos;

import java.io.*;
import java.net.*;

class ServidorTCPapuntes {
	static final int PUERTO = 6003;
	private ServerSocket skServidor;

	public ServidorTCPapuntes() {
		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("Escucho el puerto " + PUERTO);
			for (int numCli = 0; numCli < 3; numCli++) {
				Socket skCliente = skServidor.accept(); // Crea objeto
				System.out.println("Sirvo al cliente " + numCli);
				// envío de datos al cliente
				OutputStream aux = skCliente.getOutputStream();
				DataOutputStream flujo = new DataOutputStream(aux);
				flujo.writeUTF("Hola cliente " + numCli);
				skCliente.close();
			}
			System.out.println("Demasiados clientes por hoy");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		new ServidorTCPapuntes();
	}
}