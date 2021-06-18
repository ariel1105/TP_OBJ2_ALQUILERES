package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class AbonoDel50PorCientoTestCase {
	
	private AbonoDel50PorCiento accion;
	private CancelacionIntermedia politica;
	private Reserva reserva;

	@BeforeEach
	void setUp() throws Exception {
		accion = new AbonoDel50PorCiento();
		politica = mock(CancelacionIntermedia.class);
		reserva = mock(Reserva.class);
	}

	@Test
	void testEsAccionParaReserva() {
		when(politica.diferenciaDeDiasEsMayor(reserva, 19)).thenReturn(false);
		when(politica.diferenciaDeDiasEsMayor(reserva, 9)).thenReturn(true);
		boolean esAccionCorrecta = accion.esAccionParaReserva(politica, reserva); 
		assertTrue(esAccionCorrecta);
	}

	@Test
	void testRealizarPago() {
		when(reserva.valor()).thenReturn(300d);
		accion.realizarAccionDePago(reserva);
		verify(reserva).confirmarPagoPor(150d);
	}
}
