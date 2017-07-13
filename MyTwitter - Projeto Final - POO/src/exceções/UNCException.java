package exceções;

public class UNCException extends Exception {
	String usuario;
	
	public UNCException(String usuario) {
		super("Usuário não cadastrado");
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getErrorMessage() {
		return (this.usuario + ": " + super.getMessage());
	}
}
