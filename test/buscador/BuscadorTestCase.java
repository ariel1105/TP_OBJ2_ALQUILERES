package buscador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Sitio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuscadorTestCase {
	
	private Buscador buscador;
	private Busqueda busqueda;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private ArrayList<Inmueble> inmueblesTotales;
	private ArrayList<Inmueble> inmueblesEsperados;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Sitio sitio;
	

	@BeforeEach
	void setUp() throws Exception {
		buscador = new Buscador();
		busqueda = mock(Busqueda.class);
		inmueblesTotales = new ArrayList <Inmueble>();
		inmueblesEsperados = new ArrayList<Inmueble>();
		fechaInicio = mock(LocalDate.class);
		fechaFin = mock(LocalDate.class);
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		inmueble3 = mock(Inmueble.class);
		inmueblesTotales.add(inmueble1);
		inmueblesTotales.add(inmueble2);
		inmueblesTotales.add(inmueble3);
		sitio = mock(Sitio.class);
	}

	@Test
	void testBusquedaVaciaPorBusquedaSinLosAtributosNecesarios() {
		when(busqueda.esAdmitida()).thenReturn(false);
		ArrayList<Inmueble> inmuebles = buscador.inmueblesPara(busqueda, sitio);
		assertEquals(inmueblesEsperados, inmuebles);
	}
	
	@Test
	void testFiltrarPorCiudad() {
		when(inmueble1.getCiudad()).thenReturn("Carilo");
		when(inmueble2.getCiudad()).thenReturn("Pinamar");
		when(inmueble3.getCiudad()).thenReturn("Mar del Plata");
		inmueblesTotales = buscador.filtrarPorCiudad(inmueblesTotales, "Pinamar");
		inmueblesEsperados.add(inmueble2);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	}


	@Test
	void testFiltrarPorDisponibilidad() {
		when(inmueble1.estaDisponible(fechaInicio, fechaFin)).thenReturn(true);
		when(inmueble2.estaDisponible(fechaInicio, fechaFin)).thenReturn(false);
		when(inmueble3.estaDisponible(fechaInicio, fechaFin)).thenReturn(true);
		inmueblesEsperados.add(inmueble1);
		inmueblesEsperados.add(inmueble3);
		inmueblesTotales = buscador.filtrarPorDisponibilidad(inmueblesTotales, fechaInicio, fechaFin);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	}
	
	@Test
	void testFiltrarPorCantidadDeHuespedes() {
		when(inmueble1.getCapacidad()).thenReturn(3);
		when(inmueble2.getCapacidad()).thenReturn(6);
		when(inmueble3.getCapacidad()).thenReturn(5);
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble3);
		inmueblesTotales = buscador.filtrarPorCantDeHuespedes(inmueblesTotales, 5);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	}
	
	@Test
	void testFiltrarPorCantidadHuespedesCasoNulo() {
		inmueblesEsperados.add(inmueble1);
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble3);
		inmueblesTotales = buscador.filtrarPorCantDeHuespedes(inmueblesTotales, null);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	}
	
	@Test
	void testFiltrarPorPrecioMaximo() {
		when(inmueble1.precioParaRango(fechaInicio, fechaFin)).thenReturn(2000d);
		when(inmueble2.precioParaRango(fechaInicio, fechaFin)).thenReturn(5000d);
		when(inmueble3.precioParaRango(fechaInicio, fechaFin)).thenReturn(3000d);
		inmueblesEsperados.add(inmueble1);
		inmueblesEsperados.add(inmueble3);
		inmueblesTotales = buscador.filtrarPorPrecioMaximo(inmueblesTotales, 3000d, fechaInicio, fechaFin);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	}
	
	@Test
	void testFiltrarPorPrecioMaximoCasoNulo() {
		inmueblesEsperados.add(inmueble1);
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble3);
		inmueblesTotales = buscador.filtrarPorPrecioMaximo(inmueblesTotales, null, fechaInicio, fechaFin);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	
	}
	
	@Test
	void testFiltrarPorPrecioMinimo() {
		when(inmueble1.precioParaRango(fechaInicio, fechaFin)).thenReturn(2000d);
		when(inmueble2.precioParaRango(fechaInicio, fechaFin)).thenReturn(5000d);
		when(inmueble3.precioParaRango(fechaInicio, fechaFin)).thenReturn(3000d);
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble3);
		inmueblesTotales = buscador.filtrarPorPrecioMinimo(inmueblesTotales, 3000d, fechaInicio, fechaFin);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	}
	
	@Test
	void testFiltrarPorPrecioMinimoCasoNulo() {
		inmueblesEsperados.add(inmueble1);
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble3);
		inmueblesTotales = buscador.filtrarPorPrecioMinimo(inmueblesTotales, null, fechaInicio, fechaFin);
		assertEquals(inmueblesTotales, inmueblesEsperados);
	
	}
	
	@Test
	void testFiltrarSoloConParametrosObligatorios() {
		when(busqueda.getCiudad()).thenReturn("Pinamar");
		when(busqueda.getFechaInicio()).thenReturn(fechaInicio);
		when(busqueda.getFechaFin()).thenReturn(fechaFin);
		when(inmueble1.getCiudad()).thenReturn("Pinamar");
		when(inmueble2.getCiudad()).thenReturn("Pinamar");
		when(inmueble3.getCiudad()).thenReturn("Mar del Plata");
		when(inmueble1.estaDisponible(fechaInicio, fechaFin)).thenReturn(false);
		when(inmueble2.estaDisponible(fechaInicio, fechaFin)).thenReturn(true);
		when(inmueble3.estaDisponible(fechaInicio, fechaFin)).thenReturn(true);
		inmueblesEsperados.add(inmueble2);
		inmueblesTotales = buscador.inmueblesPara(busqueda, sitio);
	}
	@Test
	void testFiltrarSoloConTodosLosParametros() {
		when(busqueda.esAdmitida()).thenReturn(true);
		when(busqueda.getCiudad()).thenReturn("Pinamar");
		when(busqueda.getFechaInicio()).thenReturn(fechaInicio);
		when(busqueda.getFechaFin()).thenReturn(fechaFin);
		when(busqueda.getCantHuespedes()).thenReturn(4);
		when(busqueda.getPrecioMaximo()).thenReturn(6000d);
		when(busqueda.getPrecioMinimo()).thenReturn(4000d);		
		when(inmueble1.getCiudad()).thenReturn("Pinamar");
		when(inmueble2.getCiudad()).thenReturn("Pinamar");
		when(inmueble3.getCiudad()).thenReturn("Mar del Plata");
		when(inmueble1.estaDisponible(fechaInicio, fechaFin)).thenReturn(false);
		when(inmueble2.estaDisponible(fechaInicio, fechaFin)).thenReturn(true);
		when(inmueble3.estaDisponible(fechaInicio, fechaFin)).thenReturn(true);
		when(inmueble1.getCapacidad()).thenReturn(2);
		when(inmueble2.getCapacidad()).thenReturn(4);
		when(inmueble3.getCapacidad()).thenReturn(3);
		when(inmueble1.precioParaRango(fechaInicio, fechaFin)).thenReturn(2000d);
		when(inmueble2.precioParaRango(fechaInicio, fechaFin)).thenReturn(5000d);
		when(inmueble3.precioParaRango(fechaInicio, fechaFin)).thenReturn(3000d);
		inmueblesEsperados.add(inmueble2);
		inmueblesTotales = buscador.inmueblesPara(busqueda, sitio);
	}

}
