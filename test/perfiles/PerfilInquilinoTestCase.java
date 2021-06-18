package perfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sitio.Categoria;
import usuario.Usuario;

class PerfilInquilinoTestCase {
	
	private PerfilInquilino perfil;
	private Usuario inquilino;
	private ArrayList<Categoria> categorias;
	
	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		categorias = new ArrayList<Categoria>();
		perfil = new PerfilInquilino(categorias, inquilino);
	}

	@Test
	void testTelefono() {
		when(inquilino.getTelefono()).thenReturn("123456789");
		String telefono = perfil.telefonoInquilino();
		assertEquals("123456789", telefono);
	}
	
	@Test
	void testMail() {
		when(inquilino.getMail()).thenReturn("a@gmail.com");
		String mail = perfil.mailInquilino();
		assertEquals("a@gmail.com", mail);
	}


}
