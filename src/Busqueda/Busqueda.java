package Busqueda;

import java.time.LocalDate;

public class Busqueda {
	
	private String ciudad;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Integer huespedes;
	private Double precioMinimo;
	private Double precioMaximo;
	

	public Busqueda(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, Integer huespedes, double precioMinimo, double precioMaximo) {
		// TODO Auto-generated constructor stub
		this.setCiudad(ciudad);
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida);
		this.setHuespedes(huespedes);
		this.setPrecioMaximo(precioMaximo);
		this.setPrecioMinimo(precioMinimo);
		
	}
	
	public Busqueda(String ciudad, LocalDate fechaDeEntrada, LocalDate fechaSalida) {
		this.setCiudad(ciudad);
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida);
		
		
	}
	

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getHuespedes() {
		return huespedes;
	}

	public void setHuespedes(Integer huespedes) {
		this.huespedes = huespedes;
	}

	public Double getPrecioMinimo() {
		return precioMinimo;
	}

	public void setPrecioMinimo(Double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public Double getPrecioMaximo() {
		return precioMaximo;
	}

	public void setPrecioMaximo(Double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	

}
