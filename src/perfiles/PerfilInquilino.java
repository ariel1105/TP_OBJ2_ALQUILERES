package perfiles;

import java.util.ArrayList;

import sitio.Categoria;
import usuario.Usuario;

public class PerfilInquilino extends Perfil {

	private Usuario inquilino;
	private ArrayList<Categoria> categorias;
	
	public PerfilInquilino(ArrayList<Categoria> categorias, Usuario inquilino) {
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
