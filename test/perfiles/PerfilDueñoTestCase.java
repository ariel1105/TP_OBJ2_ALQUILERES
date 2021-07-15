package perfiles;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Categorias.Categoria;
import Suscripciones.AppUser;
import administradorDeReservas.AdministadorDeReservasInquilino;
import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Sitio;
import usuario.Usuario;

public class PerfilDueñoTestCase {

	private PerfilPropietario perfil;
	private Inmueble inmueble;
	private Inmueble inmueble2;
	private Usuario propietario;
	private ArrayList<Categoria> categorias;
	private ArrayList<Inmueble> inmuebles;
	private Usuario propietario2;
	private Sitio sitio;
	private PerfilPropietario perfil2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		inmuebles = new ArrayList<Inmueble>();
		inmuebles.add(inmueble);
		inmuebles.add(inmueble2);
		propietario = mock(Usuario.class);
		perfil = new PerfilPropietario(categorias, propietario);
		sitio= mock(Sitio.class);
		propietario2 = mock(Usuario.class);
		perfil2= new PerfilPropietario(categorias, propietario2);
	}
	
	@Test 
	void testTiempoComoUsuario2() {
		propietario2.registrarse(sitio);
		long dias= perfil2.tiempoComoUsuario();
		assertEquals(dias, 0); //va a dar 0 dias porque se registro en el mismo dias que le pido el dato (osea hoy)
	}
	
	
	@Test
	void testTiempoComoUsuario() {
		when(propietario.tiempoComoUser()).thenReturn((long) 20);
		long dias = perfil.tiempoComoUsuario();
		assertEquals(dias, 20);
	}
	
	
	@Test
	void testInmueblesAlquilados() {
		when(propietario.getInmuebles()).thenReturn(inmuebles);
		when(inmueble.estaReservado()).thenReturn(true);
		when(inmueble2.estaReservado()).thenReturn(true);
		List<Inmueble>inmueblesAlquilados = perfil.inmueblesAlquilados();
		assertEquals(inmuebles, inmueblesAlquilados);
	}
	
	@Test
	void testCantidadDeAlquileres() {
		when(propietario.getInmuebles()).thenReturn(inmuebles);
		when(inmueble.estaReservado()).thenReturn(true);
		when(inmueble2.estaReservado()).thenReturn(true);
		when(inmueble.vecesQueFueAlquilado()).thenReturn(3);
		when(inmueble2.vecesQueFueAlquilado()).thenReturn(4);
		int cantidadDeAlquileres = perfil.cantidadDeAlquilieres();
		assertEquals(7, cantidadDeAlquileres);
	}

}
