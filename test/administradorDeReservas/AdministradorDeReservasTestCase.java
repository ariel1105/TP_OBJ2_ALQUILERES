package administradorDeReservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import politicasDeCancelacion.PoliticaDeCancelacion;
import reservas.Reserva;
import usuario.Usuario;

class AdministradorDeReservasTestCase {
	
	private AdministadorDeReservasInquilino admin;
	private Usuario propietario;
	private Reserva reserva;
	private Reserva reserva2;
	private LocalDate fechaActual;
	private LocalDate fechaReserva;
	private LocalDate fechaReserva2;
	private List <Reserva> reservas;
	private PoliticaDeCancelacion politica;
	private Inmueble inmueble;
	private Inmueble inmueble2;
	

	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reservas = new ArrayList<Reserva>();
		reservas.add(reserva);
		fechaActual = mock(LocalDate.class);
		fechaReserva = mock(LocalDate.class);
		fechaReserva2 = mock(LocalDate.class); 
		admin = new AdministadorDeReservasInquilino();
		politica = mock(PoliticaDeCancelacion.class);
		propietario = mock(Usuario.class);
		inmueble = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
	}

	@Test
	void testAgregarReserva() {
		admin.ingresar(reserva);
		List <Reserva> reservasIngresadas = admin.getReservas();
		assertEquals(reservas, reservasIngresadas);
	}
	
	@Test
	void testReservasConfirmadas() {
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva2.estaConfirmada()).thenReturn(false);
		List<Reserva> reservasConfirmadas = admin.reservasConfirmadas();
		assertEquals(reservas, reservasConfirmadas);
	}
	
	@Test
	void testReservasFuturas() {
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva2.estaConfirmada()).thenReturn(false);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(true);
		List<Reserva> reservasFuturas = admin.reservasFuturas(fechaActual);
		assertEquals(reservas, reservasFuturas);
	}
	
	@Test
	void testNingunaReservaEsFuturaPorqueNoEstanConfirmadas() {
		when(reserva.esReservaFutura(fechaActual)).thenReturn(true);
		when(reserva2.esReservaFutura(fechaActual)).thenReturn(true);
		when(reserva.estaConfirmada()).thenReturn(false);
		when(reserva2.estaConfirmada()).thenReturn(false);
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		List<Reserva> sinReservasFuturas = admin.reservasFuturas(fechaActual);
		reservas.remove(reserva);
		assertEquals(reservas, sinReservasFuturas);
	}
	
	@Test
	void testReservasConcretadas() {
		when(reserva.esReservaFutura(fechaActual)).thenReturn(false);
		when(reserva2.esReservaFutura(fechaActual)).thenReturn(true);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva2.estaConfirmada()).thenReturn(false);
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		List<Reserva> reservasConcretadas = admin.reservasConcretadas(fechaActual);
		assertEquals(reservas, reservasConcretadas);
	}
	
	@Test
	void testReservasDeCiudad() {
		when(reserva.esDeCiudad("Pinamar")).thenReturn(true);
		when(reserva2.esDeCiudad("Merlo")).thenReturn(false);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva2.estaConfirmada()).thenReturn(false);
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		List<Reserva> reservasDeCiudad = admin.reservasDeCiudad("Pinamar");
		assertEquals(reservas, reservasDeCiudad);
	}
	
	@Test
	void testCiudadesEnLasQueHayReservas() {
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva2.estaConfirmada()).thenReturn(true);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(reserva2.getInmueble()).thenReturn(inmueble2);
		when(inmueble.getCiudad()).thenReturn("Pinamar");
		when(inmueble2.getCiudad()).thenReturn("Merlo");
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		List <String> ciudades = new ArrayList<String>();
		ciudades.add("Pinamar");
		ciudades.add("Merlo");
		List <String> ciudadesEnReserva = admin.ciudadesConReserva();
		assertEquals(ciudades, ciudadesEnReserva);
	}
	
	@Test
	void testCancelarReservaConFechaActual() {
		admin.ingresar(reserva);
		admin.cancelarReserva(reserva, fechaActual);
		verify(reserva).iniciarCancelacion(fechaActual);
	}
	
	@Test
	void testCancelarReservaSinHaberlaRegistado() {
		admin.cancelarReserva(reserva, fechaActual);
		verify(reserva, never()).iniciarCancelacion(fechaActual);
	}
	
	@Test
	void testCantidadDeReservas() {
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva2.estaConfirmada()).thenReturn(true);
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		int cantidad = admin.cantidadeDeReservas();
		assertEquals(2, cantidad);
	}
	
	@Test
	void leAlquiloAPropietario() {
		admin.ingresar(reserva);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(false);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		boolean alquilo = admin.leAlquiloA(propietario, fechaActual);
		assertTrue(alquilo);
	}
	
	@Test
	void NoLeAlquiloTodaviaPorqueNoPasoLaFechaDeLaReserva() {
		admin.ingresar(reserva);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(true);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		boolean alquilo = admin.leAlquiloA(propietario, fechaActual);
		assertFalse(alquilo);
	}
	
	@Test
	void NoLeAlquiloPorqueEsUnaReservaQueNoEstaConfirmada() {
		admin.ingresar(reserva);
		when(reserva.estaConfirmada()).thenReturn(false);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(false);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getPropietario()).thenReturn(propietario);
		boolean alquilo = admin.leAlquiloA(propietario, fechaActual);
		assertFalse(alquilo);
	}
	
	@Test
	void testAlquiloInmueble() {
		admin.ingresar(reserva);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(false);
		when(reserva.getInmueble()).thenReturn(inmueble);
		boolean alquilo = admin.alquilo(inmueble, fechaActual);
		assertTrue(alquilo);
	}
	
	@Test
	void testTodaviaNoUsoLaReservaPorElInmueble() {
		admin.ingresar(reserva);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(true);
		when(reserva.getInmueble()).thenReturn(inmueble);
		boolean alquilo = admin.alquilo(inmueble, fechaActual);
		assertFalse(alquilo);		
	}
	
	@Test
	void testNoAlquiloInmueblePorqueLaReservaNoEstaConfirmada() {
		admin.ingresar(reserva);
		when(reserva.estaConfirmada()).thenReturn(false);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(false);
		when(reserva.getInmueble()).thenReturn(inmueble);
		boolean alquilo = admin.alquilo(inmueble, fechaActual);
		assertFalse(alquilo);
	}
	
	@Test
	void testNoAlquiloElInmueblePedido() {
		admin.ingresar(reserva);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva.esReservaFutura(fechaActual)).thenReturn(false);
		when(reserva.getInmueble()).thenReturn(inmueble2);
		boolean alquilo = admin.alquilo(inmueble, fechaActual);
		assertFalse(alquilo);
	}
}
