package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class SinAbonoTestCase {
	
	private SinAbono accion;
	private CancelacionIntermedia politica;
	private Reserva reserva;

	@BeforeEach
	void setUp() throws Exception {
		accion = new SinAbono();
		politica = mock(CancelacionIntermedia.class);
		reserva = mock(Reserva.class);
	}

	@Test
	void testEsAccionParaReserva() {
		when(politica.diferenciaDeDiasEsMayor(reserva, 19)).thenReturn(true);
		boolean esAccionCorrecta = accion.esAccionParaReserva(politica, reserva); 
		assertTrue(esAccionCorrecta);
	}
	
	@Test
	void testRealizarAccionDePago() {
		accion.realizarAccionDePago(reserva);
		verify(reserva, never()).confirmarPagoPor(0d);
	}

}
