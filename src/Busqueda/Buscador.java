package Busqueda;

import java.util.ArrayList;
import java.util.List;

import reservas.Publicacion;

public abstract class Buscador {

	public List<Publicacion> busquedasCoincidentes(Busqueda busqueda, List<Publicacion> publicaciones) {
		// TODO Auto-generated method stub
		
		List <Publicacion> publicacionesConCoincidencia= new ArrayList<Publicacion>();
		
			for (int i=0; i < publicaciones.size(); i++) {
				
				if (this.compararPublicacion(busqueda ,publicaciones.get(i))) {
					
					publicacionesConCoincidencia.add(publicaciones.get(i));
				}
				
			}
	
			return publicacionesConCoincidencia;
	}
	
	 public abstract boolean compararPublicacion(Busqueda busqueda, Publicacion publicacion);

	
}
