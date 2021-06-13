package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.DatosDePago;
import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Sitio;

class UsuarioTestCase {
	
	private Usuario inquilino;
	private Usuario propietario;
	private Inmueble inmueble;
	private DatosDePago datosDePago;
	private Sitio sitio;
	private LocalDate fecha;
	private Reserva reserva;
	private ArrayList<Inmueble> galeriaDeInmuebles = new ArrayList<Inmueble>();
	private ArrayList<LocalDate> diasDeReserva = new ArrayList<LocalDate>();
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	
	@BeforeEach
	void setUp() throws Exception {
		inquilino = new Usuario("nombre", "mail", "telefono");
		propietario = new Usuario("nombre2", "mail2", "telefono2");
		inmueble = mock(Inmueble.class);
		datosDePago = mock(DatosDePago.class);
		reserva = mock(Reserva.class);
		sitio = mock(Sitio.class);
		fecha = mock(LocalDate.class);
		galeriaDeInmuebles.add(inmueble);
		diasDeReserva.add(fecha);
		reservas.add(reserva);
	}

	@Test
	void testRegistrarse() {
		inquilino.registrarse(sitio);
		verify(sitio).registrarUsuario(inquilino);
	}
	
	@Test
	void testAgregarInmueble() {
		propietario.agregar(inmueble);
		ArrayList<Inmueble> inmuebles = propietario.getInmuebles();
		assertEquals(galeriaDeInmuebles, inmuebles);
	}
	
	@Test
	void testSubirInmueble() {//consultar, no deberia funcionar asi
		propietario.registrarse(sitio);
		propietario.publicar(inmueble, sitio);
		verify(sitio).agregar(inmueble);
		ArrayList<Inmueble> inmuebles = propietario.getInmuebles();
		assertEquals(galeriaDeInmuebles, inmuebles);

	}
	
	@Test
	void testRecibirSolicitudDeReserva() {
		propietario.recibirSolicitudDeReserva(reserva);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		assertEquals(reservas, reservasPendientesDeConfirmacion);
	}
	
	@Test
	void testSolicitarReserva() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getDueño()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(true);
		inquilino.solicitarReserva(reserva);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		assertEquals(reservas, reservasPendientesDeConfirmacion);
	}
	
	@Test
	void testSolicitarReservaSeNiega() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getDueño()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(false);
		inquilino.solicitarReserva(reserva);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		ArrayList<Reserva> sinReservas = new ArrayList<Reserva>();
		assertEquals(sinReservas, reservasPendientesDeConfirmacion);
	}
	
	@Test
	void testConfirmarReserva() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getDueño()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(true);
		inquilino.solicitarReserva(reserva);
		propietario.confirmar(reserva, sitio);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		ArrayList<Reserva> sinReservas = new ArrayList<Reserva>();
		assertEquals(sinReservas, reservasPendientesDeConfirmacion);
		verify(reserva).confirmarseEn(sitio);
	}
	
	
}
