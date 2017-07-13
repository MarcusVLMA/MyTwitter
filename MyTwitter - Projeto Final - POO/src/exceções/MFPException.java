package exceções;

public class MFPException extends Exception {
	String mensagem;
	
	public MFPException(String mensagem) {
		super("Seu tweet deve ter entre 1 e 140 caracteres");
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
	
	public String getErrorMessage() {
		return super.getMessage();
	}
}
