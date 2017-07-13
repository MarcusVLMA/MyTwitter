package exce��es;

public class PDException extends Exception {
	String usuario;
	boolean ativo;
	
	public PDException(String usuario, boolean ativo) {
		super("Usu�rio Inativo");
		this.usuario = usuario;
		this.ativo = ativo;
	}
	
	public boolean isAtivo() {
		return this.ativo;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public String getErrorMessage() {
		return (this.usuario + ": " + super.getMessage());
	}
}
