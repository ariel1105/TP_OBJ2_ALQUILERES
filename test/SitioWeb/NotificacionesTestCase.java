package SitioWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Suscripciones.AppUser;
import Suscripciones.INotify;
import Suscripciones.SitioWeb;
import inmueble.Inmueble;

class NotificacionesTestCase {

	private INotify sitioWeb;
	private INotify appUser;
	private Inmueble inmuebleS1;
	private Inmueble inmuebleS2;
	private Inmueble inmuebleS3;
	private Inmueble inmuebleS4;
	private Inmueble inmuebleA1;
	private Inmueble inmuebleA2;
	private Inmueble inmuebleA3;
	private Inmueble inmuebleA4;
	private List<Inmueble> inmuebles= new ArrayList<Inmueble>();
	
	
	@BeforeEach
	void setUp() {


		
		inmuebleS1= new Inmueble(null, "Duplex", 0, null, null, null, null, 0, null, null, null, null, 0, null);
		inmuebles.add(inmuebleS1);
		sitioWeb= new SitioWeb(inmuebles);
		appUser= new AppUser(inmuebles);
	}
	
	
	@Test
	void test() {
		assertEquals(inmuebleS1.getListenersPaginas().size(), 2);
		inmuebleS1.notificar("Cancelacion");
		inmuebleS1.notificar("Baja de precio");
		assertEquals(sitioWeb.getInmueblesConInteres(), inmuebles);
		assertEquals(appUser.getInmueblesConInteres(), inmuebles);
		
	}

}
