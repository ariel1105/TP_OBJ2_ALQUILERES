package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class PoliticaDeCancelacionTestCase {

	private PoliticaDeCancelacion politica;
	private LocalDate fechaActual;
	private LocalDate fechaPrimerDiaReserva;
	private Reserva reserva;
	
	
	@BeforeEach
	void setUp() throws Exception {
		politica = spy(PoliticaDeCancelacion.class);
		fechaActual = mock(LocalDate.class);
		fechaPrimerDiaReserva = mock(LocalDate.class);
		reserva = mock(Reserva.class);
	}

	@Test
	void testActualizarFecha() {//test de la clase abastracta
		politica.actualizarFecha(fechaActual);
		LocalDate fechaDePolitica = politica.getFechaActual();
		assertEquals(fechaActual, fechaDePolitica);
	}
	
	@Test
	void testDiferenciaDeDias() { // test de la clase abstracta
		politica.actualizarFecha(fechaActual);
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(21);
		assertTrue(politica.diferenciaDeDiasEsMayor(reserva, 20));
	}
	
}
