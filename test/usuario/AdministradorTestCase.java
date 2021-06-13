package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Categoria;
import sitio.Sitio;

class AdministradorTestCase {
	
	private Administrador administradorDelSitio;
	private Sitio sitio;
	private Categoria categoriaUbicacion;
	private Categoria categoriaComodidad;
	private Categoria categoriaCercania;
	private Categoria atencion;
	private Categoria amabilidad;
	private Categoria solucionDeProblemas;
	private Categoria comportamiento;
	private Categoria pagoATiempo;
	private Categoria actitud;
	

	private Usuario user1;
	private Usuario user2;
	private Usuario user3;
	private Usuario user4;
	private Usuario user5;
	private Usuario user6;
	private Usuario user7;
	private Usuario user8;
	private Usuario user9;
	private Usuario user10;
	private Usuario user11;
	private Usuario user12;
	
	
	private ArrayList<Usuario> users= new ArrayList();
	
	@BeforeEach
	void setUp() {
		
		administradorDelSitio= new Administrador();
		sitio=  mock(Sitio.class);
		
		categoriaUbicacion= mock(Categoria.class);
		categoriaComodidad= mock(Categoria.class);
		categoriaCercania=  mock(Categoria.class);
		
		atencion= mock(Categoria.class);
		amabilidad= mock(Categoria.class);
		solucionDeProblemas=mock(Categoria.class);
		
		comportamiento= mock(Categoria.class);
		pagoATiempo= mock(Categoria.class);
		actitud= mock(Categoria.class);
		
		user1=mock(Usuario.class);
		user2=mock(Usuario.class);
		user3=mock(Usuario.class);
		user4=mock(Usuario.class);
		user5=mock(Usuario.class);
		user6=mock(Usuario.class);
		user7=mock(Usuario.class);
		user8=mock(Usuario.class);
		user9=mock(Usuario.class);
		user10=mock(Usuario.class);
		user11=mock(Usuario.class);
		user12=mock(Usuario.class);
		
		when(user1.vecesQueAlquilaron()).thenReturn(2);
		when(user2.vecesQueAlquilaron()).thenReturn(2);
		when(user3.vecesQueAlquilaron()).thenReturn(2);
		when(user4.vecesQueAlquilaron()).thenReturn(2);
		when(user5.vecesQueAlquilaron()).thenReturn(2);
		when(user6.vecesQueAlquilaron()).thenReturn(2);
		when(user7.vecesQueAlquilaron()).thenReturn(6);
		when(user8.vecesQueAlquilaron()).thenReturn(2);
		when(user9.vecesQueAlquilaron()).thenReturn(2);
		when(user10.vecesQueAlquilaron()).thenReturn(2);
		when(user11.vecesQueAlquilaron()).thenReturn(2);
		when(user12.vecesQueAlquilaron()).thenReturn(2);


		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		users.add(user9);
		users.add(user10);
		users.add(user11);
		users.add(user12);
		
		when(sitio.usuarios()).thenReturn(users);
		when(sitio.usuariosQueAlquilaron()).thenReturn(users);
		
	}

	@Test
	void testDarDeAltaCategoriaInmueble() {
		administradorDelSitio.darDeAltaCategoriaParaInmueble(sitio, categoriaUbicacion);
		administradorDelSitio.darDeAltaCategoriaParaInmueble(sitio, categoriaComodidad);
		administradorDelSitio.darDeAltaCategoriaParaInmueble(sitio, categoriaCercania);
		
		verify(sitio).altaDeCategoriaInmueble(categoriaUbicacion);
		verify(sitio).altaDeCategoriaInmueble(categoriaComodidad);
		verify(sitio).altaDeCategoriaInmueble(categoriaCercania);
			
	}
	@Test
	void testDarDeAltaCategoriaPropietario() {
		administradorDelSitio.darDeAltaCategoriaParaPropietario(sitio, atencion);
		administradorDelSitio.darDeAltaCategoriaParaPropietario(sitio, amabilidad);
		administradorDelSitio.darDeAltaCategoriaParaPropietario(sitio, solucionDeProblemas);
		
		verify(sitio).altaDeCategoriaPropietario(atencion);
		verify(sitio).altaDeCategoriaPropietario(amabilidad);
		verify(sitio).altaDeCategoriaPropietario(solucionDeProblemas);
			
	}
	@Test
	void testDarDeAltaCategoriaInquilino() {
		administradorDelSitio.darDeAltaCategoriaParaInquilino(sitio, comportamiento);
		administradorDelSitio.darDeAltaCategoriaParaInquilino(sitio, pagoATiempo);
		administradorDelSitio.darDeAltaCategoriaParaInquilino(sitio, actitud);
	
	
		verify(sitio).altaDeCategoriaInquilino(comportamiento);
		verify(sitio).altaDeCategoriaInquilino(pagoATiempo);
		verify(sitio).altaDeCategoriaInquilino(actitud);
	}
	
	@Test
	void testDarDeAltaTipoDeInmueble() {
		administradorDelSitio.darDeAltaTipoDeInmueble(sitio,"Departamento");
		administradorDelSitio.darDeAltaTipoDeInmueble(sitio,"Casa");
		administradorDelSitio.darDeAltaTipoDeInmueble(sitio, "Duplex");
		
		verify(sitio).altaDeTipoInmueble("Departamento");
		verify(sitio).altaDeTipoInmueble("Casa");
		verify(sitio).altaDeTipoInmueble("Duplex");
		
	}
	
	@Test
	void testDarDeAltaServicios() {
		administradorDelSitio.darDeAltaServicio(sitio, "TV con cable");
		administradorDelSitio.darDeAltaServicio(sitio, "Wifi");
		administradorDelSitio.darDeAltaServicio(sitio, "Parrilla");
		
		verify(sitio).altaServicio("TV con cable");
		verify(sitio).altaServicio("Wifi");
		verify(sitio).altaServicio("Parrilla");
	
	}
	
	@Test
	void testTopTenInquilinos() {
		List <Usuario> topTen= administradorDelSitio.topTenInquilinos(sitio);
		assertEquals(topTen.size(), 10);
		
	}
	
	@Test
	void inmueblesLibres() {
		List <Usuario> valor= administradorDelSitio.usuariosRank(sitio, users);
		assertEquals(valor.size(), 12);
		Usuario user= valor.get(0);
		assertEquals(user, user1);

	}
	
	@Test
	void tasaOcupacion() {
		
		administradorDelSitio.tasaOcupacion(sitio);
	}
}
