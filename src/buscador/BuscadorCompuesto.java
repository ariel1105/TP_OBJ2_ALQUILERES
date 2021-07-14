package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import inmueble.Inmueble;
import sitio.Sitio;

public class BuscadorCompuesto implements IBuscador {
	
	private List <IBuscador> buscadores;	
	

	public BuscadorCompuesto(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida){
		buscadores= new ArrayList<IBuscador>();
		
		if(this.esAdmitida(ciudad, fechaEntrada, fechaSalida)) {

			this.agregarBuscador(new BuscadorCiudad(ciudad));
			this.agregarBuscador(new BuscadorFechas(fechaEntrada, fechaSalida));
			//this.agregarParametrosOpcionalesSi(cantidadHuespedes, precioMinimo, precioMaximo);
		}
					
		
	}
	
	public void agregarBuscador(IBuscador buscador) {
		// TODO Auto-generated method stub
		this.buscadores.add(buscador);
	}

	public void agregarParametrosOpcionalesSi(Integer cantidadHuespedes, Double precioMinimo, Double precioMaximo) {
		// TODO Auto-generated method stub
		//if (!(cantidadHuespedes == null)) {
			this.buscadores.add(new BuscadorHuespedes(cantidadHuespedes));
		//}
		//if (!(precioMinimo == null) || !(precioMaximo==null)) {
			
			this.buscadores.add(new BuscadorPrecioMinimoYMaximo(precioMinimo, precioMaximo));
		//}
	}

	public Boolean esAdmitida(String ciudad, LocalDate fechaInicio, LocalDate fechaFin) {
		return (ciudad != null) && (fechaInicio != null) && (fechaFin != null); 
	}
	
	@Override
	public List<Inmueble> filtrar(List<Inmueble> inmueblesPublicados){
		
		List<Inmueble> inmueblesFiltrados= new ArrayList<Inmueble>();
		List<Inmueble> inmuebles= inmueblesPublicados;
		
		if (!(buscadores.isEmpty())) {
		
			for (int i=0;i < buscadores.size();i++) {
		
			
				inmueblesFiltrados= (ArrayList<Inmueble>) buscadores.get(i).filtrar(inmuebles);
				inmuebles= inmueblesFiltrados;
			}
		}
	return inmueblesFiltrados;
	}

	public List<Inmueble> realizarBusqueda(Sitio sitio) {
		// TODO Auto-generated method stub
		return (this.filtrar(sitio.getInmueblesPublicados()));
	}
	
	

	
}
