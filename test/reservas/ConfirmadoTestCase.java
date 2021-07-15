package reservas;

import static org.junit.Assert.assertFalse;
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
	void testFechaOcupadaFechaEnElMedio() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean esFechaOcupada = estado.esfechaOcupada(reserva, LocalDate.of(2021, 1, 15));
		assertTrue(esFechaOcupada);
	}
	
	@Test
	void testFechaOcupadaFechaEnElInicio() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean esFechaOcupada = estado.esfechaOcupada(reserva, LocalDate.of(2021, 1, 10));
		assertTrue(esFechaOcupada);
	}
	
	@Test
	void testFechaOcupadaFechaEnElFinal() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean esFechaOcupada = estado.esfechaOcupada(reserva, LocalDate.of(2021, 1, 20));
		assertTrue(esFechaOcupada);
	}
	
	@Test
	void testFechaOcupadaFechaFueraDeRango() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean esFechaOcupada = estado.esfechaOcupada(reserva, LocalDate.of(2021, 1, 25));
		assertFalse(esFechaOcupada);
	}
	
	@Test
	void testReservaEstaConfirmada() {
		boolean confirmada = estado.estaConfirmada();
		assertTrue(confirmada);
	}
	
	@Test
	void testOcupaPrimerFechaDeRango() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean ocupaAlgunaFecha = estado.ocupaFechaDeRango(reserva, LocalDate.of(2021, 1, 20), LocalDate.of(2021, 2, 1));
		assertTrue(ocupaAlgunaFecha);
	}
	
	@Test
	void testOcupaFechasDelMedioDelDeRango() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean ocupaAlgunaFecha = estado.ocupaFechaDeRango(reserva, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 2, 1));
		assertTrue(ocupaAlgunaFecha);
	}
	
	@Test
	void testOcupaUltimaFechaDeRango() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean ocupaAlgunaFecha = estado.ocupaFechaDeRango(reserva, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 10));
		assertTrue(ocupaAlgunaFecha);
	}
	
	@Test
	void testNoOcupaFechaDeRango() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 10));
		when(reserva.getDiaFin()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean ocupaAlgunaFecha = estado.ocupaFechaDeRango(reserva, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 5));
		assertFalse(ocupaAlgunaFecha);
	}
}
