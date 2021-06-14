package buscador;

public class Buscador {
	parametros obligatorios
		ciudad
		fecha entrada
		fecha salida
	
	parametros opcionales 
		cantidad de huespedes
		precio minimo 
		precio maximo
		
	ArrayList<(ParametroDeBusqueda(enum), valor)> busqueda
	
	buscador.agregarParametroBusqueda(ciudad, "Pinamar")
	
	usuario.buscar(parametrosDeBusqueda, sitio){
		usuario.crearBusqeudaInmuebles(parametrosBusqueda);
		
	}
	
	usuario.buscarInmuebles(busqueda, sitio){
		ArrayList<Inmuebles>inmuebles = new ArrayListInmuebles();
		if (buscador.esBusquedaPosible (busqueda)) {
			sitio.InmublesPara(busqueda, inmuebles);
			return inmuebles;
			
		}
		else {return inmuebles};
	}
	
}
