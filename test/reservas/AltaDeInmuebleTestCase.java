package reservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AltaDeInmuebleTestCase {

	private Sitio sitio;
	private Publicacion publicacionDeCabaņa;
	private Inmueble cabaņa;
	private Usuario propietarioCabaņa;
	
	
	@BeforeEach
	void setUp() {
		
		//sitio= new Sitio(); no lo puedo instanciar asi porque la clase es abstracta! Hay que cambiarla sino no puedo verificar lo de abajo

		publicacionDeCabaņa= mock(Publicacion.class);
		cabaņa= mock(Inmueble.class);
		propietarioCabaņa= mock(Usuario.class);
		
		
		
		
	}
	@Test
	void test() {
		//sitio.altaDeInmueble(cabaņa, propietarioCabaņa);
		//Integer cantidadDePublicaciones= sitio.getPublicaciones().size(); 
		//assertEquals(1, cantidadDePublicaciones);
		
	}

}
