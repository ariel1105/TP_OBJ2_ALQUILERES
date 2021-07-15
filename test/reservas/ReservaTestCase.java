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

	private Reserva reserva;
	private Reserva reserva2;
	private Reserva reserva3; 
	private Inmueble inmueble;
	private Inmueble inmueble2;
	private LocalDate diaInicio;
	private LocalDate diaFin;
	private LocalDate dia3;
	private LocalDate dia4;
	private ArrayList<LocalDate> dias;
	private ArrayList<LocalDate> fechas;
	private Sitio sitio;
	private DatosDePago datosDePago;
	private Usuario inquilino;
	private Usuario propietario;
	private PoliticaDeCancelacion politica;
	private ArrayList<Reserva> reservas;
	private PendienteDeConfirmacion estadoPendiente;
	private Confirmado estadoConfirmado;
	private Cancelado estadoCancelado;
	
	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		propietario = mock(Usuario.class);
		diaInicio = LocalDate.of(2022, 2, 3);
		diaFin = mock(LocalDate.class);
		dia3 = mock(LocalDate.class);
		dia4 = mock(LocalDate.class);
		dias = new ArrayList<LocalDate>();
		inmueble = mock(Inmueble.class);
		datosDePago = mock(DatosDePago.class);
		sitio = mock(Sitio.class);
		politica = mock(PoliticaDeCancelacion.class);
		reservas = new ArrayList<Reserva>();
		reserva = new Reserva(inquilino, inmueble, diaInicio, diaFin, datosDePago, politica);
		reserva2 = new Reserva(propietario,inmueble,diaInicio, diaFin, datosDePago,politica);
		reservas.add(reserva);
		reservas.add(reserva2);
		estadoPendiente = mock(PendienteDeConfirmacion.class);
		estadoConfirmado = mock(Confirmado.class);
		estadoCancelado = mock(Cancelado.class);
	}

	@Test 
	void testConfirmarReservaParaEstadoPendiente() {
		reserva.setEstado(estadoPendiente);
		reserva.confirmarseEn(sitio);
		verify(estadoPendiente).confirmarEn(reserva,sitio);
	}
	
	@Test
	void testConfirmarReservaParaEstadoConfirmado() {
		reserva.setEstado(estadoConfirmado);
		reserva.confirmarseEn(sitio);
		verify(estadoConfirmado).confirmarEn(reserva,sitio);
	}
	
	@Test
	void testConfirmarReservaParaEstadoCancelado() {
		reserva.setEstado(estadoCancelado);
		reserva.confirmarseEn(sitio);
		verify(estadoCancelado).confirmarEn(reserva,sitio);
	}
	
	@Test
	void testReservaOcupaFechaDependeDelEstado() {
		reserva.setEstado(estadoCancelado);
		reserva.ocupaFecha(dia3);
		verify(estadoCancelado).esfechaOcupada(reserva, dia3);
	}
	
	@Test
	void testOcupaFechaDeRangoDependeDelEstado() {
		reserva.setEstado(estadoCancelado);
		reserva.ocupaAlgunaFechaDeRango(diaInicio, diaFin);
		verify(estadoCancelado).ocupaFechaDeRango(reserva, diaInicio, diaFin);
	}
	
	@Test
	void testIniciarCancelacionDelegaEnLaPoliticaDeCancelacionYElInmueble() {
		reserva.iniciarCancelacion(dia3);
		verify(politica).cancelar(reserva,dia3);
		verify(inmueble).cancelarReserva();
	}
	
	@Test
	void testCancelarDelegaEnElEstado() {
		reserva.setEstado(estadoCancelado);
		reserva.cancelar(dia3);
		verify(estadoCancelado).cancelarReserva(reserva, dia3);
	}
	
	@Test
	void testValorDelegaEnElInmueble() {
		reserva.valor();
		verify(inmueble).valorPorRangoDeFechas(diaInicio, diaFin);
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
	void testEsReservaFutura() {
		LocalDate fechaActual = LocalDate.of(2020, 1, 1);
		boolean esReservaFutura = reserva.esReservaFutura(fechaActual);
		assertTrue(esReservaFutura);
	}
	
	@Test
	void testReservaEstaConfirmada() {
		reserva.setEstado(estadoCancelado);
		reserva.estaConfirmada();
		verify(estadoCancelado).estaConfirmada();
	}
	
	
}
