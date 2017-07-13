package perfis;

public class PessoaJuridica extends Perfil {
	private long cnpj;
	
	public PessoaJuridica(String usuario, long cnpj) {
		super(usuario);
		this.cnpj = cnpj;
	}
	
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	
	public long getCnpj() {
		return this.cnpj;
	}
}
