package perfiles;

import java.util.ArrayList;
import java.util.List;

import Categorias.Categoria;
import inmueble.Inmueble;
import reservas.Reserva;

public class PerfilInmueble extends Perfil {
	
	private Inmueble inmueble;
	private PerfilPropietario perfilDue�o;
	
	public PerfilInmueble(List<Categoria> categorias, Inmueble inmueble, PerfilPropietario perfilDue�o) {
		super();
		this.setCategorias(categorias);
		this.inmueble = inmueble;
		this.perfilDue�o = perfilDue�o;
	}
	
	@Override
	public List<String> getComentarios() {
		List<String>comentariosDelInmueble = super.getComentarios();
		List<String>comentariosDelDue�o = this.perfilDue�o.getComentarios();
		comentariosDelInmueble.addAll(comentariosDelDue�o);
		return comentariosDelInmueble;
	}
	
	public int vecesQueSeAlquiloEstaPropiedad() {
		return this.inmueble.getReservas().size();
	}


}
