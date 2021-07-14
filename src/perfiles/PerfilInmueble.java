package perfiles;

import java.util.ArrayList;
import java.util.List;

import Categorias.Categoria;
import inmueble.Inmueble;
import reservas.Reserva;

public class PerfilInmueble extends Perfil {
	
	private Inmueble inmueble;
	private PerfilPropietario perfilDueño;
	
	public PerfilInmueble(List<Categoria> categorias, Inmueble inmueble, PerfilPropietario perfilDueño) {
		super();
		this.setCategorias(categorias);
		this.inmueble = inmueble;
		this.perfilDueño = perfilDueño;
	}
	
	@Override
	public List<String> getComentarios() {
		List<String>comentariosDelInmueble = super.getComentarios();
		List<String>comentariosDelDueño = this.perfilDueño.getComentarios();
		comentariosDelInmueble.addAll(comentariosDelDueño);
		return comentariosDelInmueble;
	}
	
	public int vecesQueSeAlquiloEstaPropiedad() {
		return this.inmueble.getReservas().size();
	}


}
