
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
	private PerfilPropietario perfilDue�o;
	private PerfilInquilino perfilInquilino;
	private Usuario propietario;
	
	@BeforeEach
	void setUp() throws Exception {
		admin = mock(AdministadorDeReservasInquilino.class);
		perfilDue�o = mock(PerfilPropietario.class);
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
		propietario.setPerfilPropietario(perfilDue�o);
		propietario.recibirPuntuacionPorEstadia(cat, 5);
		verify(perfilDue�o).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testPuntuarComoInquilino() {
		propietario.setPerfilPropietario(perfilDue�o);
		when(admin.leAlquiloA(propietario)).thenReturn(true);
		inquilino.puntuarComoInquilino(propietario, cat, 5);
		verify(perfilDue�o).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testNoSeRealizaPuntuacionComoInquilino() {
		propietario.setPerfilPropietario(perfilDue�o);
		when(admin.leAlquiloA(propietario)).thenReturn(false);
		inquilino.puntuarComoInquilino(propietario, cat, 5);
		verify(perfilDue�o, never()).recibirPuntuacion(cat, 5);
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
	void testPuntuarComoDue�o() {
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
	void obtenerReservaQueImposibilita() {
		reservas.add(reserva2);
		when(reserva2.esReservaQueImposibilita(reserva)).thenReturn(true);
		
		Reserva reservaEsperada = propietario.obtenerReservaQueImposibilitaReserva(reserva, reservas);
		
		assertEquals(reserva2, reservaEsperada);
	}
	
	@Test
	void obtenerReservasEncoladas() {
		
		
		propietario.agregarReservaAConfirmadas(reserva3);
		reservas.remove(reserva);
		when(reserva3.esReservaQueImposibilita(reserva)).thenReturn(true);
		ArrayList<Reserva> reservasEncoladas = propietario.obtenerReservasEncoladasParaAgregar(reserva);
		
		assertEquals(reservas,reservasEncoladas);
	}
	
	@Test
	void encolarReserva() {
		
		HashMap<Reserva, ArrayList<Reserva>> reservasEsperadas = new HashMap<Reserva, ArrayList<Reserva>>();
		reservasEsperadas.put(reserva2, reservas);
		
		when (reserva2.esReservaQueImposibilita(reserva)).thenReturn(true);
		
		propietario.agregarReservaAConfirmadas(reserva2);
		propietario.encolarReserva(reserva);
		
		HashMap<Reserva, ArrayList<Reserva>> reservasConfirmadasYEncoladas = propietario.getReservasConfirmadasYEncoladas();
		
		assertEquals(reservasEsperadas,reservasConfirmadasYEncoladas);
	}
	
	@Test
	void iniciarTramiteDeReservaCuandoHayDisponibilidad() {
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getFechas()).thenReturn(diasDeReserva);
		when(inmueble.estaDisponible1(diasDeReserva)).thenReturn(true);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(reserva.getDatosDePago()).thenReturn(datosDePago);
		when(datosDePago.sonDatosAdmitidosPara(inmueble)).thenReturn(true);
		inquilino.iniciarTramiteDeReserva(reserva);
		ArrayList<Reserva> reservasConfirmadas = propietario.getReservasConfirmadas();
		
		assertEquals(reservas, reservasConfirmadas);
		
	}
	
	@Test
	void IniciarTramiteDeReservaCuandoNoHayDisponibilidadTestCase(){
		when(reserva.getFechas()).thenReturn(diasDeReserva);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(inmueble.estaDisponible1(diasDeReserva)).thenReturn(false);
		when(reserva2.esReservaQueImposibilita(reserva)).thenReturn(true);
		
		HashMap<Reserva, ArrayList<Reserva>> reservasEsperadas = new HashMap<Reserva, ArrayList<Reserva>>();
		reservasEsperadas.put(reserva2, reservas);
		propietario.agregarReservaAConfirmadas(reserva2);
		inquilino.iniciarTramiteDeReserva(reserva);
		
		HashMap<Reserva, ArrayList<Reserva>> reservasConfirmadasYEncoladas = propietario.getReservasConfirmadasYEncoladas();
		
		assertEquals(reservasEsperadas,reservasConfirmadasYEncoladas);
		
	}
	
	@Test
	void agregarColaDeReservasTestCase() {
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		
		propietario.agregarColaDeReservas(reserva,reservas);
		
		HashMap<Reserva, ArrayList<Reserva>> reservasEsperadas = new HashMap<Reserva, ArrayList<Reserva>>();
		reservasEsperadas.put(reserva, reservas);

		HashMap<Reserva, ArrayList<Reserva>> reservasConfirmadasYEncoladas = propietario.getReservasConfirmadasYEncoladas();
		
		assertEquals(reservasConfirmadasYEncoladas,reservasEsperadas);
		
	}
	
	@Test
	void iniciarTramiteParaElPrimeroDeLaFila() {
		when(reserva2.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getFechas()).thenReturn(diasDeReserva);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(inmueble.estaDisponible1(diasDeReserva)).thenReturn(true);
		
		reservas.add(reserva3);
		propietario.agregarReservaAConfirmadas(reserva2);
		propietario.agregarColaDeReservas(reserva2, reservas);
		
		propietario.iniciarTramiteParaElPrimeroDeLaFila(reserva2);
		
		HashMap<Reserva, ArrayList<Reserva>> reservasEsperadas = new HashMap<Reserva, ArrayList<Reserva>>();
		ArrayList<Reserva> encoladas = new ArrayList<Reserva>();
		encoladas.add(reserva3);
		reservasEsperadas.put(reserva2, encoladas);
		reservasEsperadas.put(reserva, encoladas);
		
		assertEquals(reservasEsperadas, propietario.getReservasConfirmadasYEncoladas());
		
	}
	
	@Test
	void tieneDisponible() {
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(reserva.algunaDeLasFechasEstaOcupada(diasDeReserva)).thenReturn(true);
		when(reserva2.algunaDeLasFechasEstaOcupada(diasDeReserva)).thenReturn(false);
		
		propietario.agregarReservaAConfirmadas(reserva);
		propietario.agregarReservaAConfirmadas(reserva2);
		
		boolean estaDisp = propietario.tieneDisponible(inmueble, diasDeReserva);
		
		assertFalse(estaDisp);
	}
	
	@Test
	void eliminarReserva() {
		when(reserva2.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva.getFechas()).thenReturn(diasDeReserva);
		when(inmueble.getPropietario()).thenReturn(propietario);
		when(inmueble.estaDisponible(diasDeReserva)).thenReturn(true);
		
		propietario.agregarReservaAConfirmadas(reserva2);
		propietario.agregarColaDeReservas(reserva2, reservas);
		propietario.eliminarReserva(reserva2);
		HashMap<Reserva, ArrayList<Reserva>> reservasConfirmadas = propietario.getReservasConfirmadasYEncoladas();
		assertFalse(reservasConfirmadas.containsKey(reserva2));
		assertTrue(reservasConfirmadas.containsKey(reserva));
	}
	

}
