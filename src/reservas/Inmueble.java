package reservas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Inmueble {
	
	private Integer capacidad;
	private String ciudad;
	private String pais;
	private Usuario propietario;
	private List<String> servicios;
	private String tipoDeInmueble;
	private Double valorPorDias;
	
	
	public Inmueble(Integer capacidad, String ciudad, String pais, Usuario propietario, List<String> servicios, String tipoDeInmueble, Double valorPorDias) {
		this.setCapacidad(capacidad);
		this.setCiudad(ciudad);
		this.setPais(pais);
		this.setPropietario(propietario);
		this.setServicios(servicios);
		this.setTipoDeInmueble(tipoDeInmueble);
		this.setValorPorDias(valorPorDias);
		
		
	}
	
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

	public String getTipoDeInmueble() {
		return tipoDeInmueble;
	}

	public void setTipoDeInmueble(String tipoDeInmueble) {
		this.tipoDeInmueble = tipoDeInmueble;
	}

	public Double getValorPorDias() {
		return valorPorDias;
	}

	public void setValorPorDias(Double valorPorDias) {
		this.valorPorDias = valorPorDias;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public Double valorPorDias(ArrayList<LocalDate> fechas) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCiudad() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getCapacidad() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double getPrecio() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean estaDisponible(LocalDate of, LocalDate of2) {
		// TODO Auto-generated method stub
		return (Boolean) null;
	}

} 