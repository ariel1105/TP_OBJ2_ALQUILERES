package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class AbonoDel50PorCientoTestCase {
	
	private AbonoDel50PorCiento abono;
	private CancelacionIntermedia politica;
	private Reserva reserva;
	private LocalDate fecha;

	@BeforeEach
	void setUp() throws Exception {
		abono = new AbonoDel50PorCiento();
		politica = mock(CancelacionIntermedia.class);
		reserva = mock(Reserva.class);
	}

	@Test
	void testEsAbonoParaReserva() {
		when(politica.diferenciaDeDiasEsMayor(reserva, 19, fecha)).thenReturn(false);
		when(politica.diferenciaDeDiasEsMayor(reserva, 9, fecha)).thenReturn(true);
		boolean esAbono = abono.esAbonoParaReserva(politica, reserva, null); 
		assertTrue(esAbono);
	}

	@Test
	void testMonto() {
		when(reserva.valor()).thenReturn(300d);
		double monto = abono.monto(reserva);
		assertEquals(150d, monto);
	}
}
