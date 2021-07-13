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
	Inmueble inmueble2;
	LocalDate dia;
	LocalDate dia2;
	LocalDate dia3;
	LocalDate dia4;
	ArrayList<LocalDate> dias;
	ArrayList<LocalDate> fechas;
	Sitio sitio;
	DatosDePago datosDePago;
	Usuario inquilino;
	Usuario propietario;
	PoliticaDeCancelacion politica;
	ArrayList<Reserva> reservas;
	
	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		propietario = mock(Usuario.class);
		dia = mock(LocalDate.class);
		dia2 = mock(LocalDate.class);
		dia3 = mock(LocalDate.class);
		dia4 = mock(LocalDate.class);
		dias = new ArrayList<LocalDate>();
		dias.add(dia);
		dias.add(dia2);
		fechas = new ArrayList<LocalDate>();
		fechas.add(dia3);
		fechas.add(dia4);
		inmueble = mock(Inmueble.class);
		datosDePago = mock(DatosDePago.class);
		sitio = mock(Sitio.class);
		politica = mock(PoliticaDeCancelacion.class);
		reservas = new ArrayList<Reserva>();
		reserva = new Reserva(inquilino, inmueble, dias, datosDePago, politica);
		reserva2 = new Reserva(propietario,inmueble,fechas,datosDePago,politica);
		reservas.add(reserva);
		reservas.add(reserva2);
	}


	@Test 
	void testConfirmarReserva() {
		reserva.confirmarseEn(sitio);
		verify(sitio).enviarMailDeConfirmacion(reserva);
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
		reserva.iniciarCancelacion(dia);//supone la fecha en la q se realiza la reserva
		verify(politica).cancelar(reserva);
		verify(politica).actualizarFecha(dia);
	}
	
	@Test
	void testAbonarAlDueñoCantidad() {
		when(inmueble.getPropietario()).thenReturn(propietario);
		reserva.confirmarPagoPor(100d);
		verify(datosDePago).abonar(propietario, 100d);
	}
	
	@Test
	void testValorPorDias() {
		when(inmueble.valorPorDias(dias)).thenReturn(300d);
		Double monto = reserva.valorPorDias(2);
		assertEquals(monto, 300d);
	}
	
	@Test
	void algunaDeLasFechasEstaOcupadaTestCase() {
		fechas.add(dia);
		assertTrue (reserva.algunaDeLasFechasEstaOcupada(fechas));
	}
	
	@Test
	void ningunaDeLasFechasEstaOcupadaTestCase() {
		assertFalse (reserva.algunaDeLasFechasEstaOcupada(fechas));
	}
	
	@Test
	void noEsReservaQueImposibilitaTestCase() {
		
		assertFalse(reserva.esReservaQueImposibilita(reserva2));
	}
	
	@Test
	void esReservaQueImposibilitaTestCase() {
		fechas.add(dia);
		assertTrue(reserva.esReservaQueImposibilita(reserva2));
	}
	
	@Test
	void cancelarReserva() {
		when(inmueble.getPropietario()).thenReturn(propietario);
		reserva.cancelar();
		verify(propietario).eliminarReserva(reserva);
	}
}
