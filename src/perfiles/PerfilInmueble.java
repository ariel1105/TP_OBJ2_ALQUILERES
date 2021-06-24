package perfiles;

import java.util.ArrayList;

import Categorias.Categoria;
import inmueble.Inmueble;
import reservas.Reserva;

public class PerfilInmueble extends Perfil {
	
	private Inmueble inmueble;
	private PerfilPropietario perfilDueño;
	
	public PerfilInmueble(ArrayList<Categoria> categorias, Inmueble inmueble, PerfilPropietario perfilDueño) {
		super();
		this.setCategorias(categorias);
		this.inmueble = inmueble;
		this.perfilDueño = perfilDueño;
	}
	
	@Override
	public ArrayList<String> getComentarios() {
		ArrayList<String>comentariosDelInmueble = super.getComentarios();
		ArrayList<String>comentariosDelDueño = this.perfilDueño.getComentarios();
		comentariosDelInmueble.addAll(comentariosDelDueño);
		return comentariosDelInmueble;
	}
	
	public int vecesQueSeAlquiloEstaPropiedad() {
		int cantidad = 0;
		ArrayList<Reserva> reservas = this.inmueble.getPropietario().getReservasConfirmadas();
		for (Reserva reserva : reservas) {
			if (reserva.getInmueble().equals(this.inmueble)) {
				cantidad++;
			}
		}
		return cantidad;
	}


}
