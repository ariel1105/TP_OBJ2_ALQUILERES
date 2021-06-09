package reservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AltaDeInmuebleTestCase {

	private Sitio sitio;
	private Publicacion publicacionDeCaba�a;
	private Inmueble caba�a;
	private Usuario propietarioCaba�a;
	
	
	@BeforeEach
	void setUp() {
		
		//sitio= new Sitio(); no lo puedo instanciar asi porque la clase es abstracta! Hay que cambiarla sino no puedo verificar lo de abajo

		publicacionDeCaba�a= mock(Publicacion.class);
		caba�a= mock(Inmueble.class);
		propietarioCaba�a= mock(Usuario.class);
		
		
		
		
	}
	@Test
	void test() {
		//sitio.altaDeInmueble(caba�a, propietarioCaba�a);
		//Integer cantidadDePublicaciones= sitio.getPublicaciones().size(); 
		//assertEquals(1, cantidadDePublicaciones);
		
	}

}
