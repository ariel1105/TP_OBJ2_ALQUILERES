package SitioWeb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Suscripciones.AppUser;
import Suscripciones.INotify;
import Suscripciones.SitioWeb;
import inmueble.Inmueble;
import periodo.PeriodoPrecio;
import sitio.Sitio;
import usuario.Usuario;

class NotificacionesTestCase {

	private Sitio sitio1;
	private SitioWeb sitioWeb;
	private AppUser appUser;
	private SitioWeb sitioWeb2;
	private AppUser appUser2;
	private Inmueble inmuebleS1;
	private Inmueble inmuebleS2;
	private Inmueble inmuebleS3;
	private Inmueble inmuebleS4;
	private Inmueble inmuebleA1;
	private Inmueble inmuebleA2;
	private Inmueble inmuebleA3;
	private Inmueble inmuebleA4;
	private List<Inmueble> inmuebles= new ArrayList<Inmueble>();
	
	///sitioweb
	private Usuario usuario;
	private ArrayList<Usuario> usuariosRegistrados= new ArrayList<Usuario>();
	private PeriodoPrecio periodo1;
	private List<Inmueble> inmueblesRegistradosEnUsuario= new ArrayList<Inmueble>();
	
	//App
	
	
	
	@BeforeEach
	void setUp() {
		sitio1= new Sitio();
		inmuebleS1= new Inmueble(null, "Duplex", 0, null, null, null, null, 0, null, null, null, null, 0, null);
		inmuebleA1= new Inmueble(null, "Hotel", 0, null, null, null, null, 0, null, null, null, null, 50000.0, null);
		inmuebleA2= new Inmueble(null, "Loft", 0, null, null, null, null, 0, null, null, null, null, 50000.0, null);
		inmuebles.add(inmuebleS1);
		inmueblesRegistradosEnUsuario.add(inmuebleA1);
		sitioWeb= new SitioWeb(inmuebles);
		appUser= new AppUser(inmuebles);
		sitioWeb2= mock(SitioWeb.class);
		appUser2= mock(AppUser.class);
		

		usuario=new Usuario("Lautaro", "lautaro@gmail.com", "42500197", null, null);
		usuario.registrarse(sitio1);
		usuariosRegistrados.add(usuario);
		usuario.agregarInmueble(inmuebleA1);
		periodo1= mock(PeriodoPrecio.class);
		when(periodo1.perteneceLaFecha(LocalDate.now())).thenReturn(true);
		when(periodo1.getPrecio()).thenReturn(45000.0);
		inmuebleA1.establecerPeriodosConPrecios(periodo1);

	}
	
	@Test
	void testCancelarInmueble() {
		inmuebleA1.addObserver(sitioWeb2);
		usuario.actualizarPrecioAInmueble(inmuebleA1);
		verify(sitioWeb2).update(inmuebleA1, "Baja de precio");
		
	}
	
	@Test
	void testCambiarPrecio() {
		inmuebleA1.addObserver(sitioWeb2);
		usuario.actualizarPrecioAInmueble(inmuebleA1);
		verify(sitioWeb2).update(inmuebleA1, "Baja de precio");
		
	}

	
	@Test
	void test() {
		assertEquals(inmuebleS1.getListenersPaginas().size(), 2);
		inmuebleS1.notificar("Cancelacion");
		inmuebleS1.notificar("Baja de precio");
		assertEquals(sitioWeb.getInmueblesConInteres(), inmuebles);
		assertEquals(appUser.getInmueblesConInteres(), inmuebles);
		
	}

}
