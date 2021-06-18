package buscador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusquedaTestCase {
	
	private Busqueda busqueda;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	@BeforeEach
	void setUp() throws Exception {
		busqueda = new Busqueda();
		fechaInicio = mock(LocalDate.class);
		fechaFin = mock(LocalDate.class);
	}
	
	@Test
	void testSetearParametrosObligatorios() {
		busqueda.setParametosObligatorios("Pinamar", fechaInicio, fechaFin);
		String ciudad = busqueda.getCiudad();
		LocalDate fechaI = busqueda.getFechaInicio();
		LocalDate fechaF = busqueda.getFechaFin();
		assertEquals("Pinamar", ciudad);
		assertEquals(fechaInicio, fechaI);
		assertEquals(fechaFin, fechaF);
		
	}

	@Test
	void testNoEsBusquedaAdmitida() {
		assertFalse(busqueda.esAdmitida());
	}
	
	@Test
	void testEsBusquedaAdmitida() {
		busqueda.setParametosObligatorios("Pinamar", fechaInicio, fechaFin);
		assertTrue(busqueda.esAdmitida());
	}
	
	@Test
	void testSetearCantidadHuespedes() {
		busqueda.setCantHuespedes(2);
		int cantHuespedes = busqueda.getCantHuespedes();
		assertEquals(2, cantHuespedes);
	}
	
	@Test 
	void testSetearPrecioMaximo() {
		busqueda.setPrecioMaximo(400d);
		Double precioMaximo = busqueda.getPrecioMaximo();
		assertEquals(400d, precioMaximo);
	}
	
	@Test
	void testSetearPrecioMinimo() {
		busqueda.setPrecioMinimo(400d);
		Double precioMinimo = busqueda.getPrecioMinimo();
		assertEquals(400d, precioMinimo);
	}

}
