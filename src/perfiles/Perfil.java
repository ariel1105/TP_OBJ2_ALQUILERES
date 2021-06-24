package perfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Categorias.Categoria;

public abstract class Perfil {
	private ArrayList <String> comentarios;
	private Map <Categoria, Integer> popularidad;
	private ArrayList<Categoria> categoriasDisponibles;
	
	public Perfil() {
		this.comentarios = new ArrayList<String>();
		this.popularidad = new HashMap <Categoria, Integer>();
	}
	
	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categoriasDisponibles = categorias;
	}
	
	public void setPopularidad() {
		ArrayList <Categoria>categorias = this.categoriasDisponibles;
		for (int i = 0; i < categorias.size(); i++) {
			this.popularidad.put(categorias.get(i), 0);
		}
	}
	
	public ArrayList<String> getComentarios() {
		return this.comentarios;
	}

	public ArrayList<Categoria> getCategorias() {
		return this.categoriasDisponibles;
	}

	public Map<Categoria, Integer> getPopularidad() {
		return this.popularidad;
	}

	public void recibirPuntuacion(Categoria cat1, int puntos) {
		if(this.existeCategoria(cat1)) {
			cat1.sumarPuntos(puntos);
			Integer puntosSumados = popularidad.get(cat1)+1;
			this.popularidad.put(cat1, puntosSumados);
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
		Double puntosTotales = 0d;
		Double cantPuntuaciones = 0d;
		for (Categoria cat : this.popularidad.keySet()) {
			puntosTotales = puntosTotales + cat.getPuntaje();
			cantPuntuaciones = cantPuntuaciones + this.popularidad.get(cat);
		}
		return puntosTotales/cantPuntuaciones;
	}

	public void recibirComentarios(String comentario) {
		this.comentarios.add(comentario);
	}
}
