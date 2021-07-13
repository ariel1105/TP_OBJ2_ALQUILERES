package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorHuespedes implements IBuscador {
	
	private Integer cantidadHuespedes;
	
	public BuscadorHuespedes(Integer cantidad) {
		
		cantidadHuespedes= cantidad;
	}



	@Override
	public ArrayList<Inmueble> filtrar(ArrayList<Inmueble> inmuebles) {
		// TODO Auto-generated method stub

	
	return (ArrayList<Inmueble>) inmuebles.stream().
			filter(inmueble -> inmueble.getCapacidad() >= cantidadHuespedes).
			collect(Collectors.toList());

	
	
	
	}

}
