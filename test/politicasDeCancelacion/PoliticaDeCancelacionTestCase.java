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
		fechaActual = LocalDate.of(2021, 1, 1);
		fechaPrimerDiaReserva = LocalDate.of(2021, 1, 22);
		reserva = mock(Reserva.class);
	}
	
	@Test
	void testDiferenciaDeDias() { 
		when(reserva.getDiaInicio()).thenReturn(fechaPrimerDiaReserva);
		assertTrue(politica.diferenciaDeDiasEsMayor(reserva, 20, fechaActual));
	}
	
	
	@Test
	void testValor() {
		double valor = politica.valorPara(reserva, fechaActual);
		assertEquals(0, valor);
	}
	
}
