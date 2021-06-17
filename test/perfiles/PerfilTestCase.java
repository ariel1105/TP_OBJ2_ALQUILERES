package perfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sitio.Categoria;

class PerfilTestCase {
	
	private Perfil perfil;
	private Categoria cat1;
	private Categoria cat2;
	private ArrayList<Categoria> categorias;
	private Map<Categoria,Integer> popularidad;

	@BeforeEach
	void setUp() throws Exception {
		perfil = spy(Perfil.class);
		cat1 = mock(Categoria.class);
		cat2 = mock(Categoria.class);
		popularidad = new HashMap<Categoria, Integer>();
		categorias = new ArrayList<Categoria>();
		categorias.add(cat1);
		categorias.add(cat2);
		perfil.setCategorias(categorias);
		perfil.setPopularidad();
	}
	
	@Test 
	void testGetComentarios() {
		ArrayList <String> sinComentarios = new ArrayList<String>();
		ArrayList <String> comentariosDePerfil = perfil.getComentarios();
		assertEquals(sinComentarios, comentariosDePerfil);
	}

	@Test
	void testSetCategorias() {
		ArrayList<Categoria> categoriasDePerfil = perfil.getCategorias();
		assertEquals(categorias, categoriasDePerfil);
	}
	
	@Test
	void testSetPopularidad() {
		Map<Categoria, Integer> popularidadDePerfil = perfil.getPopularidad();
		popularidad.put(cat1, 0);
		popularidad.put(cat2, 0);
		assertEquals(popularidad, popularidadDePerfil);
	}
	
	@Test
	void testSubirPuntuacion() {
		perfil.recibirPuntuacion(cat1, 4);
		Map<Categoria, Integer>popularidadResultante = perfil.getPopularidad();
		popularidad.put(cat1, 1);
		popularidad.put(cat2, 0);
		verify(cat1).sumarPuntos(4);
		assertEquals(popularidad, popularidadResultante);
	}
	
	@Test
	void testExisteCategoria() {
		Boolean existe = perfil.existeCategoria(cat1);
		assertTrue(existe);
	}
	
	@Test
	void testNoCambiaLaPopularidadSinoExisteLaCategoria() {
		Categoria cat3= mock(Categoria.class);
		popularidad.put(cat1, 0);
		popularidad.put(cat2, 0);
		perfil.recibirPuntuacion(cat3, 4);
		Map<Categoria, Integer>popularidadResultante = perfil.getPopularidad();
		assertEquals(popularidad, popularidadResultante);
	}
	
	@Test
	void testPuntajePromedioPorCategoria() {
		perfil.recibirPuntuacion(cat1, 5);
		perfil.recibirPuntuacion(cat1, 5);
		perfil.recibirPuntuacion(cat1, 5);
		perfil.recibirPuntuacion(cat1, 5);
		perfil.recibirPuntuacion(cat1, 5);
		when(cat1.getPuntaje()).thenReturn(25);
		Double promedio = perfil.promedioPorCat(cat1);
		assertEquals(5d,promedio);
	}
	
	@Test
	void testPuntajePromediototal() {
		perfil.recibirPuntuacion(cat1, 5);
		perfil.recibirPuntuacion(cat2, 5);
		perfil.recibirPuntuacion(cat1, 5);
		when(cat1.getPuntaje()).thenReturn(10);
		when(cat2.getPuntaje()).thenReturn(5);
		Double promedioTotal = perfil.promedioTotal();
		assertEquals(5d, promedioTotal);
	}

}
