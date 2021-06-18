package administradorDeReservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import politicasDeCancelacion.PoliticaDeCancelacion;
import reservas.Reserva;

class AdministradorDeReservasTestCase {
	
	private AdministadorDeReservasInquilino admin;
	private Reserva reserva;
	private Reserva reserva2;
	private LocalDate fechaActual;
	private LocalDate fechaReserva;
	private LocalDate fechaReserva2;
	private ArrayList <Reserva> reservas;
	private PoliticaDeCancelacion politica;
	

	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reservas = new ArrayList<Reserva>();
		reservas.add(reserva);
		fechaActual = mock(LocalDate.class);
		fechaReserva = mock(LocalDate.class);
		fechaReserva2 = mock(LocalDate.class); 
		admin = new AdministadorDeReservasInquilino(fechaActual);
		politica = mock(PoliticaDeCancelacion.class); 
	}

	@Test
	void testAgregarReserva() {
		admin.ingresar(reserva);
		ArrayList <Reserva> reservasIngresadas = admin.getTodasLasReservas();
		assertEquals(reservas, reservasIngresadas);
	}
	
	@Test
	void testEsReservaFutura() {
		when(reserva.primerDia()).thenReturn(fechaReserva);
		when(fechaActual.isBefore(fechaReserva)).thenReturn(true);
		boolean esReservaFutura = admin.esReservaFutura(reserva);
		assertTrue(esReservaFutura);
	}
	
	@Test
	void testReservasFuturas() {
		when(reserva.primerDia()).thenReturn(fechaReserva);
		when(fechaActual.isBefore(fechaReserva)).thenReturn(true);
		when(reserva2.primerDia()).thenReturn(fechaReserva2);
		when(fechaActual.isBefore(fechaReserva2)).thenReturn(false);
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		ArrayList<Reserva> reservasFuturas = admin.reservasFuturas();
		assertEquals(reservas, reservasFuturas);
	}
	
	@Test
	void testNingunaReservaEsFutura() {
		when(reserva.primerDia()).thenReturn(fechaReserva);
		when(fechaActual.isBefore(fechaReserva)).thenReturn(false);
		when(reserva2.primerDia()).thenReturn(fechaReserva2);
		when(fechaActual.isBefore(fechaReserva2)).thenReturn(false);
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		ArrayList<Reserva> sinReservasFuturas = admin.reservasFuturas();
		ArrayList<Reserva> sinReservas = new ArrayList<Reserva>();
		assertEquals(sinReservasFuturas, sinReservas);
	}
	
	@Test
	void testReservasDeCiudad() {
		when(reserva.esDeCiudad("Pinamar")).thenReturn(true);
		when(reserva2.esDeCiudad("Merlo")).thenReturn(false);
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		ArrayList<Reserva> reservasDeCiudad = admin.reservasDeCiudad("Pinamar");
		assertEquals(reservas, reservasDeCiudad);
	}
	
	@Test
	void testCiudadesEnLasQueHayReservas() {
		when(reserva.ciudad()).thenReturn("Pinamar");
		when(reserva2.ciudad()).thenReturn("Merlo");
		admin.ingresar(reserva);
		admin.ingresar(reserva2);
		ArrayList <String> ciudades = new ArrayList<String>();
		ciudades.add("Pinamar");
		ciudades.add("Merlo");
		ArrayList <String> ciudadesEnReserva = admin.ciudadesConReserva();
		assertEquals(ciudades, ciudadesEnReserva);
	}
	
	@Test
	void testCancelarReservaConFechaActual() {
		admin.ingresar(reserva);
		admin.cancelarReserva(reserva, fechaActual);
		verify(reserva).IniciarCancelacion(fechaActual);
	}
	

}
