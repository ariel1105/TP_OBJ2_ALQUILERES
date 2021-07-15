package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorPrecioMinimoYMaximo implements IBuscador {
	
	/**
     * Esta clase sera una de las "hojas" de nuestro patron compuesto, implementando la interfaz IBuscador. 
     * Se encargara de buscar inmuebles segun el precio minimo y maximo
     */

	private Double precioMinimoP;
	private Double precioMaximoP;
	
	public BuscadorPrecioMinimoYMaximo(Double precioMinimo, Double precioMaximo) {
		// TODO Auto-generated constructor stub
		
		precioMinimoP= precioMinimo;
		precioMaximoP= precioMaximo;
	}
	/**
     * Constructor para la clase, se le asignaran dos parametros double que seran los precioMinimo y Maximo para filtrar un inmueble
     * 
     */
	
	@Override
	public ArrayList<Inmueble> filtrar(List<Inmueble> inmuebles) {
		// TODO Auto-generated method stub
		return (ArrayList<Inmueble>) inmuebles.stream().
				filter(inmueble -> inmueble.getPrecioActual() >= precioMinimoP && inmueble.getPrecioActual() < precioMaximoP ).
				collect(Collectors.toList());
	
	}
	/**
     * Método que retorna los inmuebles que tengan un precio entre los dos que se asignaron en el constructor
     */ 
}
