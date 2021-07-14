package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class Busqueda {

	private String ciudad;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;				
	private Integer CantHuespedes;
	private Double precioMaximo;
	private Double precioMinimo;
	

	public Boolean esAdmitida() {
		return (this.ciudad != null) && (this.fechaInicio != null) && (this.fechaFin != null); 
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}

	public void setCantHuespedes(int cantidad) {
		this.CantHuespedes = cantidad;
	}

	public Integer getCantHuespedes() {
		return this.CantHuespedes;
	}

	public void setPrecioMaximo(double precio) {
		this.precioMaximo = precio;
	}

	public Double getPrecioMaximo() {
		return this.precioMaximo;
	}

	public void setPrecioMinimo(double precio) {
		this.precioMinimo = precio;
	}

	public Double getPrecioMinimo() {
		return this.precioMinimo;
	}

	public void setParametosObligatorios(String ciudad, LocalDate fechaInicio, LocalDate fechaFin) {
		this.ciudad = ciudad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	

}
