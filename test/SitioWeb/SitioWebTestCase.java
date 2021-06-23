package SitioWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Suscripciones.AppUser;
import Suscripciones.INotify;
import Suscripciones.PopUpWindow;
import Suscripciones.SitioWeb;
import administradorDeReservas.AdministadorDeReservasInquilino;
import inmueble.DatosDePago;
import inmueble.Inmueble;
import periodo.PeriodoPrecio;
import politicasDeCancelacion.PoliticaDeCancelacion;
import reservas.Reserva;
import sitio.Sitio;
import usuario.Usuario;

class SitioWebTestCase {
	
	private Sitio sitio1;
	private SitioWeb trivago;
	private SitioWeb trivago2;
	private AppUser trivagoMobile;
	private AppUser trivagoMobile2;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	
	private Usuario usuario;
	private AdministadorDeReservasInquilino admin;
	private PeriodoPrecio periodo1;
	private PeriodoPrecio periodo2;
	private PeriodoPrecio periodo3;
	private Reserva reserva;
	
	private List<Inmueble> inmuebles= new ArrayList<Inmueble>();
	private List<INotify> listeners= new ArrayList<INotify>();
	private ArrayList<String> servicios= new ArrayList<String>();
	
	//Fechas
	private ArrayList<LocalDate> fechas1= new ArrayList<LocalDate>();
	private ArrayList<Usuario> usuariosRegistrados= new ArrayList<Usuario>();
	private DatosDePago datosDePago;
	private PoliticaDeCancelacion politica;

	@BeforeEach
	void setUp() {
		sitio1= new Sitio();
		trivagoMobile= mock(AppUser.class);
		trivago= mock(SitioWeb.class);
		admin = new AdministadorDeReservasInquilino(LocalDate.now());
		usuario=new Usuario("Lautaro", "lautaro@gmail.com", "42500197", admin);
		usuario.registrarse(sitio1);
		usuariosRegistrados.add(usuario);
		inmueble1= new Inmueble(null, "Depto", 0, null, null, null, 0, null, null, null, null, 50000.0, null);
		inmueble2= mock(Inmueble.class);
		inmuebles.add(inmueble1);
		inmuebles.add(inmueble2);
		listeners.add(trivago);
		trivago2= new SitioWeb(inmuebles);
		trivagoMobile2= new AppUser(inmuebles);
		politica= mock(PoliticaDeCancelacion.class);
		datosDePago= mock(DatosDePago.class);
		reserva= new Reserva(usuario, inmueble1, fechas1, datosDePago, politica);
		
		
		when(trivago.getInmueblesConInteres()).thenReturn(inmuebles);
		when(trivagoMobile.getInmueblesConInteres()).thenReturn(inmuebles);

		//Seteo el precio y fechas de los periodos
		fechas1.add(LocalDate.of(2021, 06, 18));
		fechas1.add(LocalDate.of(2021, 06, 19));
		fechas1.add(LocalDate.of(2021, 06, 21));
		fechas1.add(LocalDate.of(2021, 06, 22));
		
		usuario.publicar(inmueble1, sitio1, servicios);
		
		periodo1= new PeriodoPrecio(40000.0, fechas1);
		inmueble1.establecerPeriodosConPrecios(periodo1);
		
		servicios.add("WIFI");
		servicios.add("Aire acondicionado");
		servicios.add("Estufa");
		
		admin.ingresar(reserva);
	}


	@Test
	void agregarObservador() {
		assertEquals(trivago2.getInmueblesConInteres().size(), 2);
		assertEquals(trivagoMobile2.getInmueblesConInteres().size(), 2);
		assertEquals(inmueble1.getListenersPaginas().size(), 2);

	}
	
	@Test
	void testAlertaEnPagina() {
		inmueble1.addObserver(trivago);
		inmueble2.addObserver(trivago);
		usuario.actualizarPrecioAInmueble(inmueble1);
		verify(trivago).publish("No te pierdas esta oferta: Un inmueble Depto a tan solo 40000.0 pesos!");
		
		
	}
	
	@Test
	void testAlertaEnAplicacion() {
		inmueble1.addObserver(trivagoMobile);
		inmueble1.addObserver(trivago);
		admin.cancelarReserva(reserva, LocalDate.of(2021, 06, 22));
		verify(trivagoMobile).popUp("El/la Depto que te interesa se ha liberado! Corre a reservarlo!", "Rojo", 3);
		
	}

}
