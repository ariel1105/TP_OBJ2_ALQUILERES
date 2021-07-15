

package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import administradorDeReservas.AdministadorDeReservasInquilino;
import inmueble.DatosDePago;
import inmueble.Inmueble;
import perfiles.PerfilPropietario;
import perfiles.PerfilInquilino;
import reservas.Reserva;
import Categorias.Categoria;
import sitio.Sitio;

class UsuarioTestCase {
	
	private Usuario inquilino;
	//private Usuario propietario;
	private Inmueble inmueble;
	private DatosDePago datosDePago;
	private Sitio sitio;
	private LocalDate fecha;
	private Reserva reserva;
	private Reserva reserva2;
	private Reserva reserva3;
	private AdministadorDeReservasInquilino admin;
	private Categoria cat;
	private ArrayList<Inmueble> galeriaDeInmuebles = new ArrayList<Inmueble>();
	private ArrayList<LocalDate> diasDeReserva = new ArrayList<LocalDate>();
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private ArrayList<String> servicios = new ArrayList<String>();
	private PerfilPropietario perfilPropietario;
	private PerfilInquilino perfilInquilino;
	private Usuario propietario;
	
	@BeforeEach
	void setUp() throws Exception {
		admin = mock(AdministadorDeReservasInquilino.class);
		perfilPropietario = mock(PerfilPropietario.class);
		perfilInquilino = mock(PerfilInquilino.class);
		inquilino = new Usuario("nombre", "mail", "telefono",admin, null);
		propietario = new Usuario("nombre2", "mail2", "telefono2",admin, null);
		inmueble = mock(Inmueble.class);
		datosDePago = mock(DatosDePago.class);
		reserva = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		cat = mock(Categoria.class);
		sitio = mock(Sitio.class);
		fecha = mock(LocalDate.class);
		galeriaDeInmuebles.add(inmueble);
		diasDeReserva.add(fecha);
		reservas.add(reserva);
	}

	@Test
	void testRegistrarse() {
		inquilino.registrarse(sitio, fecha);
		LocalDate fechaDeRegistro = inquilino.getFechaQueSeRegistro();
		verify(sitio).registrarUsuario(inquilino);
		assertEquals(fecha, fechaDeRegistro);
	}
		
	@Test
	void testRecibirSolicitudDeReserva() {
		propietario.agregarInmueble(inmueble);
		when(reserva.getInmueble()).thenReturn(inmueble);
		propietario.recibirSolicitudDeReserva(reserva);
		verify(inmueble).agregarReserva(reserva);
	}
	
	@Test
	void testSolicitarReserva() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(true);
		when(inmueble.getPropietario()).thenReturn(propietario);
		inquilino.solicitarReserva(reserva, inmueble);
		verify(inmueble).agregarReserva(reserva);
		verify(admin).ingresar(reserva);
	}
	
	@Test
	void testSolicitarReservaSeNiega() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(false);
		inquilino.solicitarReserva(reserva, inmueble);
		verify(inmueble, never()).agregarReserva(reserva);
		verify(admin, never()).ingresar(reserva);
	}
	
	@Test
	void testConfirmarReserva() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.tieneReserva(reserva)).thenReturn(true);
		propietario.confirmar(reserva, sitio);
		verify(reserva).confirmarseEn(sitio);
	}
	
	@Test
	void testNoSeConfirmaReservaInexistente() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.tieneReserva(reserva)).thenReturn(false);
		propietario.confirmar(reserva, sitio);
		verify(reserva, never()).confirmarseEn(sitio);
	}
	
	
	@Test
	void testCantidadDeReservas() {
		when(admin.cantidadeDeReservas()).thenReturn(5);
		int vecesQueInquilinoAlquilo = inquilino.vecesQueAlquilaron();
		assertEquals(5, vecesQueInquilinoAlquilo);
	}
	
	@Test
	void testPropietarioPuedeRecibirPuntuacionDeInquilino() {
		when(admin.leAlquiloA(propietario, fecha)).thenReturn(true);
		boolean puedeRecibirPuntuacion = propietario.puedeRecibirPuntuacionPorEstadiaPor(inquilino, fecha);
		assertTrue(puedeRecibirPuntuacion);
	}
	
	@Test
	void testRecibirPuntuacionPorEstadia() {
		propietario.setPerfilPropietario(perfilPropietario);
		propietario.recibirPuntuacionPorEstadia(cat, 5);
		verify(perfilPropietario).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testPuntuarComoInquilino() {
		propietario.setPerfilPropietario(perfilPropietario);
		when(admin.leAlquiloA(propietario, fecha)).thenReturn(true);
		inquilino.puntuarComoInquilino(propietario, cat, 5, fecha);
		verify(perfilPropietario).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testNoSeRealizaPuntuacionComoInquilino() {
		propietario.setPerfilPropietario(perfilPropietario);
		when(admin.leAlquiloA(propietario, fecha)).thenReturn(false);
		inquilino.puntuarComoInquilino(propietario, cat, 5, fecha);
		verify(perfilPropietario, never()).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testPuntuarInmueble() {
		when(inmueble.puedeRecibirPuntuacionPorEstadiaPor(inquilino, fecha)).thenReturn(true);
		inquilino.puntuarComoInquilino(inmueble, cat, 5, fecha);
		verify(inmueble).recibirPuntuacionPorEstadia(cat, 5);
	}
	
	@Test
	void testNoSeRealizaPuntuacionEnInmueble() {
		when(inmueble.puedeRecibirPuntuacionPorEstadiaPor(inquilino, fecha)).thenReturn(false);
		inquilino.puntuarComoInquilino(inmueble,cat,5, fecha);
		verify(inmueble, never()).recibirPuntuacionPorEstadia(cat, 5);
	}
	
	@Test
	void testRecibirPuntuacionComoIniquilino() {
		inquilino.setPerfilInquilino(perfilInquilino);
		inquilino.recibirPuntuacionComoInquilino(cat, 5);
		verify(perfilInquilino).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testPuedePuntuarInquilino() {
		when(admin.leAlquiloA(propietario, fecha)).thenReturn(true);
		boolean puedeRecibirPuntuacion = inquilino.puedeRecibirPuntuacionComoInquilinoPor(propietario, fecha);
		assertTrue(puedeRecibirPuntuacion);
	}
	
	@Test
	void testPuntuarComoPropietario() {
		inquilino.setPerfilInquilino(perfilInquilino);
		when(admin.leAlquiloA(propietario, fecha)).thenReturn(true);
		propietario.puntuarComoPropietario(inquilino, cat, 5, fecha);
		verify(perfilInquilino).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testNoSeRealizaPuntuacion() {
		inquilino.setPerfilInquilino(perfilInquilino);
		when(admin.leAlquiloA(propietario, fecha)).thenReturn(false);
		propietario.puntuarComoPropietario(inquilino, cat, 5, fecha);
		verify(perfilInquilino, never()).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testTiempoComoUsuario() {
		inquilino.registrarse(sitio, LocalDate.of(2021, 1, 1));
		int dias = inquilino.tiempoComoUser(LocalDate.of(2021, 1, 10));
		assertEquals(9, dias);
	}
}

