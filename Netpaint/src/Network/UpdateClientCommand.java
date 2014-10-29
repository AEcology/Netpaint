package Network;

import java.util.ArrayList;

import Shapes.NPShape;

/**
 * 
 * @author Anthony Rodriguez
 *	Created whenever server object list changes. Execute on client, replaces list of objects in GUI with list sent from server
 */
public class UpdateClientCommand extends Command<NPClient>{
	
	private ArrayList<NPShape> shapes;
	
	public UpdateClientCommand(ArrayList<NPShape> newShapeArry){
		this.shapes = newShapeArry;
	}
	
	@Override
	void execute(NPClient executeOn) {
		executeOn.update(shapes);		
	}

}
