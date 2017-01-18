package ejemplos;

import java.io.*;
import java.net.*;
import java.util.Date;

public class Servidor_uno {
	public static void main(String[] args) throws IOException {
		// el puerto de escucha del servidor será el 8050
		int PUERTO = 8100;

		byte msg[] = new byte[1024];

		// Creamos el socket UDP del servidor en el pueto asociado

		try {

			DatagramSocket s = new DatagramSocket(PUERTO);
			System.out.println("Servidor Activo");
			s.setSoTimeout(30000);

			// implementacion del protocolo del servidor en un bucle infinito
			while (true) {
				DatagramPacket recibido = new DatagramPacket(new byte[1024], 1024);

				// llega un datagrama
				s.receive(recibido);
				System.out.println("Ha llegado una peticion \n");
				System.out.println("Procedente de :" + recibido.getAddress());
				System.out.println("Mensaje recibido " + new String(recibido.getData()));
				System.out.println("Sirviendo la petición");

				// se prepara el mensaje a enviar con la fecha del sistema
				String message = new String("HORA DEL SERVIDOR " + new Date().toString());
				msg = message.getBytes();

				// se crea el datagrama que contendrá al mensaje
				DatagramPacket paquete = new DatagramPacket(msg, msg.length, recibido.getAddress(), recibido.getPort());

				// se le envia al cliente
				s.send(paquete);
			}

		} catch (SocketTimeoutException e) {

			// SocketTimeoutException e
			System.err.println("30 segs sin recibir nada");
		}
	}
}
