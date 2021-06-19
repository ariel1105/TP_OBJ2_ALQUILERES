package perfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Categoria;
import usuario.Usuario;

public class PerfilPropietario extends Perfil {

	private Usuario dueño;
	private Inmueble inmuebleDePerfil;
	
	public PerfilPropietario(ArrayList<Categoria> categoriasDisponibles, Usuario dueño) {
		super();
		this.setCategorias(categoriasDisponibles);
		this.dueño = dueño;
	}
	
	public int tiempoComoUsuario() { 
		return this.dueño.tiempoComoUsuario();
	}
	
	
	public Set<Inmueble> inmueblesAlquilados() {
		Set<Inmueble> inmuebles = new HashSet();
		ArrayList<Reserva> reservas = this.dueño.getReservasConfirmadas();
		for (Reserva reserva : reservas) {
			inmuebles.add(reserva.getInmueble());
		}
		return inmuebles;
	}
	
	public int cantidadDeAlquilieres() {
		return this.dueño.getReservasConfirmadas().size();
	}


}