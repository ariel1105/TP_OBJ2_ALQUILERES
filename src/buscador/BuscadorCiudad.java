package buscador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorCiudad implements IBuscador {
	
	/**
     * Esta clase sera una de las "hojas" de nuestro patron compuesto, implementando la interfaz IBuscador. 
     * Se encargara de buscar inmuebles segun la ciudad
     */
	
	private String ciudad;
	
	public BuscadorCiudad(String ciudad) {
		// TODO Auto-generated constructor stub
		this.ciudad= ciudad;
	}
	/**
     * Constructor para la clase, se le asignara un parametro string que sera la ciudad para filtrar los inmuebles
     * 
     */

	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		// TODO Auto-generated method stub
		return  inmuebles.stream().
				filter(inmueble -> inmueble.getCiudad().equals(ciudad)).
				collect(Collectors.toList());
	}
	/**
     * Método que retorna los inmuebles que se encuentren en la ciudad que se encuentra en el constructor
     */ 
}
