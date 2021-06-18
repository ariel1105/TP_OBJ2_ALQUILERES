package perfiles;

import java.util.ArrayList;

import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Categoria;

public class PerfilInmueble extends Perfil {
	
	private Inmueble inmueble;
	private PerfilDueño perfilDueño;
	
	public PerfilInmueble(ArrayList<Categoria> categorias, Inmueble inmueble, PerfilDueño perfilDueño) {
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
		ArrayList<Reserva> reservas = this.inmueble.getDueño().getReservasConfirmadas();
		for (Reserva reserva : reservas) {
			if (reserva.getInmueble().equals(this.inmueble)) {
				cantidad++;
			}
		}
		return cantidad;
	}


}
