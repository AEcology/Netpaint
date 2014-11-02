//package Network;
//
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import java.util.ArrayList;
//
//import Shapes.NPShape;
//import View.NetpaintGUI;
//
//public class NPClient extends NetpaintGUI{
//	NetpaintGUI gui;
//	
//	
//	public NPClient(){
//		//Layout the GUI
//		super();
//		
//		//Set up separate thread for handling input from server
//		new Thread(new ServerHandler()).start();
//	}
//	
//	
//	/**
//	 * Relay shape array to model once Client recieves update Command from server.
//	 * @param shapes
//	 */
//	public void update(ArrayList<NPShape> shapes) {
//		this.getCanvas().update(shapes);
//	}
//
//
//	/**
//	 * Handles command objects received from the server.
//	 */
//	private class ServerHandler implements Runnable{
//		@Override
//		public void run() {
//			try{
//				//Server connect
//				Socket socket = new Socket(IPAddress, Integer.parseInt(port));
//				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
//				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
//				Command<NPServer> command;
//				
//				//Write username to server TODO: this might need to be send as Command object
//				output.writeObject(username);
//				
//				while(true){
//					command = (Command<NPClient>)input.readObject();
//					command.execute(NetpaintGUI.this);	//TODO: not sure how to get instance of server since it is running a
//				}
//					
//			} catch(Exception e){
//				e.printStackTrace();
//			}		
//		}	
//	}
//	
//	public static void main(String[] args){
//		new NPClient();
//	}
//}
//
//
