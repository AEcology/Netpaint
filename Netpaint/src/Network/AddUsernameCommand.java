package Network;

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
