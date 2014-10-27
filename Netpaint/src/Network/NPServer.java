package Network;

import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 * Server functions
 *
 */
public class NPServer {

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
}
