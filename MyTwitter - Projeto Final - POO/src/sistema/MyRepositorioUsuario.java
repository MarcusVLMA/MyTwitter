package sistema;

import perfis.Perfil;
import exceções.*;
import java.util.Vector;

public class MyRepositorioUsuario implements IRepositorioUsuario {
	Vector<Perfil> repositorio;
	
	MyRepositorioUsuario() {
		this.repositorio = new Vector<Perfil>();
	}
	
	public void cadastrar(Perfil usuario) throws UJCException {
		for(Perfil perfil : repositorio) {
			if(perfil.getUsuario().equals(usuario.getUsuario())) {
				throw new UJCException(usuario.getUsuario());
			}
		}
		this.repositorio.addElement(usuario);
	}

	public Perfil buscar(String usuario) {
		for(Perfil perfil : repositorio) {
			if(perfil.getUsuario().equals(usuario)) {
				return perfil;
			}
		}
		return null;
	}

	public void atualizar(Perfil usuario) throws UNCException {
		for(Perfil perfil : repositorio) {
			if(perfil.getUsuario().equals(usuario.getUsuario())) {
				perfil = usuario;
				return;
			}
		}
		throw new UNCException(usuario.getUsuario());
	}
	
}
