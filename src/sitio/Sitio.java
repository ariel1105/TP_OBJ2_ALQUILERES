package sitio;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import usuario.Usuario;

public class Sitio {
	private ArrayList<Usuario> usuariosRegistrados;
	
	public Sitio() {
		this.usuariosRegistrados = new ArrayList<Usuario>();
	}

	public ArrayList<Usuario> obtenerUsuariosRegistrados() {
		// TODO Auto-generated method stub
		return usuariosRegistrados;
	}

	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		if(! this.elUsuarioEstaRegistrado(usuario)) {
			usuariosRegistrados.add(usuario);
		}
	}

	public boolean elUsuarioEstaRegistrado(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuariosRegistrados.contains(usuario);
	}

}
