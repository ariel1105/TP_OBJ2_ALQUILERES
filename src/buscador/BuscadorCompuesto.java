package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import inmueble.Inmueble;
import sitio.Sitio;

public class BuscadorCompuesto implements IBuscador {
	
	/**
     * Esta clase sera la compuesta del patron que utilizamos (composite) implementando la interfaz IBuscador. Se encargará de realizar la busqueda total de los inmuebles 
     * dependiendo los buscadores que vayamos agregando (parametros de busqueda)
     */
	
	private List <IBuscador> buscadores;	
	

	public BuscadorCompuesto(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida){
		buscadores= new ArrayList<IBuscador>();
		
		if(this.esAdmitida(ciudad, fechaEntrada, fechaSalida)) {

			this.agregarBuscador(new BuscadorCiudad(ciudad));
			this.agregarBuscador(new BuscadorFechas(fechaEntrada, fechaSalida));
		}
		/**
	     * Constructor para el buscador compuesto. Se le pasa como parametro un string(ciudad), dos LocalDate(fecha entrada y salida)
	     * Al tener estos parametros, hago que la busqueda tenga los parametros obligatorios que pide la consigna
	     * 
	     */

		
	}
	
	public void agregarBuscador(IBuscador buscador) {
		// TODO Auto-generated method stub
		this.buscadores.add(buscador);
	}
	/**
     * Método que agrega un nuevo parametro para la busqueda, por ejemplo, un parametro para buscar, ademas de los 3 obligatorios,
     * un inmueble con capacidad de 8 huespedes
     */ 


	public Boolean esAdmitida(String ciudad, LocalDate fechaInicio, LocalDate fechaFin) {
		return (ciudad != null) && (fechaInicio != null) && (fechaFin != null); 
	}
	/**
     * Método que verifica si ninguno de los 3 parametros del constructor sean nulos.
     */ 
	
	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmueblesPublicados){
		
		List<Inmueble> inmueblesFiltrados= new ArrayList<Inmueble>();
		List<Inmueble> inmuebles= inmueblesPublicados;
		
		if (!(buscadores.isEmpty())) {
		
			for (int i=0;i < buscadores.size();i++) {
		
			
				inmueblesFiltrados= (ArrayList<Inmueble>) buscadores.get(i).filtrar(inmuebles);
				inmuebles= inmueblesFiltrados;
			}
		}
	return inmueblesFiltrados;
	}
	
	/**
     * Método recursivo que recorrera la lista de los buscadores y filtrara la busqueda de inmuebles
     */ 

	public List<Inmueble> realizarBusqueda(Sitio sitio) {
		// TODO Auto-generated method stub
		return (this.filtrar(sitio.getInmueblesPublicados()));
	}
	/**
     * Método que realiza la busqueda ingresando el sitio en el cual queremos buscar los inmuebles.
     * Este metodo, llama al metodo filtrar.
     */ 
	
	

	
}
