package perfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import Categorias.Categoria;

public abstract class Perfil {
	/**
     * Clase abstracta utilizada para calificar a un inmueble, propietario o inquilino
     */
	private List <String> comentarios;
	private Map <Categoria, Integer> popularidad;
	private List<Categoria> categoriasDisponibles;
	
	public Perfil() {
		this.comentarios = new ArrayList<String>();
		this.popularidad = new HashMap <Categoria, Integer>();
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categoriasDisponibles = categorias;
	}
	/**
     * Metodo que setea las categorias para calificar
     */
	public void setPopularidad() {
		Stream<Categoria>categorias = this.categoriasDisponibles.stream();
		categorias.forEach(cat -> this.popularidad.put(cat, 0));;
	}
	/**
     * Metodo que califica la popularidad de las categorias
     */
	public List<String> getComentarios() {
		return this.comentarios;
	}
	/**
     * Metodo que retorna los comentarios del perfil
     */
	public List<Categoria> getCategorias() {
		return this.categoriasDisponibles;
	}
	/**
     * Metodo que retorna las categorias calificables del perfil
     */
	public Map<Categoria, Integer> getPopularidad() {
		return this.popularidad;
	}
	/**
     * Metodo que retorna un map con una categoria y su respectiva popularidad
     */
	public void recibirPuntuacion(Categoria cat, int puntos) {
		if(this.existeCategoria(cat)) {
			cat.sumarPuntos(puntos);
			Integer puntosSumados = popularidad.get(cat)+1;
			this.popularidad.put(cat, puntosSumados);
		}
	}
	/**
     * Metodo que suma puntos en cierta categoria dada como parametro
     */
	public Boolean existeCategoria(Categoria cat1) {
		return this.categoriasDisponibles.contains(cat1);
	}
	/**
     * Metodo que verifica si existe una categoria dada como parametro
     */
	public Double promedioPorCat(Categoria categoria) {
		Double puntosCategoria = (double) (categoria.getPuntaje().intValue());
		Double cantPuntuaciones = (double) (this.popularidad.get(categoria));
		return puntosCategoria / cantPuntuaciones; 
	}
	/**
     * Metodo que retorna el promedio en cierta categoria dada como parametro
     */
	public Double promedioTotal() {

		Stream <Integer>stream = this.popularidad.keySet().stream().map(cat -> cat.getPuntaje());
		Integer total = stream.reduce(0, Integer::sum);
		Integer cantPuntuaciones = this.popularidad.values().stream().reduce(0, Integer::sum);
		return (double) (total / cantPuntuaciones);
		
	}
	/**
     * Metodo que retorna el promedio total de todas las categorias
     */

	public void recibirComentarios(String comentario) {
		this.comentarios.add(comentario);
	}
	/**
     * Metodo que agrega un comentario a la lista de comentarios del perfil
     */
}
