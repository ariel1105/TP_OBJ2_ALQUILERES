package perfiles;

import java.util.ArrayList;
import java.util.List;

import Categorias.Categoria;
import inmueble.Inmueble;
import reservas.Reserva;

public class PerfilInmueble extends Perfil {
	
	/**
     * Subclase de la clase perfil, se utilizara para calificar a un inmueble
     */
	
	private Inmueble inmueble;
	private PerfilPropietario perfilDueño;
	
	public PerfilInmueble(List<Categoria> categorias, Inmueble inmueble, PerfilPropietario perfilDueño) {
		super();
		this.setCategorias(categorias);
		this.inmueble = inmueble;
		this.perfilDueño = perfilDueño;
	}
	/**
     * Constructor de la subclase
     * Se asignara como parametros las categorias, el inmueble que sera calificado y el perfil del propietario
     */
	
	@Override
	public List<String> getComentarios() {
		List<String>comentariosDelInmueble = super.getComentarios();
		List<String>comentariosDelDueño = this.perfilDueño.getComentarios();
		comentariosDelInmueble.addAll(comentariosDelDueño);
		return comentariosDelInmueble;
	}
	/**
     * Metodo que retorna los comentarios del perfil del inmueble
     */
	public int vecesQueSeAlquiloEstaPropiedad() {
		return this.inmueble.getReservas().size();
	}
	/**
     * Metodo que retorna la cantidad de veces que se alquilo el inmueble del perfil
     */

}
