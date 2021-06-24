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
import sitio.Categoria;
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
	private PerfilPropietario perfilDueño;
	private PerfilInquilino perfilInquilino;
	private Usuario propietario;
	
	@BeforeEach
	void setUp() throws Exception {
		admin = mock(AdministadorDeReservasInquilino.class);
		perfilDueño = mock(PerfilPropietario.class);
		perfilInquilino = mock(PerfilInquilino.class);
		inquilino = new Usuario("nombre", "mail", "telefono",admin);
		propietario = new Usuario("nombre2", "mail2", "telefono2",admin);
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
		propietario.getReservasConfirmadasYEncoladas().put(reserva2, reservas);
	}

	@Test
	void testRegistrarse() {
		inquilino.registrarse(sitio);
		verify(sitio).registrarUsuario(inquilino);
	}
	
	@Test
	void testPublicarSinRegistrarsePreviamente() {
		when(sitio.elUsuarioEstaRegistrado(propietario)).thenReturn(false);
		propietario.publicar(inmueble, sitio);
		verify(sitio, never()).publicar(inmueble, propietario);
	}
	
	@Test
	void testPublicarConRegistroPrevio() {
		when(sitio.elUsuarioEstaRegistrado(propietario)).thenReturn(true);
		propietario.publicar(inmueble, sitio);
		verify(sitio).publicar(inmueble, propietario);
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
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(true);
		inquilino.solicitarReserva(reserva);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		assertEquals(reservas, reservasPendientesDeConfirmacion);
	}
	
	@Test
	void testSolicitarReservaSeNiega() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(false);
		inquilino.solicitarReserva(reserva);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		ArrayList<Reserva> sinReservas = new ArrayList<Reserva>();
		assertEquals(sinReservas, reservasPendientesDeConfirmacion);
	}
	
	@Test
	void testRecibirConfirmacionReserva() {
		inquilino.recibirConfirmacion(reserva);
		verify(admin).ingresar(reserva);
	}
	
	@Test
	void testConfirmarReserva() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(reserva.getInquilino()).thenReturn(inquilino);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(true);
		inquilino.solicitarReserva(reserva);
		propietario.confirmar(reserva, sitio);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		ArrayList<Reserva> sinReservas = new ArrayList<Reserva>();
		assertEquals(sinReservas, reservasPendientesDeConfirmacion);
		verify(reserva).confirmarseEn(sitio);
		verify(admin).ingresar(reserva);
	}
	
	@Test
	void testNoSeConfirmaReservaSinSolicitarse() {
		propietario.confirmar(reserva, sitio);
		verify(reserva, never()).confirmarseEn(sitio);
		verify(admin, never()).ingresar(reserva);
	}
	
	@Test
	void testCantidadDeReservas() {
		when(admin.cantidadeDeReservas()).thenReturn(5);
		int vecesQueInquilinoAlquilo = inquilino.vecesQueAlquilaron();
		assertEquals(5, vecesQueInquilinoAlquilo);
	}
	
	@Test
	void testPropietarioPuedeRecibirPuntuacionDeInquilino() {
		when(admin.leAlquiloA(propietario)).thenReturn(true);
		boolean puedeRecibirPuntuacion = propietario.puedeRecibirPuntuacionPorEstadiaPor(inquilino);
		assertTrue(puedeRecibirPuntuacion);
	}
	
	@Test
	void testRecibirPuntuacionPorEstadia() {
		propietario.setPerfilPropietario(perfilDueño);
		propietario.recibirPuntuacionPorEstadia(cat, 5);
		verify(perfilDueño).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testPuntuarComoInquilino() {
		propietario.setPerfilPropietario(perfilDueño);
		when(admin.leAlquiloA(propietario)).thenReturn(true);
		inquilino.puntuarComoInquilino(propietario, cat, 5);
		verify(perfilDueño).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testNoSeRealizaPuntuacionComoInquilino() {
		propietario.setPerfilPropietario(perfilDueño);
		when(admin.leAlquiloA(propietario)).thenReturn(false);
		inquilino.puntuarComoInquilino(propietario, cat, 5);
		verify(perfilDueño, never()).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testPuntuarInmueble() {
		when(inmueble.puedeRecibirPuntuacionPorEstadiaPor(inquilino)).thenReturn(true);
		inquilino.puntuarComoInquilino(inmueble, cat, 5);
		verify(inmueble).recibirPuntuacionPorEstadia(cat, 5);
	}
	
	@Test
	void testNoSeRealizaPuntuacionEnInmueble() {
		when(inmueble.puedeRecibirPuntuacionPorEstadiaPor(inquilino)).thenReturn(false);
		inquilino.puntuarComoInquilino(inmueble,cat,5);
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
		when(admin.leAlquiloA(propietario)).thenReturn(true);
		boolean puedeRecibirPuntuacion = inquilino.puedeRecibirPuntuacionComoInquilinoPor(propietario);
		assertTrue(puedeRecibirPuntuacion);
	}
	
	@Test
	void testPuntuarComoDueño() {
		inquilino.setPerfilInquilino(perfilInquilino);
		when(admin.leAlquiloA(propietario)).thenReturn(true);
		propietario.puntuarComoPropietario(inquilino, cat, 5);
		verify(perfilInquilino).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testNoSeRealizaPuntuacion() {
		inquilino.setPerfilInquilino(perfilInquilino);
		when(admin.leAlquiloA(propietario)).thenReturn(false);
		propietario.puntuarComoPropietario(inquilino, cat, 5);
		verify(perfilInquilino, never()).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void obtenerReservaDeInmueble_ConFechas_() {
		reservas.add(reserva2);
		when(reserva2.esReservaQueImposibilita(reserva)).thenReturn(true);
		
		Reserva reservaEsperada = propietario.obtenerReservaQueImposibilitaReserva(reserva, reservas);
		
		assertEquals(reserva2, reservaEsperada);
	}
	
	@Test
	void obtenerReservasEncoladas() {
		
		when (reserva2.esReservaQueImposibilita(reserva3)).thenReturn(true);
		
		ArrayList<Reserva> reservasEncoladas = propietario.obtenerReservasEncoladas(reserva3);
		
		assertEquals(reservasEncoladas,reservas);
	}
	
	@Test
	void encolarReserva() {
		
		HashMap<Reserva, ArrayList<Reserva>> reservasEsperadas = new HashMap<Reserva, ArrayList<Reserva>>();
		reservas.add(reserva3);
		reservasEsperadas.put(reserva2, reservas);
		
		when (reserva2.esReservaQueImposibilita(reserva3)).thenReturn(true);
		
		propietario.encolarReserva(reserva3);
		
		HashMap<Reserva, ArrayList<Reserva>> reservasConfirmadasYEncoladas = propietario.getReservasConfirmadasYEncoladas();
		
		assertEquals(reservasConfirmadasYEncoladas,reservasEsperadas);
	}
	
	@Test
	void quieroReservarCuandoHayDisponibilidad() {
		
		when(inmueble.estaDisponible(diasDeReserva)).thenReturn(true);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(true);
		inquilino.solicitarReserva(reserva);
		ArrayList<Reserva> reservasPendientesDeConfirmacion = propietario.getReservasPendientes();
		propietario.quieroReservar(reserva3);
		assertEquals(reservas, reservasPendientesDeConfirmacion);
		
	}
	

}
