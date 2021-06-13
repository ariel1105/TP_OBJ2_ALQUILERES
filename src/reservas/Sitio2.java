package reservas;

import java.util.ArrayList;
import java.util.List;

import Busqueda.Buscador;
import Busqueda.Busqueda;
import usuario.Usuario;

public abstract class Sitio2 {
	
	
	//Deje la clase en abstracta para agregar la variable de publicaciones, despues hay que dejar
	private List<Publicacion> publicaciones;
	private List<Usuario> usuariosRegistrados;
	private Buscador buscadorActual;


	public Sitio2() {
		
		publicaciones= new ArrayList<Publicacion>();
		usuariosRegistrados= new ArrayList <Usuario>();
		
	}
	
	public void setBuscador(Buscador buscadorAsignado) {
		
		buscadorActual= buscadorAsignado;
		
	}
	

	public void agregarPublicacion(Publicacion publicacion) { ////!!!!!!!!!Este metodo lo deje para testear, cuando ya este todo implementado se borra!!!!!
		
		this.publicaciones.add(publicacion);
	}
 
 
	protected abstract void agegarReserva(Reserva r);

	protected abstract void enviarMailDeConfirmacion(Reserva reserva);
	

	public List<Publicacion> busquedaDeInmuebles(Busqueda busqueda1){
		

	
			return this.buscadorActual.busquedasCoincidentes(busqueda1, publicaciones);
	}




	
	
	//Getters
	
	public List<Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}
	
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	
	public Buscador getBuscadorActual() {
		return buscadorActual;
	}

}
