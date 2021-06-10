package reservas;

import java.util.List;

import politicasDeCancelacion.PoliticaDeCancelacion;

public class Publicacion {
	
	private Inmueble inmuebleAsignado;
	private	List<String> comentarios;
	private List<Categoria> categorias;
	private Integer vecesAlquilado;
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	public Publicacion(Inmueble inmueble) {
		
		this.setInmuebleAsignado(inmueble);
		
		
	}

	public Inmueble getInmuebleAsignado() {
		return inmuebleAsignado;
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
