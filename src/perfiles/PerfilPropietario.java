package perfiles;

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

	private Usuario propietario;
	private Inmueble inmuebleDePerfil;
	
	public PerfilPropietario(List<Categoria> categoriasDisponibles, Usuario propietario) {
		super();
		this.setCategorias(categoriasDisponibles);
		this.propietario = propietario;
	}
	
	public long tiempoComoUsuario() { 
		return this.propietario.tiempoComoUser();
	}
	
	public List<Inmueble> inmueblesAlquilados() {
		return this.propietario.getInmuebles().stream()
											  .filter(i -> i.estaReservado())
											  .collect(Collectors.toList());
	}
	
	public int cantidadDeAlquilieres() {
		return this.inmueblesAlquilados().stream()
									     .map(i -> i.vecesQueFueAlquilado())
									     .reduce(0, Integer::sum);
	}


}