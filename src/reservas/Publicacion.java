package reservas;

import java.util.List;

import politicasDeCancelacion.PoliticaDeCancelacion;

public class Publicacion {
	
	private Inmueble inmuebleAsignado;
	private	List<String> comentarios;
	private List<Categoria> categorias;
	private Integer vecesAlquilado;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Usuario propietario;
	
	public Publicacion(Inmueble inmueble, Usuario propietario) {
		
		this.setInmuebleAsignado(inmueble);
		this.setPropietario(propietario);
		vecesAlquilado=0;
		
		
	}

	public Inmueble getInmuebleAsignado() {
		return inmuebleAsignado;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public void setInmuebleAsignado(Inmueble inmuebleAsignado) {
		this.inmuebleAsignado = inmuebleAsignado;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Integer getVecesAlquilado() {
		return vecesAlquilado;
	}

	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return politicaDeCancelacion;
	}
	
	


}
