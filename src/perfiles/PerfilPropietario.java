package perfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Categoria;
import usuario.Usuario;

public class PerfilPropietario extends Perfil {

	private Usuario due�o;
	private Inmueble inmuebleDePerfil;
	
	public PerfilPropietario(ArrayList<Categoria> categoriasDisponibles, Usuario due�o) {
		super();
		this.setCategorias(categoriasDisponibles);
		this.due�o = due�o;
	}
	
	public int tiempoComoUsuario() { 
		return this.due�o.tiempoComoUsuario();
	}
	
	
	public Set<Inmueble> inmueblesAlquilados() {
		Set<Inmueble> inmuebles = new HashSet();
		ArrayList<Reserva> reservas = this.due�o.getReservasConfirmadas();
		for (Reserva reserva : reservas) {
			inmuebles.add(reserva.getInmueble());
		}
		return inmuebles;
	}
	
	public int cantidadDeAlquilieres() {
		return this.due�o.getReservasConfirmadas().size();
	}


}