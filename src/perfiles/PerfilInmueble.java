package perfiles;

import java.util.ArrayList;

import Categorias.Categoria;
import inmueble.Inmueble;
import reservas.Reserva;

public class PerfilInmueble extends Perfil {
	
	private Inmueble inmueble;
	private PerfilPropietario perfilDue�o;
	
	public PerfilInmueble(ArrayList<Categoria> categorias, Inmueble inmueble, PerfilPropietario perfilDue�o) {
		super();
		this.setCategorias(categorias);
		this.inmueble = inmueble;
		this.perfilDue�o = perfilDue�o;
	}
	
	@Override
	public ArrayList<String> getComentarios() {
		ArrayList<String>comentariosDelInmueble = super.getComentarios();
		ArrayList<String>comentariosDelDue�o = this.perfilDue�o.getComentarios();
		comentariosDelInmueble.addAll(comentariosDelDue�o);
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
