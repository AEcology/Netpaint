package Network;

/**
 * Used to inform server of username associated with a connection.<br>
 * Sent from {@link NetpaintGUI} client to {@link NPServer}
 * @author Anthony Rodriguez, Jonathan Snavely
 *
 */
@SuppressWarnings("serial")
public class AddUsernameCommand extends Command<NPServer>{

	private String username;

	public AddUsernameCommand(String username){
		this.username = username;
	}

	@Override
	void execute(NPServer executeOn) {
		executeOn.updateUsername(username);
	}
}
