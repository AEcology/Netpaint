package Network;

import Shapes.NPShape;

/**
 * 
 * @author Anthony Rodriguez, Jonathan Snavely
 * Created when user draws shape on client canvas.
 * When executed on server, adds object to list of all objects on canvas
 */
public class AddObjectCommand extends Command<NPServer>{

	private NPShape shape;
	
	public AddObjectCommand(NPShape newShape){
		this.shape = newShape;
	}
	
	@Override
	void execute(NPServer executeOn) {
		// TODO Auto-generated method stub
		executeOn.update(shape);
	}



}
