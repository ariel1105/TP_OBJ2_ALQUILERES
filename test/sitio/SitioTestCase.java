package sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuario.Usuario;

class SitioTestCase {
	private Sitio sitio;
	private Usuario usuario1;
	private Usuario usuario2;
	

	@BeforeEach
	void setUp() throws Exception {
		sitio = new Sitio();
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		
	}

	@Test
	void cuandoSeInicializaElSitioNoHayUsuariosRegistrados() {
		int cantUsuariosRegistados = sitio.obtenerUsuariosRegistrados().size();
		
		assertEquals(0,cantUsuariosRegistados);
	}
	
	@Test
	void registrarNuevosUsuarios() {
		ArrayList<Usuario> usuariosARegistrar = new ArrayList<Usuario>();
		usuariosARegistrar.add(usuario1);
		usuariosARegistrar.add(usuario2);
		
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario2);
		
		assertEquals(usuariosARegistrar, sitio.obtenerUsuariosRegistrados());
	}
	
	@Test
	void elUsuarioPerteneceAlSitio() {
		sitio.registrarUsuario(usuario1);
		
		assertTrue(sitio.elUsuarioEstaRegistrado(usuario1));
	}
	
	@Test
	void registrarUsuarioYaRegistrado() {
		ArrayList<Usuario> usuariosARegistrar = new ArrayList<Usuario>();
		usuariosARegistrar.add(usuario1);
		
		sitio.registrarUsuario(usuario1);
		sitio.registrarUsuario(usuario1);
		
		assertEquals(usuariosARegistrar, sitio.obtenerUsuariosRegistrados());
	}

}
