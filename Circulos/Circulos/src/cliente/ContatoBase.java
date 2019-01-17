package cliente;

public abstract class ContatoBase{
	public String id, email;

	public ContatoBase(String id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ContatoBase){
			ContatoBase other = (ContatoBase) obj;
			return (this.id.equals(other.id) && 
					this.email.equals(other.email));
		}
		return false;
	}
}
