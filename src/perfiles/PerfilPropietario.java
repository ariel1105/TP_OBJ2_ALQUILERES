package perfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Categorias.Categoria;
import inmueble.Inmueble;
import reservas.Reserva;
import usuario.Usuario;

public class PerfilPropietario extends Perfil {
	
	/**
     * Subclase de la clase perfil, se utilizara para calificar a un propietario
     */

	private Usuario propietario;
	private Inmueble inmuebleDePerfil;
	
	public PerfilPropietario(List<Categoria> categoriasDisponibles, Usuario propietario) {
		super();
		this.setCategorias(categoriasDisponibles);
		this.propietario = propietario;
	}
	/**
     * Constructor de la subclase
     * Se asignara como parametros las categorias y el propietario
     */
	public int tiempoComoUsuario(LocalDate fechaActual) { 
		return this.propietario.tiempoComoUser(fechaActual);
	}
	/**
     * Metodo que retorna el tiempo como usuario que lleva registrado en el sitio
     */
	public List<Inmueble> inmueblesAlquilados() {
		return this.propietario.getInmuebles().stream()
											  .filter(i -> i.estaReservado())
											  .collect(Collectors.toList());
	}
	/**
     * Metodo que retorna los inmuebles que ya alquiló
     */
	public int cantidadDeAlquilieres() {
		return this.inmueblesAlquilados().stream()
									     .map(i -> i.vecesQueFueAlquilado())
									     .reduce(0, Integer::sum);
	}
	/**
     * Metodo que retorna la cantidad de alquileres totales que realizó
     */

}