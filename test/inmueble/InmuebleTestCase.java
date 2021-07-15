package inmueble;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Categorias.Categoria;
import administradorDeReservas.AdministadorDeReservasInquilino;
import perfiles.PerfilInmueble;
import periodo.PeriodoPrecio;
import politicasDeCancelacion.PoliticaDeCancelacion;
import reservas.Reserva;
import usuario.Usuario;

class InmuebleTestCase {
	private Usuario dueño;
	private Usuario inquilino;
	private AdministadorDeReservasInquilino admin;
	private Inmueble casa;
	private String tipoDeInmueble;
	private PoliticaDeCancelacion politica;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private int capacidad;
	private double precio;
	private List<String> servicios ;
	private List<IFoto> fotos;
	private IHora horarioCheckIn;
	private IHora horarioCheckOut;
	private List<FormaDePago> formasDePago;
	private FormaDePago efectivo;
	private FormaDePago tarjetaDeDebito;
	private FormaDePago tarjetaDeCredito;
	private PeriodoPrecio periodoPrecio1;
	private PeriodoPrecio periodoPrecio2;
	private LocalDate fecha1;
	private LocalDate fecha2;
	private LocalDate fecha3;
	private PerfilInmueble perfil;
	private Categoria cat;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private List<Reserva> reservas;
	
	@BeforeEach
	void setUp() throws Exception {;
	
		dueño = mock(Usuario.class);
		inquilino = mock(Usuario.class);
		admin = mock(AdministadorDeReservasInquilino.class);
		fecha1 = mock(LocalDate.class);
		fecha2 = mock(LocalDate.class);
		periodoPrecio1 = mock(PeriodoPrecio.class);
		perfil = mock(PerfilInmueble.class);
		cat = mock(Categoria.class);
		fecha3 = mock(LocalDate.class);
		periodoPrecio2 = mock(PeriodoPrecio.class);
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		reservas = new ArrayList<Reserva>();
		efectivo= mock(FormaDePago.class);
		tarjetaDeDebito= mock(FormaDePago.class);
		tarjetaDeCredito= mock(FormaDePago.class);
		formasDePago= new ArrayList<FormaDePago>();
		formasDePago.add(efectivo);
		formasDePago.add(tarjetaDeDebito);
		formasDePago.add(tarjetaDeCredito);
		
		
		casa = new Inmueble(dueño, tipoDeInmueble, superficie,pais,
				ciudad,direccion,servicios, capacidad,fotos,
				horarioCheckIn, horarioCheckOut, formasDePago,3000d, politica);
		
		casa.establecerPeriodosConPrecios(periodoPrecio1);
		casa.establecerPeriodosConPrecios(periodoPrecio2);
		
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		when(periodoPrecio2.getPrecio()).thenReturn(4000d);
	}

	
	@Test 
	void establecerPeriodosConPreciosTestCase(){
		List <PeriodoPrecio> periodosYPrecios = new ArrayList<PeriodoPrecio>();
		periodosYPrecios.add(periodoPrecio1);
		periodosYPrecios.add(periodoPrecio2);
		assertEquals(periodosYPrecios, casa.getPeriodosYPrecios());
	}
	
	@Test
	void perteneceLaFechaAAlgunPeriodoTestCase() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(true);
		
		assertTrue(casa.perteneceLaFechaAAlgunPeriodo(fecha3));
	}
	
	@Test
	void LaFechaNoPerteneceANingunPeriodoTestCase() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(false);
		
		assertFalse(casa.perteneceLaFechaAAlgunPeriodo(fecha3));
	}
	
	@Test
	void testPrecioParaLaFechaCuandoPerteneceAlPeriodo1() {
		
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		
		double precio = casa.obtenerElPrecioParaLaFecha(fecha1);
		
		assertEquals(precio,2000d);
	}
	
	@Test
	void TestPrecioPorDefecto() {
		
		double precio = casa.getPrecioPorDefecto();
		
		assertEquals(precio,3000d);
	}
	
	
	@Test
	void TestPrecioParaFechasDelPeriodo1() {
		fecha1 = LocalDate.of(2021, 7, 14);
		fecha2 = LocalDate.of(2021, 7, 15);
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio1.perteneceLaFecha(fecha2)).thenReturn(true);
		
		double precio = casa.valorPorRangoDeFechas(fecha1, fecha2);
		
		assertEquals(4000d, precio);
	}
	
	@Test
	void TestPrecioParaFechaPeriodo1YFechaPeriodo2() {
		fecha1 = LocalDate.of(2021, 7, 14);
		fecha2 = LocalDate.of(2021, 7, 15);
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio2.perteneceLaFecha(fecha2)).thenReturn(true);
		
		double precio = casa.valorPorRangoDeFechas(fecha1, fecha2);
		
		assertEquals(precio, 6000d);
	}
	
	@Test
	void TestPrecioParaFechaQueNoPertenecenANingunPeriodo() {
		fecha1 = LocalDate.of(2021, 7, 14);
		fecha2 = LocalDate.of(2021, 7, 15);
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(false);
		when(periodoPrecio1.perteneceLaFecha(fecha2)).thenReturn(false);
		
		double precio = casa.valorPorRangoDeFechas(fecha1, fecha2);
		
		assertEquals(precio, 6000d);
	}
	
	
	@Test
	void testPuedeRecibirPuntaje() {
		when(dueño.getAdmin()).thenReturn(admin);
		when(admin.alquilo(casa, fecha1)).thenReturn(true);
		boolean puedeRecibirPuntos = casa.puedeRecibirPuntuacionPorEstadiaPor(dueño, fecha1);
		assertTrue(puedeRecibirPuntos);
	}
	
	@Test
	void testRecibePuntaje() {
		casa.setPerfilInmueble(perfil);
		casa.recibirPuntuacionPorEstadia(cat, 5);
		verify(perfil).recibirPuntuacion(cat, 5);
	}
	
	@Test
	void testAgregarReservas() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		reservas.add(reserva1);
		reservas.add(reserva2);
		List<Reserva> reservasDeCasa = casa.getReservas();
		assertEquals(reservas, reservasDeCasa);
	}
	
	@Test
	void testNoEstaDisponible() {
		casa.agregarReserva(reserva1);
		when(reserva1.ocupaAlgunaFechaDeRango(fecha1, fecha2)).thenReturn(true);
		boolean noEstaDisponible = casa.estaDisponible(fecha1, fecha2);
		assertFalse(noEstaDisponible);
	}
	
	@Test
	void testEstaDisponible() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.ocupaAlgunaFechaDeRango(fecha1, fecha2)).thenReturn(false);
		when(reserva2.ocupaAlgunaFechaDeRango(fecha1, fecha2)).thenReturn(false);
		boolean estaDisponible = casa.estaDisponible(fecha1, fecha2);
		assertTrue(estaDisponible);
	}
	
	@Test
	void testEstaReservada() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.estaConfirmada()).thenReturn(false);
		when(reserva2.estaConfirmada()).thenReturn(true);
		boolean estaReservada = casa.estaReservado();
		assertTrue(estaReservada);
	}
	
	@Test
	void testNoEstaReservada() {
		boolean noEstaReservada = casa.estaReservado();
		assertFalse(noEstaReservada);
	}
	
	@Test
	void testVecesQueFueAlquilado() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.estaConfirmada()).thenReturn(false);
		when(reserva2.estaConfirmada()).thenReturn(true);
		int veces = casa.vecesQueFueAlquilado();
		assertEquals(1, veces);
	}
	
	@Test
	void testReservasQueImposibilitanAUnaNuevaReserva() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.imposibilitaReserva(reserva3)).thenReturn(true);
		when(reserva2.imposibilitaReserva(reserva3)).thenReturn(false);
		reservas.add(reserva1);
		List<Reserva>reservasQueImposibilitan = casa.reservasQueImposibilitan(reserva3);
		assertEquals(reservas, reservasQueImposibilitan);
	}
	
	@Test
	void testEncolarNuevaReservaEnReservasCorrectas() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.imposibilitaReserva(reserva3)).thenReturn(true);
		when(reserva2.imposibilitaReserva(reserva3)).thenReturn(false);
		casa.encolar(reserva3);
		verify(reserva1).encolarReserva(reserva3);
	}
	
	@Test
	void testHayReservasQueImposibilitenNuevaReserva() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.imposibilitaReserva(reserva3)).thenReturn(true);
		when(reserva2.imposibilitaReserva(reserva3)).thenReturn(false);
		boolean noHayReservasQueImposibilitan = casa.noHayReservasQueImposibiliten(reserva3);
		assertFalse(noHayReservasQueImposibilitan);
	}
	
	@Test
	void testNoHayReservasQueImposibilitenNuevaReserva() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.imposibilitaReserva(reserva3)).thenReturn(false);
		when(reserva2.imposibilitaReserva(reserva3)).thenReturn(false);
		boolean noHayReservasQueImposibilitan = casa.noHayReservasQueImposibiliten(reserva3);
		assertTrue(noHayReservasQueImposibilitan);
	}
	
	@Test
	void testRealizarSolicitudParaPrimeroDeLaCola() {
		when(reserva2.getInquilino()).thenReturn(inquilino);
		when(reserva1.primeraDeLaCola()).thenReturn(reserva2);
		casa.realizarSolicitudParaLaPrimeraDeLaCola(reserva1);
		verify(reserva2).recibirCola(reserva1);
		verify(inquilino).solicitarReserva(reserva2, casa);
	}
	
	@Test
	void testNoSeSolicitaLaReservaEncoladaCuandoHayOtraReservaQueLaImposibilita() {
		casa.agregarReserva(reserva1);
		when(reserva2.primeraDeLaCola()).thenReturn(reserva3);
		when(reserva1.imposibilitaReserva(reserva3)).thenReturn(true);
		casa.desencolarReserva(reserva2);
		verify(reserva2).sacarPrimeroDeLaCola();
	}
	
	@Test
	void testSePuedeRealizarPorReservaEncoladaEnReserva() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.primeraDeLaCola()).thenReturn(reserva3);
		when(reserva2.imposibilitaReserva(reserva3)).thenReturn(false);
		when(reserva1.tieneColaDeReservas()).thenReturn(true);
		when(reserva1.estaConfirmada()).thenReturn(true);
		boolean esPosible = casa.sePuedeRealizarSolicitudPorReservaEncoladaEn(reserva1);
		assertTrue(esPosible);
	}
	
	@Test
	void testDesencolarCuandoEsPosible() {
		casa.agregarReserva(reserva1);
		casa.agregarReserva(reserva2);
		when(reserva1.primeraDeLaCola()).thenReturn(reserva3);
		when(reserva2.imposibilitaReserva(reserva3)).thenReturn(false);
		when(reserva1.tieneColaDeReservas()).thenReturn(true);
		when(reserva1.estaConfirmada()).thenReturn(true);
		when(reserva3.getInquilino()).thenReturn(inquilino);
		casa.desencolarReserva(reserva1);
		verify(reserva3).recibirCola(reserva1);
		verify(inquilino).solicitarReserva(reserva3, casa);
	}
	
	@Test
	void testInmuebleTieneReserva() {
		when(reserva1.estaConfirmada()).thenReturn(true);
		boolean tieneReserva = casa.tieneReserva(reserva1);
		assertFalse(tieneReserva);
	}
	
	@Test
	void testFormasDePago() {
		List <FormaDePago> formasEsperadas= new ArrayList<FormaDePago>();
		formasEsperadas.add(efectivo);
		formasEsperadas.add(tarjetaDeDebito);
		formasEsperadas.add(tarjetaDeCredito);
		assertEquals(casa.getFormasDePago(), formasEsperadas);
		
		
	}
}
