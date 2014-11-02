package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import Shapes.NPShape;


/**
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 * Server functions
 *
 */
public class NPServer{
	private ServerSocket socket;
	//Map client names to their respective ouputstreams
	//This may not be necessary for our implementation,
	//but it allows support of multiple clients
	private HashMap<String, ObjectOutputStream> outputs;
	private ArrayList<NPShape> shapes;

	public NPServer(){
		outputs = new HashMap<String, ObjectOutputStream>();
		shapes = new ArrayList<NPShape>();

		try {
			socket = new ServerSocket(9010);
			System.out.println("Server online");
			new Thread(new ClientAccepter()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Threaded class that handles incoming and outgoing traffic from connected clients<br>
	 * One ClientHandler thread runs in background for each connected client
	 */
	private class ClientHandler implements Runnable{
		private Socket client;
		private ObjectOutputStream output;
		private ObjectInputStream input;

		public ClientHandler(Socket client){
			this.client = client;		
			try{
				output = new ObjectOutputStream(client.getOutputStream());
				input = new ObjectInputStream(client.getInputStream());						
			} catch(Exception e){
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while(true){
				try {
					Command<NPServer> com = (Command<NPServer>)input.readObject();

					//Add a shape to the currently existing list of shapes. Note this will work for beginning username command too.
					com.execute(NPServer.this);

					//Then send the newly updated list of shapes to all connected clients
					output.writeObject(new UpdateClientCommand(shapes));

					//TODO: Check if com is DisconnectCommand Class
					//		if it is, then return
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
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
					new Thread(new ClientHandler(s)).start();	
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
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
	 * Add a shape to the currently existing list of shapes. This is called upon UpdateClientCommand execute()
	 * @param shape
	 */
	public void update(NPShape shape) {
		shapes.add(shape);			
	}

	/**
	 * This is called upon AddUsernameCommand execute(). For some reason the specs say we need to send username command
	 * @param username
	 */
	public void updateUsername(String username) {
		//TODO: Add this username to Hashmap (if its even necessary, we could probably just keep a list of users instead)
		System.out.println(username + " logged into NetPaint!");
	}		

	public static void main(String[] args){
		new NPServer();
	}
}
