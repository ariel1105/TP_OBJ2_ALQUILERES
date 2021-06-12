package Busqueda;

import java.util.List;

import reservas.Publicacion;

public class BuscadorCompleto extends Buscador {

	@Override
	public boolean compararPublicacion(Busqueda busqueda, Publicacion publicacion) {
		// TODO Auto-generated method stub
		return publicacion.getInmuebleAsignado().getCiudad().equals(busqueda.getCiudad())
				
				&& publicacion.getInmuebleAsignado().estaDisponible(busqueda.getFechaEntrada(), busqueda.getFechaSalida()) 
						&& publicacion.getInmuebleAsignado().getCapacidad() >= busqueda.getHuespedes()
							&&  (publicacion.getInmuebleAsignado().getPrecio() >= busqueda.getPrecioMinimo() && publicacion.getInmuebleAsignado().getPrecio() <= busqueda.getPrecioMaximo());

	}

	
}
