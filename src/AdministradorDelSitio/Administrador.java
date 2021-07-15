package AdministradorDelSitio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Categorias.Categoria;
import inmueble.Inmueble;
import sitio.Sitio;
import usuario.Usuario;

public class Administrador  {
	/**
     * Esta clase se utilizará realizar acciones de administrador en el sitio como dar de alta categorias, agregar tipos de inmuebles y servicios validos.
     * Obtener listado de los inquilinos que mas alquilaron, los inmuebles libres y la tasa de inmuebles reservados.
     */

	public void darDeAltaCategoriaParaInmueble(Sitio sitio, Categoria categoria) {
		// TODO Auto-generated method stub
		sitio.altaDeCategoriaInmueble( categoria);
		
	}
	/**
     * Método que agrega una nueva categoria de calificacion para los inmuebles en el sitio.
     */  
	public void darDeAltaCategoriaParaPropietario(Sitio sitio, Categoria categoria) {
		// TODO Auto-generated method stub
		sitio.altaDeCategoriaPropietario(categoria);
	}
	/**
     * Método que agrega una nueva categoria de calificacion para los propietarios en el sitio.
     */
	public void darDeAltaCategoriaParaInquilino(Sitio sitio, Categoria categoria) {
		// TODO Auto-generated method stub
		sitio.altaDeCategoriaInquilino(categoria);
	}
	/**
     * Método que agrega una nueva categoria de calificacion para los inquilinos en el sitio.
     */
	public void darDeAltaTipoDeInmueble(Sitio sitio, String tipo) {
		// TODO Auto-generated method stub
		sitio.altaDeTipoInmueble(tipo);
	}
	/**
     * Método que agrega un nuevo tipo de inmueble permitido en el sitio.
     */
	public void darDeAltaServicio(Sitio sitio, String servicio) {
		// TODO Auto-generated method stub
		sitio.altaServicio(servicio);
		
	}
	/**
     * Método que agrega un nuevo servicio de inmueble permitido en el sitio.
     */

	public List<Usuario> topTenInquilinos(Sitio sitio) {
		// TODO Auto-generated method stub
		List <Usuario> usuarios= this.usuariosRank(sitio);
		List<Usuario> topTen= new ArrayList<Usuario>();
		
		for(int i=0; i<usuarios.size(); i++) {
			
			if(i<10) {
			
			topTen.add(usuarios.get(i));
			}
		}
		
		return topTen;
	}
	/**
     * Método que devuelve una lista de los 10 inquilinos que mas veces alquilaron en forma ascendente dentro de un sitio
     */
	
	public List<Usuario> usuariosRank(Sitio sitio ) {
		
		List<Usuario> users=this.usuariosQueAlquilaron(sitio);
		
		Collections.sort(users, new CompararUsuarios());
		
		return users;
		
}
	/**
     * Método que devuelve una lista de todos los inquilinos ordenados en forma ascendente con respecto a las veces que alquilaron
     */
	
	
	public List<Usuario> usuariosQueAlquilaron(Sitio sitio) { 
		// TODO Auto-generated method stub
		
		List <Usuario> usuarios = new ArrayList<Usuario>();
		for (int i=0; i< sitio.obtenerUsuariosRegistrados().size(); i++) {
			if (sitio.obtenerUsuariosRegistrados().get(i).vecesQueAlquilaron() > 0) {
				usuarios.add(sitio.obtenerUsuariosRegistrados().get(i));
			}
		}
		return  usuarios;
	}
	
	/**
     * Método que devuelve una lista de todos los inquilinos de un sitio
     */


	public List <Inmueble> inmueblesLibres(Sitio sitio) {
		// TODO Auto-generated method stub
		
		List <Inmueble> inmuebles= sitio.getInmueblesPublicados();
		List <Inmueble> inmueblesLibres= new ArrayList<Inmueble>();
		for (int i=0; i < inmuebles.size(); i++){
			
			if (!inmuebles.get(i).estaReservado()) {
				
				inmueblesLibres.add(inmuebles.get(i));
				
			}
		}
		return inmueblesLibres;
	}
	/**
     * Método que devuelve una lista de todos los inmuebles libres de un sitio
     */
	public Double tasaOcupacion(Sitio sitio) {
		// TODO Auto-generated method stub
		int cantInmuebles= sitio.getInmueblesPublicados().size();
		return (double) (cantInmuebles / inmueblesReservados(sitio));
		
		
	}
	/**
     * Método que devuelve la tasa de ocupacion de los inmuebles de un sitio
     */
	public Integer inmueblesReservados(Sitio sitio) {
		List <Inmueble> inmuebles= sitio.getInmueblesPublicados();
		int cantidadDeInmueblesAlquilados= 0;
		for (int i=0; i < inmuebles.size(); i++){
			
			if (inmuebles.get(i).estaReservado()) {
				
				cantidadDeInmueblesAlquilados++;
				
			}
		}
		return cantidadDeInmueblesAlquilados;
	

		
	}
	/**
     * Método que devuelve la cantidad de inmueblesReservados de un sitio
     */

}
