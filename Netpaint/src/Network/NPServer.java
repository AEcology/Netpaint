package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Model.Canvas;
import Shapes.NPShape;
import View.NetpaintGUI;


/**
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 * Server functions
 *
 */
public class NPServer extends NetpaintGUI{
		private ServerSocket socket;
		//Map client names to their respective ouputstreams
		//This may not be necessary for our implementation,
		//but it allows support of multiple clients
		private HashMap<String, ObjectOutputStream> outputs;
	
		public NPServer(){
			outputs = new HashMap<String, ObjectOutputStream>();
			
			try {
				socket = new ServerSocket(9010);
				System.out.println("Server online");
				new Thread(new ClientAccepter()).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Threaded class that handles incoming traffic from connected clients<br>
		 * One ClientHandler thread runs in background for each connected client
		 */
		private class ClientHandler implements Runnable{
			private ObjectInputStream input;
			
			public ClientHandler(ObjectInputStream input){
				this.input = input;
			}
			
			@Override
			public void run() {
				while(true){
					try {
						Command<NPServer> com = (Command<NPServer>)input.readObject();
						com.execute(NPServer.this);
						//TODO: Check if com is DisconnectCommand Class
						//		if it is, then return
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
		/**
		 * Threaded responsible for Listening for/handling incoming Client connection requests
		 */
		private class ClientAccepter implements Runnable{
			@Override
			public void run() {
				while(true){
					try {
						Socket s = socket.accept();
						ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());
						String clientName = (String) in.readObject();
						outputs.put(clientName, out);
						new Thread(new ClientAccepter()).start();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}

		/**
		 * Disconnect from client (TODO)
		 * @param clientName
		 */
		public void disconnect(String clientName){
			//TODO
		}
		
		/**
		 * Update Server's canvas with addition to shape list
		 * @param shape
		 */
		public void update(NPShape shape) {
			this.getCanvas().addShape(shape);			
		}
		
		public static void main(String[] args){
			new NPServer();
		}
}
