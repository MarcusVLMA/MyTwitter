package exceções;

public class PIException extends Exception {
	String usuario;
	
	public PIException(String usuario) {
		super("Usuário Inexistente");
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getErrorMessage() {
		return (this.usuario + ": " + super.getMessage());
	}
}
