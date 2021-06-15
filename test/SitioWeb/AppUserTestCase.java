package SitioWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import sitio.AppUser;
import sitio.Sitio;
import sitio.SitioWeb;

class AppUserTestCase {
	
	private AppUser app;
	private Sitio sitio;
	private Inmueble inmueble1;
	
	private List<String> inmuebles= new ArrayList<String>();

	@BeforeEach
	void setUp() {
		
		
		inmuebles.add("Departamento");
		inmuebles.add("Hotel");
		inmuebles.add("Cabaña");
		inmuebles.add("Duplex");
		
		
		sitio= new Sitio();
		
		when(app.getInmueblesDeInteres()).thenReturn(inmuebles);
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
