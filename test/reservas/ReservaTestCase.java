package reservas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import politicasDeCancelacion.CancelacionGratuita;
import politicasDeCancelacion.CancelacionIntermedia;
import politicasDeCancelacion.SinCancelacion;

class ReservaTestCase {

	Reserva reserva;
	Reserva reserva2;
	Reserva reserva3; 
	Inmueble inmueble;
	LocalDate dia;
	LocalDate dia2;
	ArrayList<LocalDate> dias;
	Sitio sitio;
	String formaDePago;
	Usuario inquilino;
	Estado estado;
	CancelacionGratuita politica1;
	CancelacionIntermedia politica2;
	SinCancelacion politica3;
	
	
	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		dia = mock(LocalDate.class);
		dia2 = mock(LocalDate.class);
		dias = new ArrayList<LocalDate>();
		dias.add(dia);
		dias.add(dia2);
		estado = mock(Estado.class);
		inmueble = mock(Inmueble.class);
		formaDePago = "Efectivo";
		sitio = mock(Sitio.class);
		politica1 = mock(CancelacionGratuita.class);
		politica2 = mock(CancelacionIntermedia.class);
		politica3 = mock(SinCancelacion.class);
		reserva = new Reserva(inquilino, inmueble, dias, formaDePago, politica1);
		reserva2 = new Reserva(inquilino, inmueble, dias, formaDePago, politica2);
		reserva3 = new Reserva(inquilino, inmueble, dias, formaDePago, politica3);
	}

	@Test
	void testIngresarReservaEnSitio() {
		reserva.ingresarEnSitio(sitio);
		verify(sitio).agegarReserva(reserva);
	}

	@Test 
	void testConfirmarReserva() {
		reserva.confirmarseEn(sitio);
		verify(sitio).enviarMailDeConfirmacion(reserva);
	}
	
	@Test 
	void testDiaNoExisteEnReservaPorqueNoEstaConfirmada() {
		Boolean diaOcupado = reserva.ocupaFecha(dia);
		assertFalse(diaOcupado);
	}
	
	@Test
	void testDiaOcupado() {
		reserva.confirmarseEn(sitio);
		Boolean diaOcupado = reserva.ocupaFecha(dia);
		assertTrue(diaOcupado);
	}
	
	@Test
	void testReservaEstaConfirmada() {
		reserva.confirmarseEn(sitio);
		assertTrue(reserva.estaConfirmada());
	}
	
	
	@Test 
	void testReservaNoEstaConfirmada() {
		assertFalse(reserva.estaConfirmada());
	}
	
	@Test
	void testNoSeCancelaUnaReservaPendienteDeConfirmacion() {
		reserva.IniciarCancelacion();
		verify(politica1, never()).cancelar(reserva);
	}
	
	@Test 
	void testValorPorReserva() {
		when(inmueble.valorPorDias(dias)).thenReturn(1000d);
		Double monto = reserva.valor();
		assertEquals(1000d, monto);
	}
	
	@Test
	void testDiaDesocupadoPorCancelacion() {
		reserva.confirmarseEn(sitio);
		reserva.cancelar();
		Boolean diaOcupado = reserva.ocupaFecha(dia);
		assertFalse(diaOcupado);
	}
	
	@Test 
	void testFechaInicial() {
		LocalDate diaDeInicioReserva = reserva.primerDia();
		assertEquals(dia, diaDeInicioReserva);
	}
	
	@Test
	void testReservaEsDeCiudad() {
		when(inmueble.getCiudad()).thenReturn("Pinamar");
		boolean esDeCiudad = reserva.esDeCiudad("Pinamar");
		assertTrue(esDeCiudad);
	}
	
	@Test
	void testCiudadDeReserva() {
		when(inmueble.getCiudad()).thenReturn("Pinamar");
		String ciudad = reserva.ciudad();
		assertEquals(ciudad, "Pinamar");
	}
	// preguntar sobre dia actual para hacer las politicas de cancelacion
	
}
