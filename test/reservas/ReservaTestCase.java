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

import inmueble.DatosDePago;
import inmueble.Inmueble;
import sitio.Sitio;
import politicasDeCancelacion.CancelacionGratuita;
import politicasDeCancelacion.CancelacionIntermedia;
import politicasDeCancelacion.PoliticaDeCancelacion;
import politicasDeCancelacion.SinCancelacion;
import usuario.Usuario;

class ReservaTestCase {

	Reserva reserva;
	Reserva reserva2;
	Reserva reserva3; 
	Inmueble inmueble;
	LocalDate dia;
	LocalDate dia2;
	ArrayList<LocalDate> dias;
	Sitio sitio;
	DatosDePago datosDePago;
	Usuario inquilino;
	Usuario dueño;
	Estado estado;
	PoliticaDeCancelacion politica;
	
	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		dueño = mock(Usuario.class);
		dia = mock(LocalDate.class);
		dia2 = mock(LocalDate.class);
		dias = new ArrayList<LocalDate>();
		dias.add(dia);
		dias.add(dia2);
		estado = mock(Estado.class);
		inmueble = mock(Inmueble.class);
		datosDePago = mock(DatosDePago.class);
		sitio = mock(Sitio.class);
		politica = mock(PoliticaDeCancelacion.class);
		reserva = new Reserva(inquilino, inmueble, dias, datosDePago, politica);
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
	
	@Test 
	void testIniciarCancelacion() {
		reserva.IniciarCancelacion(dia);//supone la fecha en la q se realiza la reserva
		verify(politica).cancelar(reserva);
		verify(politica).actualizarFecha(dia);
	}
	
	@Test
	void testAbonarAlDueñoCantidad() {
		when(inmueble.getDueño()).thenReturn(dueño);
		reserva.confirmarPagoPor(100d);
		verify(datosDePago).abonar(dueño, 100d);
	}
	
	@Test
	void testValorPorDias() {
		when(inmueble.valorPorDias(dias)).thenReturn(300d);
		Double monto = reserva.valorPorDias(2);
		assertEquals(monto, 300d);
	}
	
}
