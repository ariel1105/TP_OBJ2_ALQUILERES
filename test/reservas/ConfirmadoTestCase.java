package reservas;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sitio.Sitio;

class ConfirmadoTestCase {
	
	private Confirmado estado;
	private Reserva reserva;
	private Sitio sitio;
	private LocalDate fecha;
	private LocalDate inicio;
	private LocalDate fin;
	private Stream<LocalDate> rango;

	@BeforeEach
	void setUp() throws Exception {
		estado = new Confirmado();
		reserva = mock(Reserva.class);
		sitio = mock(Sitio.class);
		fecha = mock(LocalDate.class);
		inicio = mock(LocalDate.class);
		fin = mock(LocalDate.class);
		rango = inicio.datesUntil(fin);
	}

	@Test
	void testConfirmarReserva() {
		estado.confirmarEn(reserva,sitio);
		verify(reserva, never()).setEstado(any(Confirmado.class));
	}
	
	@Test
	void testCancelarReserva() {
		estado.cancelarReserva(reserva, fecha);
		verify(reserva).iniciarCancelacion(fecha);
		verify(reserva).setEstado(any(Cancelado.class));
	}
	
	@Test
	void testFechaOcupada() {
		when(reserva.getDiaInicio()).thenReturn(inicio);
		when(reserva.getDiaFin()).thenReturn(fin);
		when(inicio.datesUntil(fin)).thenReturn(rango);
		when(inicio.equals(fecha)).thenReturn(true);
		boolean esFechaOcupada = estado.esfechaOcupada(reserva, fecha);
		assertTrue(esFechaOcupada);
	}
}
