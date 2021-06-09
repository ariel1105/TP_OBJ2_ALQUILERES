package reservas;

import java.util.ArrayList;
import java.util.List;

public abstract class Sitio {
	
	
	//Deje la clase en abstracta para agregar la variable de publicaciones, despues hay que dejar
	private List<Publicacion> publicaciones;

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

	protected abstract boolean compararPublicacion(Busqueda busqueda1, Publicacion publicacion);/// falta implementar esta clase
	


}
