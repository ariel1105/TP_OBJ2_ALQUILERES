package sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Categorias.Categoria;
import inmueble.Inmueble;
import usuario.Usuario;

class SitioTestCase {
	private Sitio sitio;
	private Usuario usuario1;
	private Usuario usuario2;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private Inmueble inmueble4;
	private Categoria categoria1;
	private Categoria categoria2;
	private Categoria categoria3;
	private Categoria categoriaP1;
	private Categoria categoriaP2;
	private Categoria categoriaP3;
	private Categoria categoriaI1;
	private Categoria categoriaI2;
	private Categoria categoriaI3;
	private ArrayList<String> servicios1= new ArrayList<String>();
	private ArrayList<String> servicios2= new ArrayList<String>();
	private ArrayList<String> servicios3= new ArrayList<String>();

	@BeforeEach
	void setUp() throws Exception {
		sitio = new Sitio();
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		categoria1= mock(Categoria.class);
		categoria2= mock(Categoria.class);
		categoria3= mock(Categoria.class);
		categoriaP1= mock(Categoria.class);
		categoriaP2= mock(Categoria.class);
		categoriaP3= mock(Categoria.class);
		categoriaI1= mock(Categoria.class);
		categoriaI2= mock(Categoria.class);
		categoriaI3= mock(Categoria.class);
		inmueble1=mock(Inmueble.class);
		inmueble2=mock(Inmueble.class);
		inmueble3=mock(Inmueble.class);
		inmueble4= new Inmueble(usuario1, null, 0, null, null, null, null, 0, null, null, null, null, 0, null);
		servicios1.add("WIFI");
		servicios1.add("Aire acondicionado");
		servicios1.add("Estufa");
		servicios2.add("TV con cable");
		servicios2.add("Jacuzzi");
		servicios2.add("Caloventor");
		servicios3.add("Frigovar");
		servicios3.add("Estacionamiento");
		servicios3.add("Cama Solar");
		
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
	
	@Test
	void altaDeCategoriaInmueble() {
		
		ArrayList<Categoria> categoriasRegistradas = new ArrayList<Categoria>();
		categoriasRegistradas.add(categoria1);
		categoriasRegistradas.add(categoria2);
		categoriasRegistradas.add(categoria3);
		
		sitio.altaDeCategoriaInmueble(categoria1);
		sitio.altaDeCategoriaInmueble(categoria2);
		sitio.altaDeCategoriaInmueble(categoria3);
		assertEquals(sitio.getCategoriasParaInmueble(), categoriasRegistradas );
		
	}

	
	@Test
	void altaDeCategoriaPropietario() {
		
		ArrayList<Categoria> categoriasRegistradas = new ArrayList<Categoria>();
		categoriasRegistradas.add(categoriaP1);
		categoriasRegistradas.add(categoriaP2);
		categoriasRegistradas.add(categoriaP3);
		
		sitio.altaDeCategoriaPropietario(categoriaP1);
		sitio.altaDeCategoriaPropietario(categoriaP2);
		sitio.altaDeCategoriaPropietario(categoriaP3);
		assertEquals(sitio.getCategoriasParaPropietario(), categoriasRegistradas );
		
	}
	
	
	@Test
	void altaDeCategoriaInquilino() {
		
		ArrayList<Categoria> categoriasRegistradas = new ArrayList<Categoria>();
		categoriasRegistradas.add(categoriaI1);
		categoriasRegistradas.add(categoriaI2);
		categoriasRegistradas.add(categoriaI3);
		
		sitio.altaDeCategoriaInquilino(categoriaI1);
		sitio.altaDeCategoriaInquilino(categoriaI2);
		sitio.altaDeCategoriaInquilino(categoriaI3);
		assertEquals(sitio.getCategoriasParaInquilino(), categoriasRegistradas );
		
	}
	
	@Test
	void altaTipoDeInmueble() {
		
		ArrayList<String> inmueblesRegistrados = new ArrayList<String>();
		inmueblesRegistrados.add("Departamento");
		inmueblesRegistrados.add("Hotel");
		inmueblesRegistrados.add("Cabaña");
		
		sitio.altaDeTipoInmueble("Departamento");
		sitio.altaDeTipoInmueble("Hotel");
		sitio.altaDeTipoInmueble("Cabaña");
		assertEquals(sitio.getTipoDeInmuebles(), inmueblesRegistrados );
		
	}
	
	@Test
	void altaServiciosDeInmueble() {
		
		ArrayList<String> serviciosRegistrados = new ArrayList<String>();
		serviciosRegistrados.add("WIFI");
		serviciosRegistrados.add("Jacuzzi");
		serviciosRegistrados.add("TV CON CABLE");
		
		sitio.altaServicio("WIFI");
		sitio.altaServicio("Jacuzzi");
		sitio.altaServicio("TV CON CABLE");
		assertEquals(sitio.getServicios(), serviciosRegistrados );
		
	}
	
	@Test
	void publicarInmueble() {
		ArrayList<Inmueble> inmueblesRegistrados = new ArrayList<Inmueble>();
		inmueblesRegistrados.add(inmueble1);
		inmueblesRegistrados.add(inmueble2);
		inmueblesRegistrados.add(inmueble3);
		

		sitio.publicar(inmueble1, usuario1, servicios1);
		sitio.publicar(inmueble2, usuario1, servicios2);
		sitio.publicar(inmueble3, usuario2, servicios3);
		assertEquals(inmueblesRegistrados, sitio.getInmueblesPublicados());
		
		
	}
	
	@Test
	void seleccionarCategorias() {
		ArrayList<String> serviciosQueEstanParaSeleccionar = new ArrayList<String>();
		serviciosQueEstanParaSeleccionar.add("WIFI");
		serviciosQueEstanParaSeleccionar.add("Estufa");
		sitio.altaServicio("WIFI");
		sitio.altaServicio("Jacuzzi");
		sitio.altaServicio("Estufa");
		sitio.publicar(inmueble4, usuario1, servicios1);

		assertEquals(inmueble4.getServicios(), serviciosQueEstanParaSeleccionar);
	}
}
