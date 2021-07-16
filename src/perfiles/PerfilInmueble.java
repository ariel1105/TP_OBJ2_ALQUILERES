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
	private PerfilPropietario perfilDue�o;
	
	public PerfilInmueble(List<Categoria> categorias, Inmueble inmueble, PerfilPropietario perfilDue�o) {
		super();
		this.setCategorias(categorias);
		this.inmueble = inmueble;
		this.perfilDue�o = perfilDue�o;
	}
	/**
     * Constructor de la subclase
     * Se asignara como parametros las categorias, el inmueble que sera calificado y el perfil del propietario
     */
	
	@Override
	public List<String> getComentarios() {
		List<String>comentariosDelInmueble = super.getComentarios();
		List<String>comentariosDelDue�o = this.perfilDue�o.getComentarios();
		comentariosDelInmueble.addAll(comentariosDelDue�o);
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
