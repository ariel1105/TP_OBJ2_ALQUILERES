package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reservas.Reserva;

class CancelacionIntermediaTestCase {
	
	private Reserva reserva;
	private CancelacionIntermedia politica;
	private Accion accion1;
	private Accion accion2;
	private ArrayList<Accion> acciones;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		acciones = new ArrayList<Accion>();
		accion1 = mock(Accion.class);
		accion2 = mock(Accion.class);
		acciones.add(accion1);
		acciones.add(accion2);
		politica = new CancelacionIntermedia(acciones);
	}
	
	@Test
	void testAccionParaReserva() {
		when(accion1.esAccionParaReserva(politica, reserva)).thenReturn(true);
		Accion accionResultante = politica.accionParaReserva(reserva);
		assertEquals(accion1, accionResultante);
	}
	
	@Test
	void testAccionParaReserva2() {
		when(accion1.esAccionParaReserva(politica, reserva)).thenReturn(false);
		when(accion2.esAccionParaReserva(politica, reserva)).thenReturn(true);
		Accion accionResultante = politica.accionParaReserva(reserva);
		assertEquals(accion2,accionResultante);
	}
	
	@Test
	void testCancelar() {
		when(accion1.esAccionParaReserva(politica, reserva)).thenReturn(true);
		politica.cancelar(reserva);
		verify(accion1).realizarAccionDePago(reserva);
		verify(reserva).cancelar();
	}
	
	
	/*
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
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(17);
		when(reserva.valor()).thenReturn(200d);
		politica.cancelar(reserva);
		verify(reserva).cancelar();
		verify(reserva).confirmarPagoPor(100d);
	}
	
	@Test
	void testAbonaTotalidad() {
		when(reserva.primerDia()).thenReturn(fechaPrimerDiaReserva);
		when(fechaActual.compareTo(fechaPrimerDiaReserva)).thenReturn(5);
		when(reserva.valor()).thenReturn(200d);
		politica.cancelar(reserva);
		verify(reserva).cancelar();
		verify(reserva).confirmarPagoPor(200d);
	}
	*/
}
	