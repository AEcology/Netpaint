package Network;


/**
 * Prompt server to close the associated stream
 * @author Anthony
 *
 */
public class DisconnectCommand extends Command<NPServer>{
	String clientName;
	
	public DisconnectCommand(String name){
		clientName = name;
	}
	
	@Override
	void execute(NPServer executeOn) {
		System.out.println(clientName + " has disconnected");
	}

}
