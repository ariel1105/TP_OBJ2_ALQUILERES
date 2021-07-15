package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorHuespedes implements IBuscador {
	
	/**
     * Esta clase sera una de las "hojas" de nuestro patron compuesto, implementando la interfaz IBuscador. 
     * Se encargara de buscar inmuebles segun la cantidad de huespedes
     */
	
	private Integer cantidadHuespedes;
	
	public BuscadorHuespedes(Integer cantidad) {
		
		cantidadHuespedes= cantidad;
	}

	/**
     * Constructor para la clase, se le asignara un parametro que sera la cantidad de huespedes utilizado para filtrar los inmuebles
     * 
     */

	@Override
	public ArrayList<Inmueble> filtrar(List<Inmueble> inmuebles) {
		// TODO Auto-generated method stub

		
	
	return (ArrayList<Inmueble>) inmuebles.stream().
			filter(inmueble -> inmueble.getCapacidad() >= cantidadHuespedes).
			collect(Collectors.toList());
	}
	/**
     * Método que retorna los inmuebles que tengan igual o mas capacidad que la cantidad de huespedes que se indica en el constructor
     */ 
}
