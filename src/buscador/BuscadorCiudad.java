package buscador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorCiudad implements IBuscador {
	
	private String ciudad;
	
	public BuscadorCiudad(String ciudad) {
		// TODO Auto-generated constructor stub
		this.ciudad= ciudad;
	}

	@Override
	public ArrayList<Inmueble> filtrar(List<Inmueble> inmuebles) {
		// TODO Auto-generated method stub
		return (ArrayList<Inmueble>) inmuebles.stream().
				filter(inmueble -> inmueble.getCiudad().equals(ciudad)).
				collect(Collectors.toList());
	}

}
