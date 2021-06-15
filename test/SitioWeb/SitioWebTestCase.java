package SitioWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import sitio.Sitio;
import sitio.SitioWeb;

class SitioWebTestCase {
	
	private SitioWeb trivago;
	private Sitio sitio;
	private Inmueble inmueble1;
	
	private List<String> inmuebles= new ArrayList<String>();

	@BeforeEach
	void setUp() {
		
		
		inmuebles.add("Departamento");
		inmuebles.add("Hotel");
		inmuebles.add("Cabaña");
		inmuebles.add("Duplex");
		
		trivago= mock(SitioWeb.class);
		sitio= new Sitio();
		inmueble1= mock(Inmueble.class);
		
		
	}

	@Test
	void testAddObserver() {
		sitio.addObserver(trivago);
		//assertEquals(sitio.countObservers(), 1);
		
	}
	
	@Test 
	void cambiarPrecio() {
		sitio.addObserver(trivago);
		when(trivago.getInmueblesConInteres()).thenReturn(inmuebles);
		sitio.notificarBajaDePrecio("Departamento", 50000.0);
		verify(trivago).publish("El inmueble Departamento esta a solo 50000.0 pesos!.");
		
		
	}
	

}
