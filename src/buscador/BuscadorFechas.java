package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorFechas implements IBuscador {
	
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	

	public BuscadorFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
		// TODO Auto-generated constructor stub
		
		this.fechaEntrada= fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	@Override
	public ArrayList<Inmueble> filtrar(List<Inmueble> inmuebles) {
		// TODO Auto-generated method stub
		
		return (ArrayList<Inmueble>) inmuebles.stream().
				filter(inmueble -> inmueble.estaDisponible(fechaEntrada, fechaSalida)).
				collect(Collectors.toList());
	}

}
