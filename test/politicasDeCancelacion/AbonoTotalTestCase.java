package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class AbonoTotalTestCase {

	private AbonoTotal abono;
	private CancelacionIntermedia politica;
	private Reserva reserva;
	private LocalDate fecha;

	@BeforeEach
	void setUp() throws Exception {
		abono = new AbonoTotal();
		politica = mock(CancelacionIntermedia.class);
		reserva = mock(Reserva.class);
	}

	@Test
	void testAbonoParaReserva() {
		when(politica.diferenciaDeDiasEsMayor(reserva, 9, fecha)).thenReturn(false);
		boolean esAbono = abono.esAbonoParaReserva(politica, reserva, fecha); 
		assertTrue(esAbono);	
	}

	@Test
	void testMonto() {
		when(reserva.valor()).thenReturn(300d);
		double monto = abono.monto(reserva);
		assertEquals(300d, monto);
	}
}
