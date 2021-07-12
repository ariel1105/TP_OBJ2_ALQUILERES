package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorObligatorio implements IBuscador {
	
	private String ciudadParametro;
	private LocalDate fechaEntradaParametro;
	private LocalDate fechaSalidaParametro;

	public BuscadorObligatorio(String ciudad, LocalDate fecha, LocalDate fecha2) {
		// TODO Auto-generated constructor stub
		
		ciudadParametro= ciudad;
		fechaEntradaParametro= fecha;
		fechaSalidaParametro= fecha2;
	}

	@Override
	public ArrayList<Inmueble> filtrar(ArrayList<Inmueble> inmuebles) {
		// TODO Auto-generated method stub

		
		
		return (ArrayList<Inmueble>) inmuebles.stream().
				filter(inmueble -> inmueble.getCiudad().equals(ciudadParametro) && inmueble.estaDisponible(fechaEntradaParametro, fechaSalidaParametro)).
				collect(Collectors.toList());

	}	

}
