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
	private LocalDate fecha4;
	private ArrayList<LocalDate> periodo;
	private double precio;
	private PeriodoPrecio periodoPrecio;


	@BeforeEach
	void setUp() throws Exception {
		fecha1 = mock(LocalDate.class);
		fecha2 = mock(LocalDate.class);
		fecha3 = mock(LocalDate.class);
		fecha4 = mock(LocalDate.class);
		periodo = new ArrayList<LocalDate>();
		periodo.add(fecha1);
		periodo.add(fecha2);
		periodo.add(fecha3);
		precio = 2000d;
		periodoPrecio = new PeriodoPrecio(precio,periodo);

	}

	@Test
	void FechaPerteneceAlPeriodo() {

		when(fecha4.getDayOfMonth()).thenReturn(1);
		when(fecha4.getMonthValue()).thenReturn(2);
		when(fecha4.getYear()).thenReturn(2020);
		when(fecha1.getDayOfMonth()).thenReturn(1);
		when(fecha1.getMonthValue()).thenReturn(2);
		when(fecha1.getYear()).thenReturn(2020);
		periodoPrecio.agregarFecha(fecha4);
		
		assertTrue(periodoPrecio.perteneceLaFecha(fecha4));
		
	}
	
	@Test
	void AgregarFechaAlPeriodo() {
		ArrayList <LocalDate> periodo1 = new ArrayList<LocalDate>();
		periodo1.add(fecha1);
		periodo1.add(fecha2);
		periodo1.add(fecha3);
		periodo1.add(fecha4);
		
		periodoPrecio.agregarFecha(fecha4);
		
		assertEquals(periodo1, periodoPrecio.getPeriodo());
		
	}

}
