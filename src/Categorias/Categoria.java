package Categorias;

public class Categoria {
	
	private Integer puntaje;

	public Categoria() {
		this.puntaje = 0;
		
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public void sumarPuntos(int puntos) {
		this.puntaje = this.puntaje + puntos;
	}

}
