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

class CancelacionGratuitaTestCase {
	
	private Reserva reserva;
	private CancelacionGratuita politica;
	private LocalDate fechaActual;
	private LocalDate fechaPrimerDiaReserva;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		fechaActual = mock(LocalDate.class);
		fechaPrimerDiaReserva = mock(LocalDate.class);
		politica = new CancelacionGratuita();
		politica.actualizarFecha(fechaActual);
	}

	@Test
	void testNoTieneQueAbonarNada() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(15);
		assertTrue(politica.noTieneQueAbonar(reserva));
	}
	
	@Test
	void testNoAbonaNada() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(15);
		politica.cancelar(reserva);
		verify(reserva).cancelar();
		verify(reserva, never()).confirmarPagoPor(reserva.valorPorDias(2));
	}
	
	@Test
	void testSeTieneQueAbonar() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(5);
		assertFalse(politica.noTieneQueAbonar(reserva));
	}
	
	@Test
	void testSeAbonaPorCancelacionTardia() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(5);
		politica.cancelar(reserva);
		verify(reserva).confirmarPagoPor(reserva.valorPorDias(2));
		verify(reserva).cancelar();
	}

}
