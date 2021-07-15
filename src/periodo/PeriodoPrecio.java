package periodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

public class PeriodoPrecio {
	private double precio;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	

	public PeriodoPrecio(double precio, LocalDate fechaInicial, LocalDate fechaFinal) {
		// TODO Auto-generated constructor stub
		this.precio = precio;
		this.fechaInicio= fechaInicial;
		this.fechaFin= fechaFinal;
	}


	public boolean perteneceLaFecha(LocalDate fecha) {

		Stream <LocalDate> fechas;
	    return this.fechaInicio.datesUntil(this.fechaFin).anyMatch(l -> l.equals(fecha))||this.fechaFin.equals(fecha);
		
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}


	public LocalDate getFechaFin() {
		return fechaFin;
	}


	public double getPrecio() {
		// TODO Auto-generated method stub
		return precio;
	}





}
