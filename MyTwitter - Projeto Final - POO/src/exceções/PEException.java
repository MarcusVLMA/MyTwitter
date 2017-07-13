package exce��es;

public class PEException extends Exception {
	String usuario;
	
	public PEException(String usuario) {
		super ("Usuario j� existente");
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getErrorMessage() {
		return (this.usuario + ": " + super.getMessage());
	}
}
