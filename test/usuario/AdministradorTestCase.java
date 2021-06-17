package usuario;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import sitio.Categoria;
import sitio.Sitio;

class AdministradorTestCase {
	
	private Administrador administradorDelSitio;
	private Sitio sitio;
	private Sitio sitio2;
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
	
	private ArrayList<Usuario> users2= new ArrayList();
	private ArrayList<Usuario> usersDesordenados= new ArrayList();
	
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private Inmueble inmueble4;
	
	private List<Inmueble> inmuebles= new ArrayList();

	@BeforeEach
	void setUp() {
		
		administradorDelSitio= new Administrador();
		sitio=  mock(Sitio.class);
		sitio2= new Sitio();

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
		when(user4.vecesQueAlquilaron()).thenReturn(5);
		when(user5.vecesQueAlquilaron()).thenReturn(8);
		when(user6.vecesQueAlquilaron()).thenReturn(2);
		when(user7.vecesQueAlquilaron()).thenReturn(6);
		when(user8.vecesQueAlquilaron()).thenReturn(2);
		when(user9.vecesQueAlquilaron()).thenReturn(2);
		when(user10.vecesQueAlquilaron()).thenReturn(2);
		when(user11.vecesQueAlquilaron()).thenReturn(2);
		when(user12.vecesQueAlquilaron()).thenReturn(2);


		users2.add(user1);
		users2.add(user2);
		users2.add(user3);
		users2.add(user4);
		users2.add(user5);
		users2.add(user6);
		users2.add(user7);
		users2.add(user8);
		users2.add(user9);
		users2.add(user10);
		users2.add(user11);
		users2.add(user12);
		
		
		usersDesordenados.add(user1);
		usersDesordenados.add(user2);
		usersDesordenados.add(user3);
		usersDesordenados.add(user4);
		usersDesordenados.add(user5);
		usersDesordenados.add(user6);
		usersDesordenados.add(user7);
		usersDesordenados.add(user8);
		usersDesordenados.add(user9);
		usersDesordenados.add(user10);
		usersDesordenados.add(user11);
		usersDesordenados.add(user12);
		
		when(sitio.usuariosQueAlquilaron()).thenReturn(users2);
		
		inmueble1= mock(Inmueble.class);
		inmueble2= mock(Inmueble.class);
		inmueble3= mock(Inmueble.class);
		inmueble4=mock(Inmueble.class);
		
		when(inmueble1.estaReservado()).thenReturn(true);
		when(inmueble2.estaReservado()).thenReturn(false);
		when(inmueble3.estaReservado()).thenReturn(false);
		when(inmueble4.estaReservado()).thenReturn(true);

		inmuebles.add(inmueble1);
		inmuebles.add(inmueble2);
		inmuebles.add(inmueble3);
		inmuebles.add(inmueble4);
		
		when(sitio.getInmueblesPublicados()).thenReturn(inmuebles);
		
		

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

//Se compara una lista desordenada que luego sera ordenada con la misma lista desordenada
		assertFalse(administradorDelSitio.usuariosRank(sitio)== usersDesordenados);
		assertEquals(administradorDelSitio.topTenInquilinos(sitio).size(), 10 );
	}
	
	@Test
	void inmueblesLibres() {
	
		assertEquals(sitio.getInmueblesPublicados().size(), 4);
		
		int cant=administradorDelSitio.inmueblesLibres(sitio).size();
		assertEquals(cant, 2);
		


	}
	
	@Test
	void tasaOcupacion() {

		
		double tasa=administradorDelSitio.tasaOcupacion(sitio);
		
		assertEquals(tasa, 2.0);
	}
}
