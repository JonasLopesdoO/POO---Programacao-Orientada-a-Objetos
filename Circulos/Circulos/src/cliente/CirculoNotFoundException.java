package cliente;

public class CirculoNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7331861696548215622L;

	public CirculoNotFoundException(String id) {
		super("Circulo " +  id + " nao foi encontrado");
	}

}
