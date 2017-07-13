package exceções;

public class UJCException extends Exception {
	String usuario;
	
	public UJCException(String usuario) {
		super("Usuário já cadastrado");
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getErrorMessage() {
		return (usuario + ": " + super.getMessage());
	}
	
	
}
