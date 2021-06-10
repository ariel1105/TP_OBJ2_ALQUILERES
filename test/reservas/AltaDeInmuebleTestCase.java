package reservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AltaDeInmuebleTestCase {

	private Sitio sitio;
	private Publicacion publicacionDeCabaña;
	private Inmueble cabaña;
	private Usuario propietarioCabaña;
	
	
	@BeforeEach
	void setUp() {
		
		//sitio= new Sitio(); no lo puedo instanciar asi porque la clase es abstracta! Hay que cambiarla sino no puedo verificar lo de abajo

		publicacionDeCabaña= mock(Publicacion.class);
		cabaña= mock(Inmueble.class);
		propietarioCabaña= mock(Usuario.class);
		
		
		
		
	}
	@Test
	void test() {
		//sitio.altaDeInmueble(cabaña, propietarioCabaña);
		//Integer cantidadDePublicaciones= sitio.getPublicaciones().size(); 
		//assertEquals(1, cantidadDePublicaciones);
		
	}

}
