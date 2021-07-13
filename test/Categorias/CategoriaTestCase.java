package Categorias;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoriaTestCase {
	
	Categoria cat;

	@BeforeEach
	void setUp() throws Exception {
		cat = new Categoria();
	}

	@Test
	void testSumarPuntos() {
		cat.sumarPuntos(3);
		int cantPuntos = cat.getPuntaje();
		assertEquals(3, cantPuntos);
	}

}
