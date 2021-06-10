package reservas;

import java.util.ArrayList;
import java.util.List;

public abstract class Sitio {
	
	
	//Deje la clase en abstracta para agregar la variable de publicaciones, despues hay que dejar
	private List<Publicacion> publicaciones;
	private List<Usuario> usuariosRegistrados;
	


	public Sitio() {
		
		publicaciones= new ArrayList<Publicacion>();
		usuariosRegistrados= new ArrayList <Usuario>();
		
	}
 

	protected abstract void agegarReserva(Reserva r);

	protected abstract void enviarMailDeConfirmacion(Reserva reserva);

	public List<Publicacion> busquedaDeInmuebles(Busqueda busqueda1){
		
		List <Publicacion> publicacionesConCoincidencia= new ArrayList<Publicacion>();
		
			for (int i=0; i < publicaciones.size(); i++) {
				
				if (this.compararPublicacion(busqueda1,publicaciones.get(i))) {
					
					publicacionesConCoincidencia.add(publicaciones.get(i));
				}
				
			}
	
			return publicacionesConCoincidencia;
	}

	protected  boolean compararPublicacion(Busqueda busqueda1, Publicacion publicacion) { //faltan crear los getters en inmueble
		
		return((publicacion.getInmuebleAsignado().getCiudad() == busqueda1.getCiudad() 
						
				&& true && true) /*como comparo las fechas de entrada y salida con el inmueble? para pensar  */ 
							
					|| publicacion.getInmuebleAsignado().getCapacidad() >= busqueda1.getHuespedes()
										
						||	(publicacion.getInmuebleAsignado().getPrecio() >= busqueda1.getPrecioMinimo() && publicacion.getInmuebleAsignado().getPrecio() <= busqueda1.getPrecioMaximo()))
		
	}/// falta implementar esta clase

	protected void altaDeInmueble(Inmueble cabaña, Usuario propietario) {
		
		if (this.getUsuariosRegistrados().contains(propietario)) {
		
		Publicacion publicacionParaElInmueble= new Publicacion(cabaña, propietario);
		
		this.publicaciones.add(publicacionParaElInmueble);
		}
	}
	
	
	//Getters
	
	public List<Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}
	
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	
	

}
