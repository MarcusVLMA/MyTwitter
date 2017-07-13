package perfis;
import java.util.Vector;
import tweet.Tweet;

public abstract class Perfil {
	private Vector<Perfil> seguidos;
	private Vector<Perfil> seguidores;
	private Vector<Tweet> timeline;
	private String usuario;
	private boolean ativo;
	
	Perfil(String usuario) {
		this.usuario = usuario;
		seguidos = new Vector<Perfil>();
		seguidores = new Vector<Perfil>();
		timeline = new Vector<Tweet>();
		ativo = true;
	}
	
	public void addSeguido(Perfil seguido) {
		this.seguidos.addElement(seguido);
	}
	
	public void addSeguidor(Perfil seguidor) {
		this.seguidores.addElement(seguidor);
	}
	
	public void addTweet(Tweet tweet) {
		this.timeline.addElement(tweet);
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public Vector<Perfil> getSeguidos() {
		return this.seguidos;
	}
	
	public Vector<Perfil> getSeguidores() {
		return this.seguidores;
	}
	
	public Vector<Tweet> getTimeline() {
		return this.timeline;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public boolean isAtivo() {
		return this.ativo;
	}
}
