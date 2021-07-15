package periodo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import periodo.PeriodoPrecio;

class PeriodoPrecioTestCase {
	private LocalDate fecha1;
	private LocalDate fecha2;
	private LocalDate fecha3;
	private double precio;
	private PeriodoPrecio periodoPrecio;


	@BeforeEach
	void setUp() throws Exception {
		fecha1 = LocalDate.of(2021, 7, 13);
		fecha2 = LocalDate.of(2021, 7, 20);
		precio = 2000d;
		periodoPrecio = new PeriodoPrecio(precio, fecha1, fecha2);

	}
	
	@Test
	void testConstructor() {
		LocalDate fechaInicio = periodoPrecio.getFechaInicio();
		LocalDate fechaFin = periodoPrecio.getFechaFin();
		double monto = periodoPrecio.getPrecio();
		assertEquals(fecha1,fechaInicio);
		assertEquals(fecha2,fechaFin);
		assertEquals(monto, precio);
	}

	@Test
	void FechaInicialPerteneceAlPeriodo() {
		
		fecha3 = LocalDate.of(2021, 7, 13);
		assertTrue(periodoPrecio.perteneceLaFecha(fecha3));
		
	}
	@Test
	void FechaFinalPerteneceAlPeriodo() {
		
		fecha3 = LocalDate.of(2021, 7, 20);
		assertTrue(periodoPrecio.perteneceLaFecha(fecha3));
		
	}
	@Test
	void FechaPerteneceAlPeriodo() {
		
		fecha3 = LocalDate.of(2021, 7, 15);
		assertTrue(periodoPrecio.perteneceLaFecha(fecha3));
		
	}
	@Test
	void FechaNoPerteneceAlPeriodo() {
		
		fecha3 = LocalDate.of(2022, 7, 13);
		assertFalse(periodoPrecio.perteneceLaFecha(fecha3));
		
	}
	
}
