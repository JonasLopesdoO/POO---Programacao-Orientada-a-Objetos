package cliente;

public class ContatoNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1600335032524758018L;

	public ContatoNotFoundException(String id){
		super("Cliente " + id + " nao encotrado");
	}

}
