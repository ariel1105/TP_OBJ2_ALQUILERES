package sitio;

public class Categoria {
	
	private String nombreCategoria;
	private Integer puntaje;

	public Categoria(String nombre, Integer puntaje) {

		this.setNombreCategoria(nombre);
		this.setPuntaje(puntaje);

	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public Integer getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}

}
