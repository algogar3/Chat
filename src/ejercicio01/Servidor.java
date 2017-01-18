package ejercicio01;

// Se importan las librerias necesarias
import java.net.*;
import java.io.*;

 
// Declaramos la clase Servidor
public class Servidor{
	
	// Variables de clase
	private static Socket socket_cli;
 
	// Método principal main de la clase
	public static void main(String argv[]) {
 	 
		// declaramos un objeto ServerSocket para realizar la comunicación
		ServerSocket socket;
 

	 
		// Declaramos un bloque try y catch para controlar la ejecución del subprograma
		 try {
			 // Instanciamos un ServerSocket con la dirección del destino y el
			 // puerto que vamos a utilizar para la comunicación
			 socket = new ServerSocket(8000);
			 // Tiempo de espera
			 socket.setSoTimeout(40000);
			 System.out.println("Esperando conexiones");
		 
			 // Abrimos 5 puertas para 5 posibles conexiones de cliente
			 for (int numCli = 0; numCli < 5; numCli++) {
				Socket skCliente = socket.accept(); // Crea objeto
				System.out.println("Sirvo al cliente " + numCli);
				// envío de datos al cliente
				OutputStream aux = skCliente.getOutputStream();
				DataOutputStream flujo = new DataOutputStream(aux);
				flujo.writeUTF("Hola cliente " + numCli);
				skCliente.close();
			}
		 
		
			// Declaramos e instanciamos el objeto DataInputStream que nos valdrá para recibir datos del cliente
			 DataInputStream in = new DataInputStream(socket_cli.getInputStream());
			 
			// Creamos un bucle do while en el que recogemos el mensaje
			// que nos ha enviado el cliente y después lo mostramos
			// por consola
			 
			OutputStream aux = socket_cli.getOutputStream();
			DataOutputStream flujo = new DataOutputStream(aux);
			BufferedReader ent = new BufferedReader(new InputStreamReader(System.in));
				 
			do {
				//mostrar mensajes del cliente
				String mensaje ="";
				mensaje = in.readUTF();
				System.out.println(mensaje);
			 
				//lectura de texto
				flujo.writeUTF(ent.readLine());
			 } while (1>0);
		 }
		// utilizamos el catch para capturar los errores que puedan surgir
		catch (Exception e) {
			// si existen errores los mostrará en la consola y después saldrá del programa
			 System.err.println(e.getMessage());
			 System.out.println("Has superado el número de conexiones");
			 System.exit(1);
		 }
	 }
}
