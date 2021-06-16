package SitioWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Suscripciones.AppUser;
import Suscripciones.INotify;
import Suscripciones.PopUpWindow;
import Suscripciones.SitioWeb;
import inmueble.Inmueble;
import sitio.Sitio;

class SitioWebTestCase {
	
	private SitioWeb trivago;
	private SitioWeb trivago2;
	private AppUser trivagoMobile;
	private AppUser trivagoMobile2;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	
	private List<Inmueble> inmuebles= new ArrayList<Inmueble>();
	private List<INotify> listeners= new ArrayList<INotify>();
	
	

	@BeforeEach
	void setUp() {

		trivagoMobile= mock(AppUser.class);
		trivago= mock(SitioWeb.class);
		
		inmueble1= new Inmueble(null, "Depto", 0, null, null, null, null, 0, null, null, null, null, 0, null);
		inmueble2= new Inmueble(null, "Hola", 0, null, null, null, null, 0, null, null, null, null, 0, null);
		inmuebles.add(inmueble1);
		inmuebles.add(inmueble2);
		listeners.add(trivago);
		trivago2= new SitioWeb(inmuebles);
		trivagoMobile2= new AppUser(inmuebles);
		
		when(trivago.getInmueblesConInteres()).thenReturn(inmuebles);
		when(trivagoMobile.getInmueblesConInteres()).thenReturn(inmuebles);

		
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
		inmueble1.cambiarPrecio();
		verify(trivago).publish("No te pierdas esta oferta: Un inmueble Depto a tan solo 50000.0 pesos!");
		
		
	}
	
	@Test
	void testAlertaEnAplicacion() {
		inmueble1.addObserver(trivagoMobile);
		inmueble1.addObserver(trivago);
		inmueble1.cancelarReserva();
	
		verify(trivagoMobile).popUp("El/la Depto que te interesa se ha liberado! Corre a reservarlo!", "Rojo", 3);
		
	}

}
