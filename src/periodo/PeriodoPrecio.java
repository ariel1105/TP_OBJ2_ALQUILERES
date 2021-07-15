package periodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

public class PeriodoPrecio {
	
	/**
     * Esta clase se utiliza para saber diferentes cualidades de los periodos de un inmueble, tanto como el precio y
     * si alguna fecha pertenece entre el rango de inicio y fin
     * 
     */
	private double precio;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	

	public PeriodoPrecio(double precio, LocalDate fechaInicial, LocalDate fechaFinal) {
		// TODO Auto-generated constructor stub
		this.precio = precio;
		this.fechaInicio= fechaInicial;
		this.fechaFin= fechaFinal;
	}
	/**
     * Constructor para la clase. 
     * Se le pasa como parametro un double que es el precio, y dos fechas que son la fecha inicial y la final del periodo
     * 
     */

	public boolean perteneceLaFecha(LocalDate fecha) {

		
	    return this.fechaInicio.datesUntil(this.fechaFin).anyMatch(l -> l.equals(fecha))||this.fechaFin.equals(fecha);
		
	}
	/**
     * Metodo que retorna si una fecha se encuentra dentro de la de inicio y la de fin
     */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	/**
     * Metodo que retorna la fecha de inicio del periodo
     */

	public LocalDate getFechaFin() {
		return fechaFin;
	}
	/**
     * Metodo que retorna la fecha de fin del periodo
     */


	public double getPrecio() {
		// TODO Auto-generated method stub
		return precio;
	}
	/**
     * Metodo que retorna el precio del periodo
     */




}
