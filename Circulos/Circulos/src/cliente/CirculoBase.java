package cliente;

public abstract class CirculoBase{
	protected String id;
	protected int limite;
	
	public CirculoBase(String id, int limite){
		this.id = id;
		this.limite = limite;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CirculoBase){
			CirculoBase other = (CirculoBase) obj;
			return (this.id.equals(other.id) && 
				   (this.limite == other.limite));
		}
		return false;
	}
	
	
	
	public void setLimite(int limite) {
		this.limite = limite;
	}

	public String getId() {
		return id;
	}

	public int getLimite() {
		return limite;
	}

	public abstract int getNumberOfContacts();
}