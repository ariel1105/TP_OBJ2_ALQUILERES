package perfiles;

import java.util.ArrayList;
import java.util.List;

import Categorias.Categoria;
import usuario.Usuario;

public class PerfilInquilino extends Perfil {
	
	
	/**
     * Subclase de la clase perfil, se utilizara para calificar a un inquilino
     */

	private Usuario inquilino;
	private List<Categoria> categorias;
	
	public PerfilInquilino(List<Categoria> categorias, Usuario inquilino) {
		super();
		this.setCategorias(categorias);
		this.inquilino = inquilino;
	}
	/**
     * Constructor de la subclase
     * Se asignara como parametros las categorias y el inquilino
     */
	public String telefonoInquilino() {
		return this.inquilino.getTelefono();
	}
	/**
     * Metodo que retorna el telefono del inquilino del perfil
     */
	public String mailInquilino() {
		return this.inquilino.getMail();
	}
	/**
     * Metodo que retorna el email del inquilino del perfil
     */
}
