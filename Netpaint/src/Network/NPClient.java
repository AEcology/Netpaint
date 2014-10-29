package Network;

import java.util.ArrayList;

import Shapes.NPShape;
import View.NetpaintGUI;

public class NPClient extends NetpaintGUI{

	
	/**
	 * Relay shape array to model once Client recieves update Command from server.
	 * @param shapes
	 */
	public void update(ArrayList<NPShape> shapes) {
		this.getCanvas().update(shapes);
	}

	public static void main(String[] args){
		new NPClient();
	}
}
