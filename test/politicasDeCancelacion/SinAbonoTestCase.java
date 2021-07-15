package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class SinAbonoTestCase {
	
	private SinAbono abono;
	private CancelacionIntermedia politica;
	private Reserva reserva;
	private LocalDate fecha;

	@BeforeEach
	void setUp() throws Exception {
		abono = new SinAbono();
		politica = mock(CancelacionIntermedia.class);
		reserva = mock(Reserva.class);
		fecha = mock(LocalDate.class);
	}

	@Test
	void testAbonoParaReserva() {
		when(politica.diferenciaDeDiasEsMayor(reserva, 19, fecha)).thenReturn(true);
		boolean esAbono = abono.esAbonoParaReserva(politica, reserva, fecha); 
		assertTrue(esAbono);
	}
	
	@Test
	void testMonto() {
		double monto = abono.monto(reserva);
		assertEquals(0, monto);
	}

}
