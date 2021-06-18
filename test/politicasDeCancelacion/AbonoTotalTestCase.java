package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class AbonoTotalTestCase {

	private AbonoTotal accion;
	private CancelacionIntermedia politica;
	private Reserva reserva;

	@BeforeEach
	void setUp() throws Exception {
		accion = new AbonoTotal();
		politica = mock(CancelacionIntermedia.class);
		reserva = mock(Reserva.class);
	}

	@Test
	void testEsAccionParaReserva() {
		when(politica.diferenciaDeDiasEsMayor(reserva, 9)).thenReturn(false);
		boolean esAccionCorrecta = accion.esAccionParaReserva(politica, reserva); 
		assertTrue(esAccionCorrecta);
	}

	@Test
	void testRealizarPago() {
		when(reserva.valor()).thenReturn(300d);
		accion.realizarAccionDePago(reserva);
		verify(reserva).confirmarPagoPor(300d);
	}
}
