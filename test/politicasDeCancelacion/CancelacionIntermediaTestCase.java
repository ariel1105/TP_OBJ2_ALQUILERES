package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reservas.Reserva;

class CancelacionIntermediaTestCase {
	
	private Reserva reserva;
	private CancelacionIntermedia politica;
	private LocalDate fechaActual;
	private LocalDate fechaPrimerDiaReserva;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		fechaActual = mock(LocalDate.class);
		fechaPrimerDiaReserva = mock(LocalDate.class);
		politica = new CancelacionIntermedia();
		politica.actualizarFecha(fechaActual);
	}
	
	@Test
	void testActualizarFecha() {//test de la clase abastracta
		politica.actualizarFecha(fechaActual);
		LocalDate fechaDePolitica = politica.getFechaActual();
		assertEquals(fechaActual, fechaDePolitica);
	}
	
	@Test
	void testDiferenciaDeDias() { // test de la clase abstracta
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(21);
		assertTrue(politica.diferenciaDeDiasEsMayor(reserva, 20));
	}

	@Test
	void testNoTieneQueAbonar() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(21);
		assertTrue(politica.noTieneQueAbonar(reserva));
	}
	
	@Test
	void testTieneQueAbonarEl50PorCiento() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(15);
		assertTrue(politica.tieneQueAbonar50PorCiento(reserva));
	}
	
	@Test
	void testTieneQueAbonarLaTotalidad() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(9);
		assertTrue(politica.tieneQueAbonarTotalidad(reserva));
	}
	
	@Test
	void testNoSeAbonaNada() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(22);
		politica.cancelar(reserva);
		verify(reserva).cancelar();
	}
	
	@Test
	void testSeAbonaEl50PorCiento() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(15);
		politica.cancelar(reserva);
		verify(reserva).cancelar();
		verify(reserva).confirmarPagoPor(reserva.valor());
	}
	//preguntar porq da null en valor
}
	