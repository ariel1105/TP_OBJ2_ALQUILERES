package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorFechas implements IBuscador {
	
	/**
     * Esta clase sera una de las "hojas" de nuestro patron compuesto, implementando la interfaz IBuscador. 
     * Se encargara de buscar inmuebles segun la fecha de entrada y de salida
     */
	
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	

	public BuscadorFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
		// TODO Auto-generated constructor stub
		
		this.fechaEntrada= fechaEntrada;
		this.fechaSalida = fechaSalida;
	}
	/**
     * Constructor para la clase, se le asignaran dos parametros LocalDate que seran las fechas utilizadas para filtrar los inmuebles
     * 
     */

	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmuebles) {
		// TODO Auto-generated method stub
		
		return  inmuebles.stream().
				filter(inmueble -> inmueble.estaDisponible(fechaEntrada, fechaSalida)).
				collect(Collectors.toList());
	}
	/**
     * Método que retorna los inmuebles que se encuentren entre esas dos fechas que se indican en el constructor
     */ 
}
