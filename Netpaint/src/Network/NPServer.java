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
 * <br>Runnable backend program for Netpaint server.<br>
 * Note: NPServer does not have a GUI because it is unnecessary for supporting multiple {@link NetpaintGUI} clients.<br>
 * @author Anthony Rodriguez, Jonathan Snavely
 */
public class NPServer{
	private ServerSocket socket;
	//Map client names to their respective ouputstreams
	//This may not be necessary for our implementation,
	//but it allows support of multiple clients
	//private HashMap<String, ObjectOutputStream> outputs;
	private ArrayList<NPShape> shapes;
	private ArrayList<ObjectOutputStream> connected;

	public NPServer(){
		//outputs = new HashMap<String, ObjectOutputStream>();
		shapes = new ArrayList<NPShape>();
		connected = new ArrayList<ObjectOutputStream>();

		try {
			socket = new ServerSocket(9010);
			System.out.println("Server online. Waiting for clients...");
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
				connected.add(output);
			} catch(Exception e){
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			
			
			try {
				//Waiting for username to be sent
				Command<NPServer> username = (Command<NPServer>)input.readObject();
				username.execute(NPServer.this);
				
				//Initializing the new client with the master shape list
				output.writeObject(new UpdateClientCommand(getCopyMasterList()));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			while(true){
				int removeIdx = -1;
				try {
					//Waiting for new shape to be added
					Command<NPServer> com = (Command<NPServer>)input.readObject();

					//Add a shape to the currently existing list of shapes. Note this will work for beginning username command too.
					com.execute(NPServer.this);

					if (com instanceof DisconnectCommand){
						for(int i=0; i<connected.size(); ++i){
							if (connected.get(i)==output){
								connected.remove(i);
								return;
							}
						}
					}
					
					//Create copy of the new shape list
					ArrayList<NPShape> copy = new ArrayList<NPShape>(shapes.size());
					for(NPShape shape: shapes){
						copy.add(shape);
					}
					
					//Then send the copy to all connected clients
					System.out.println("Server list size: " + shapes.size());
					for(ObjectOutputStream o: connected){
						o.writeObject(new UpdateClientCommand(copy));
					}
					//Write to single client
					//output.writeObject(new UpdateClientCommand(copy));

					//TODO: Check if com is DisconnectCommand Class
					//		if it is, then return
				} catch (Exception e) {
					//If we find corrupted streams, remove them (in lieu of disconnect functionality)
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
					//Wait for new connections, create new handler when new connection connects
					Socket s = socket.accept();
					new Thread(new ClientHandler(s)).start();
//					//outputs.put(clientName, out);	//TODO: Is this necessary?
	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Disconnect from client (TODO)<br>
	 * This is unimplemented because of class instruction.<br>
	 * Leaving it here as a reminder in case we come back to it later.
	 * @param clientName
	 */
	public void disconnect(String clientName){
		//Not implemented
	}
	
	/**
	 * Create copy of the master shape list
	 */
	public ArrayList<NPShape> getCopyMasterList(){
		ArrayList<NPShape> copy = new ArrayList<NPShape>(shapes.size());
		for(NPShape shape: shapes){
			copy.add(shape);
		}
		
		return copy;
	}

	/**
	 * Add a shape to the currently existing list of shapes. This is called upon UpdateClientCommand execute()
	 * @param shape
	 */
	public void update(NPShape shape) {
		System.out.println("Update Shape!");
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
