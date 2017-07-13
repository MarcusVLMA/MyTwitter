package sistema;

import java.util.Vector;
import perfis.Perfil;
import tweet.Tweet;
import exceções.*;

public class MyTwitter implements ITwitter {
	IRepositorioUsuario repositorio;
	
	MyTwitter(IRepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}
	
	public void criarPerfil(Perfil usuario) throws PEException {
		try {
			this.repositorio.cadastrar(usuario);
		}
		catch(UJCException ujce) {
			throw new PEException(ujce.getUsuario());
		}
		
	}

	public void cancelarPerfil(String usuario) throws PIException, PDException {
			Perfil perfil = this.repositorio.buscar(usuario); //Precisa ser ponteiro?
			
			if(perfil == null) {
				throw new PIException(usuario);
			} else {
				if(!perfil.isAtivo()) {
					throw new PDException(perfil.getUsuario(), perfil.isAtivo());
				} else {
					perfil.setAtivo(false);
				}
			}
	}

	public void tweetar(String usuario, String mensagem) throws PIException, MFPException {
		Tweet tweet = new Tweet();
		tweet.setMensagem(mensagem);
		tweet.setUsuario(usuario);
		
		Perfil perfil = this.repositorio.buscar(usuario);
		
		if(perfil == null) {
			throw new PIException(usuario);
		} else {
			if(tweet.getMensagem().length() < 1 || tweet.getMensagem().length() > 140) {
				throw new MFPException(tweet.getMensagem());
			} else {
				perfil.getTimeline().addElement(tweet);
				
				for(Perfil seguidor : perfil.getSeguidores()) {
					seguidor.getTimeline().addElement(tweet);
				}
			}
		}
	}

	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		
		if(perfil == null) {
			throw new PIException(usuario);
		} else {
			if(!perfil.isAtivo()) {
				throw new PDException(perfil.getUsuario(), perfil.isAtivo());
			} else {
				return perfil.getTimeline();
			}
		}
	}
	
	public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
		Vector<Tweet> tweets = new Vector<Tweet>();
		Perfil perfil = this.repositorio.buscar(usuario);
		
		if(perfil == null) {
			throw new PIException(usuario);
		} else {
			if(!perfil.isAtivo()) {
				throw new PDException(perfil.getUsuario(), perfil.isAtivo());
			} else {
				for(Tweet tweet : perfil.getTimeline()) {
					if(tweet.getUsuario() == perfil.getUsuario()) {
						tweets.addElement(tweet);
					}
				}
			}
		}
		
		return tweets;
	}

	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
		Perfil perfilSeguidor = this.repositorio.buscar(seguidor);
		Perfil perfilSeguido = this.repositorio.buscar(seguido);
		
		if(perfilSeguidor == null) {
			throw new PIException(seguidor);
		} else {
			if(!perfilSeguidor.isAtivo()) {
				throw new PDException(seguidor, perfilSeguidor.isAtivo());
			} else {
				if(perfilSeguido == null) {
					throw new PIException(seguido);
				} else {
					if(!perfilSeguido.isAtivo()) {
						throw new PDException(seguido, perfilSeguido.isAtivo());
					} else {
						if(seguidor.equals(seguido)) {
							throw new SIException(seguidor);
						} else {
							perfilSeguido.getSeguidores().addElement(perfilSeguidor);
						}
					}
				}
			}
		}
		
	}

	public int numeroSeguidores(String usuario) throws PIException, PDException {
		int numeroSeguidores = 0;
		
		Perfil perfil = this.repositorio.buscar(usuario);
		
		if(perfil == null) {
			throw new PIException(usuario);
		} else {
			if(!perfil.isAtivo()) {
				throw new PDException(usuario, perfil.isAtivo());
			} else {
				for(Perfil seguidor : perfil.getSeguidores()) {
					if(seguidor.isAtivo()) {
						numeroSeguidores++;
					}
				}
				return numeroSeguidores;
			}
		}
	}
	
	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		
		if(perfil == null) {
			throw new PIException(usuario);
		} else {
			if(!perfil.isAtivo()) {
				throw new PDException(usuario, perfil.isAtivo());
			} else {
				Vector<Perfil> seguidores = new Vector<Perfil>();
				
				for(Perfil seguidor : perfil.getSeguidores()) {
					if(seguidor.isAtivo()) {
						seguidores.addElement(seguidor);
					}
				}
				
				return seguidores;
			}
		}
	}

	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		
		if(perfil == null) {
			throw new PIException(usuario);
		} else {
			if(!perfil.isAtivo()) {
				throw new PDException(usuario, perfil.isAtivo());
			} else {
				Vector<Perfil> seguidos = new Vector<Perfil>();
				
				for(Perfil seguido : perfil.getSeguidos()) {
					if(seguido.isAtivo()) {
						seguidos.addElement(seguido);
					}
				}
				
				return seguidos;
			}
		}
	}

}
