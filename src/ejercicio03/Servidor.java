package ejercicio03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor  {
	
	// Variables de clase
	private static Socket socket_cli=null;
	private static ServerSocket socket;
	
	
	public static void main(String args[]){

			// Declaramos un bloque try y catch para controlar la ejecución del subprograma
			 try {
				 // Instanciamos un ServerSocket con la dirección del destino y el
				 // puerto que vamos a utilizar para la comunicación
				 socket = new ServerSocket(8000);
				 // Tiempo de espera
				 socket.setSoTimeout(4000000);
				 System.out.println("Esperando conexiones");
				 
				 // Se crean los hilos para los clientes
				 for(int i=0; i<5; i++){
					 crearNuevoHilo();
				 }
				 
			 
				
			 }
			// utilizamos el catch para capturar los errores que puedan surgir
			catch (Exception e) {
				// si existen errores los mostrará en la consola y después saldrá del programa
				 System.err.println(e.getMessage());
				 System.out.println("Has superado el número de conexiones");
				 System.exit(1);
			 }
		 
	}
	
	
	
	private static void crearNuevoHilo(){
		Thread hilo = new Thread (new Runnable() {
			
			@Override
			public void run() {
				try{
					Socket skCliente = socket.accept(); // Crea objeto
					System.out.println("Sirvo al cliente");
					// envío de datos al cliente
					OutputStream aux = skCliente.getOutputStream();
					DataOutputStream flujo = new DataOutputStream(aux);
					flujo.writeUTF("Hola cliente");
					//skCliente.close();
				
					
					// Declaramos e instanciamos el objeto DataInputStream que nos valdrá para recibir datos del cliente
					 DataInputStream in = new DataInputStream(skCliente.getInputStream());
					 
					// Creamos un bucle do while en el que recogemos el mensaje
					// que nos ha enviado el cliente y después lo mostramos
					// por consola
					 
					aux = skCliente.getOutputStream();
					flujo = new DataOutputStream(aux);
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
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		});
		hilo.start();
	}

}
