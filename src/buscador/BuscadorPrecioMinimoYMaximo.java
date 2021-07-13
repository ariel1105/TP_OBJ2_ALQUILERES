package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import inmueble.Inmueble;

public class BuscadorPrecioMinimoYMaximo implements IBuscador {
	

	private Double precioMinimoP;
	private Double precioMaximoP;
	
	public BuscadorPrecioMinimoYMaximo(Double precioMinimo, Double precioMaximo) {
		// TODO Auto-generated constructor stub
		
		precioMinimoP= precioMinimo;
		precioMaximoP= precioMaximo;
	}
	
	
	@Override
	public ArrayList<Inmueble> filtrar(ArrayList<Inmueble> inmuebles) {
		// TODO Auto-generated method stub
		return (ArrayList<Inmueble>) inmuebles.stream().
				filter(inmueble -> inmueble.getPrecioActual() >= precioMinimoP && inmueble.getPrecioActual() < precioMaximoP ).
				collect(Collectors.toList());
	
	}
}
