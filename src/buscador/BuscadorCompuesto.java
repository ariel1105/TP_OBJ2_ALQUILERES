package buscador;

import java.time.LocalDate;
import java.util.ArrayList;

import inmueble.Inmueble;
import sitio.Sitio;

public class BuscadorCompuesto implements IBuscador {
	
	private ArrayList <IBuscador> buscadores;	
	

	public BuscadorCompuesto(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida){
		buscadores= new ArrayList<IBuscador>();
		
		if(this.esAdmitida(ciudad, fechaEntrada, fechaSalida)) {

			this.agregarBuscador(new BuscadorCiudad(ciudad));
			this.agregarBuscador(new BuscadorFechaEntrada(fechaEntrada, fechaSalida));
			//this.agregarParametrosOpcionalesSi(cantidadHuespedes, precioMinimo, precioMaximo);
		}
					
		
	}
	
	public void agregarBuscador(IBuscador buscador) {
		// TODO Auto-generated method stub
		this.buscadores.add(buscador);
	}

	private void agregarParametrosOpcionalesSi(Integer cantidadHuespedes, Double precioMinimo, Double precioMaximo) {
		// TODO Auto-generated method stub
		if (!(cantidadHuespedes == null)) {
			this.buscadores.add(new BuscadorHuespedes(cantidadHuespedes));
		}
		if (!(precioMinimo == null) || !(precioMaximo==null)) {
			
			this.buscadores.add(new BuscadorPrecioMinimoYMaximo(precioMinimo, precioMaximo));
		}
	}

	public Boolean esAdmitida(String ciudad, LocalDate fechaInicio, LocalDate fechaFin) {
		return (ciudad != null) && (fechaInicio != null) && (fechaFin != null); 
	}
	

	@Override
	public ArrayList<Inmueble> filtrar(ArrayList<Inmueble> inmueblesPublicados){
		
		ArrayList<Inmueble> inmueblesFiltrados= new ArrayList<Inmueble>();
		ArrayList<Inmueble> inmuebles= inmueblesPublicados;
		
		if (!(buscadores.isEmpty())) {
		
		for (int i=0;i < buscadores.size();i++) {
		
			
			inmueblesFiltrados= buscadores.get(i).filtrar(inmuebles);
			inmuebles= inmueblesFiltrados;
	}
	}
	return inmueblesFiltrados;
}

	public ArrayList<Inmueble> realizarBusqueda(Sitio sitio) {
		// TODO Auto-generated method stub
		return (this.filtrar(sitio.getInmueblesPublicados()));
	}
	
	

	
}
