package perfiles;

import java.util.ArrayList;
import java.util.List;

import Categorias.Categoria;
import usuario.Usuario;

public class PerfilInquilino extends Perfil {

	private Usuario inquilino;
	private List<Categoria> categorias;
	
	public PerfilInquilino(List<Categoria> categorias, Usuario inquilino) {
		super();
		this.setCategorias(categorias);
		this.inquilino = inquilino;
	}
	
	public String telefonoInquilino() {
		return this.inquilino.getTelefono();
	}
	
	public String mailInquilino() {
		return this.inquilino.getMail();
	}

}
