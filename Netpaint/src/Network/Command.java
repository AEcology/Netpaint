package Network;


import java.io.Serializable;

public abstract class Command<T> implements Serializable {
	private static final long serialVersionUID = -4838155228547508978L;
	
	abstract void execute(T executeOn);
}
