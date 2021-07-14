package perfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import Categorias.Categoria;

public abstract class Perfil {
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
	
	public void setPopularidad() {
		Stream<Categoria>categorias = this.categoriasDisponibles.stream();
		categorias.forEach(cat -> this.popularidad.put(cat, 0));;
	}
	
	public List<String> getComentarios() {
		return this.comentarios;
	}

	public List<Categoria> getCategorias() {
		return this.categoriasDisponibles;
	}

	public Map<Categoria, Integer> getPopularidad() {
		return this.popularidad;
	}

	public void recibirPuntuacion(Categoria cat, int puntos) {
		if(this.existeCategoria(cat)) {
			cat.sumarPuntos(puntos);
			Integer puntosSumados = popularidad.get(cat)+1;
			this.popularidad.put(cat, puntosSumados);
		}
	}

	public Boolean existeCategoria(Categoria cat1) {
		return this.categoriasDisponibles.contains(cat1);
	}

	public Double promedioPorCat(Categoria categoria) {
		Double puntosCategoria = (double) (categoria.getPuntaje().intValue());
		Double cantPuntuaciones = (double) (this.popularidad.get(categoria));
		return puntosCategoria / cantPuntuaciones; 
	}

	public Double promedioTotal() {
		// Double puntosTotales = 0d;
		// Double cantPuntuaciones = 0d;
		/*for (Categoria cat : this.popularidad.keySet()) {
			puntosTotales = puntosTotales + cat.getPuntaje();
			cantPuntuaciones = cantPuntuaciones + this.popularidad.get(cat);
		}
		return puntosTotales/cantPuntuaciones;*/
		Stream <Integer>stream = this.popularidad.keySet().stream().map(cat -> cat.getPuntaje());
		Integer total = stream.reduce(0, Integer::sum);
		Integer cantPuntuaciones = this.popularidad.values().stream().reduce(0, Integer::sum);
		return (double) (total / cantPuntuaciones);
		
	}

	public void recibirComentarios(String comentario) {
		this.comentarios.add(comentario);
	}
}
