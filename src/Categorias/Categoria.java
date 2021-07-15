package Categorias;

public class Categoria {
	
	/**
     * Esta clase se utilizara para calificar una categoria y saber su nombre
     */
	
	private Integer puntaje;

	public Categoria() {
		this.puntaje = 0;
		
	}
	/**
     * Constructor de la clase
     * No tiene parametros, solamente setea el puntaje en 0
     */
	public Integer getPuntaje() {
		return puntaje;
	}
	/**
     * Metodo que retorna el puntaje de la categoria
     */
	public void sumarPuntos(int puntos) {
		this.puntaje = this.puntaje + puntos;
	}
	/**
     * Metodo que suma puntos a la categoria
     */
}
