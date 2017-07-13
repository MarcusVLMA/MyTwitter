package exceções;

public class SIException extends Exception {
	String usuario;
	
	public SIException(String usuario) {
		super("Usuário não pode seguir a si mesmo");
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getErrorMessage() {
		return (this.usuario + ": " + super.getMessage());
	}
}
