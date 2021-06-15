package buscador;

import java.time.LocalDate;
import java.util.ArrayList;

import inmueble.Inmueble;
import sitio.Sitio;

public class Buscador {

	public ArrayList<Inmueble> inmueblesPara(Busqueda busqueda, Sitio sitio) {
		ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
		if (busqueda.esAdmitida()) {
			inmuebles = sitio.getInmueblesPublicados();
			inmuebles = this.filtrarPorCiudad(inmuebles, busqueda.getCiudad());
			inmuebles = this.filtrarPorDisponibilidad(inmuebles, busqueda.getFechaInicio(), busqueda.getFechaFin());
			inmuebles = this.filtrarPorCantDeHuespedes(inmuebles, busqueda.getCantHuespedes());
			inmuebles = this.filtrarPorPrecioMaximo(inmuebles, busqueda.getPrecioMaximo(), busqueda.getFechaInicio(), busqueda.getFechaFin());
			inmuebles = this.filtrarPorPrecioMinimo(inmuebles, busqueda.getPrecioMinimo(), busqueda.getFechaInicio(), busqueda.getFechaFin());
		}
		return inmuebles;
	}

	public ArrayList<Inmueble> filtrarPorCiudad(ArrayList<Inmueble> inmueblesTotales, String ciudad) {
		ArrayList<Inmueble> inmuebles = inmueblesTotales;
		for(int i = 0; i < inmuebles.size(); i++) {
			if(!ciudad.equals(inmuebles.get(i).getCiudad())) {
				inmuebles.remove(i);
			}
		}
		return inmuebles;
	}
	
	public ArrayList<Inmueble> filtrarPorDisponibilidad(ArrayList<Inmueble> inmueblesTotales, LocalDate fechaInicio, LocalDate fechaFin) {
		ArrayList<Inmueble> inmuebles = inmueblesTotales;
		for(int i = 0; i < inmuebles.size(); i++) {
			if(!inmuebles.get(i).estaDisponible(fechaInicio, fechaFin)) {
				inmuebles.remove(i);
			}
		}
		return inmuebles;
	}

	public ArrayList<Inmueble> filtrarPorCantDeHuespedes(ArrayList<Inmueble> inmueblesTotales, Integer cantHuespedes) {
		ArrayList<Inmueble> inmuebles = inmueblesTotales;
		if (cantHuespedes != null) {
				for(int i= 0; i < inmuebles.size(); i++) {
					if(inmuebles.get(i).getCapacidad()<cantHuespedes) {
						inmuebles.remove(i);
					}
				}
			}
		return inmuebles;
	}

	public ArrayList<Inmueble> filtrarPorPrecioMaximo(ArrayList<Inmueble> inmueblesTotales, Double precio, LocalDate fechaInicio, LocalDate fechaFin) {
		ArrayList<Inmueble> inmuebles = inmueblesTotales;
		if (precio != null) {
			for(int i= 0; i < inmuebles.size(); i++) {
				if(inmuebles.get(i).precioParaRango(fechaInicio, fechaFin) > precio)  {
					inmuebles.remove(i);
				}
			}
		}
		return inmuebles;
	}

	public ArrayList<Inmueble> filtrarPorPrecioMinimo(ArrayList<Inmueble> inmueblesTotales, Double precio,
			LocalDate fechaInicio, LocalDate fechaFin) {
		ArrayList<Inmueble> inmuebles = inmueblesTotales;
		if (precio != null) {
			for(int i= 0; i < inmuebles.size(); i++) {
				if(inmuebles.get(i).precioParaRango(fechaInicio, fechaFin) < precio)  {
					inmuebles.remove(i);
				}
			}
		}
		return inmuebles;
	}
	
	/*private Busqueda busqueda{
		String ciudad;
		(LocalDate,LocalDate) rangoFecha 
		int cantHuespedes
		Double precioMaximo
		Double precioMinimo
	}
	
	Buscador {
	Boolean esBusquedaPosible(busqueda);

		inmueblesPara(busqueda, Reservas){
			ArrayList <Inmuebles> inmuebles= sitio.getInmuebles();
			inmuebles = this.filtrarPorCiudad(inmuebles, busqueda.getCiudad());
			inmuebles = this.filtrarPorDisponibilidad(inmuebles, busqueda.getRangoFecha, sitio);
			inmuebles = this.filtrarPorCantHuespedes(inmuebles, busqueda.cantHuespedes);
			inmuebles = this.filtrarPorPrecioMax(inmuebles, busqueda.getPrecioMaximo);
			inmuebles = this.filtrarPorPrecioMin(inmuebles, busqueda.getPrecioMinimo);
			return inmuebles;
		}
	}
	*/

	
}	
	
