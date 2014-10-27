package Network;

import java.util.ArrayList;

import Shapes.NPShape;
import View.NetpaintGUI;

public class NPClient {
	private NetpaintGUI model;
	
	public void update(ArrayList<NPShape> shapes) {
		model.update(shapes);
	}

	public static void main(String[] args){
		new NetpaintGUI();
	}
}
