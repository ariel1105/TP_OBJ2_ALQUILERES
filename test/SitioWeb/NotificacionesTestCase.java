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
	private Inmueble inmueble;
	private List<Inmueble> inmuebles= new ArrayList<Inmueble>();
	
	
	@BeforeEach
	void setUp() {


		
		inmueble= new Inmueble(null, "Duplex", 0, null, null, null, null, 0, null, null, null, null, 0, null);
		inmuebles.add(inmueble);
		sitioWeb= new SitioWeb(inmuebles);
		appUser= new AppUser(inmuebles);
	}
	
	
	@Test
	void test() {
		assertEquals(inmueble.getListenersPaginas().size(), 2);
		inmueble.notificar("Cancelacion");
		inmueble.notificar("Baja de precio");
		assertEquals(sitioWeb.getInmueblesConInteres(), inmuebles);
		assertEquals(appUser.getInmueblesConInteres(), inmuebles);
		
	}

}
