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
import inmueble.Inmueble;
import periodo.PeriodoPrecio;
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
	private PeriodoPrecio periodo1;
	private PeriodoPrecio periodo2;
	private PeriodoPrecio periodo3;
	
	private List<Inmueble> inmuebles= new ArrayList<Inmueble>();
	private List<INotify> listeners= new ArrayList<INotify>();
	
	
	//Fechas
	private ArrayList<LocalDate> fechas1= new ArrayList<LocalDate>();
	

	@BeforeEach
	void setUp() {
		sitio1=mock(Sitio.class);
		trivagoMobile= mock(AppUser.class);
		trivago= mock(SitioWeb.class);
		usuario=new Usuario("Lautaro", "lautaro@gmail.com", "42500197");

		
		inmueble1= new Inmueble(null, "Depto", 0, null, null, null, null, 0, null, null, null, null, 50000.0, null);
		inmueble2= mock(Inmueble.class);
		inmuebles.add(inmueble1);
		inmuebles.add(inmueble2);
		listeners.add(trivago);
		trivago2= new SitioWeb(inmuebles);
		trivagoMobile2= new AppUser(inmuebles);
		
		when(trivago.getInmueblesConInteres()).thenReturn(inmuebles);
		when(trivagoMobile.getInmueblesConInteres()).thenReturn(inmuebles);

		//Seteo el precio y fechas de los periodos
		fechas1.add(LocalDate.of(2021, 06, 18));
		fechas1.add(LocalDate.of(2021, 06, 19));
		fechas1.add(LocalDate.of(2021, 06, 20));
		
		usuario.publicar(inmueble1, sitio1);
		
		periodo1= new PeriodoPrecio(40000.0, fechas1);
		inmueble1.establecerPeriodosConPrecios(periodo1);
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
		inmueble1.cancelarReserva();
	
		verify(trivagoMobile).popUp("El/la Depto que te interesa se ha liberado! Corre a reservarlo!", "Rojo", 3);
		
	}
	
///fgdds

}
