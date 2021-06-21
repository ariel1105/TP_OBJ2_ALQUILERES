package perfiles;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Categoria;
import sitio.Sitio;
import usuario.Usuario;

public class PerfilDue�oTestCase {

	private PerfilPropietario perfil;
	private Inmueble inmueble;
	private Usuario due�o;
	private ArrayList<Categoria> categorias;
	private ArrayList<Reserva> reservas;
	private Reserva reserva1;
	private Reserva reserva2;
	
	private Usuario due�o2;
	private Sitio sitio;
	private Perfil perfil2;
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		due�o = mock(Usuario.class);
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		when(inmueble.getPropietario()).thenReturn(due�o);
		when(due�o.getReservasConfirmadas()).thenReturn(reservas);
		perfil = new PerfilPropietario(categorias, due�o);
		
		
		sitio= mock(Sitio.class);
		due�o2 = new Usuario(null, null, null, null);
		perfil2= new PerfilPropietario(categorias, due�o2);
	}
	
	@Test //agrego un test para chequear bien el tiempo como usuario
	void testTiempoComoUsuario2() {
		due�o2.registrarse(sitio);
		long dias= due�o2.tiempoComoUser();
		assertEquals(dias, 0); //va a dar 0 dias porque se registro en el mismo dias que le pido el dato (osea hoy)
	}
	
	
	@Test
	void testTiempoComoUsuario() {
		when(due�o.tiempoComoUser()).thenReturn((long) 20);
		long dias = perfil.tiempoComoUsuario();
		assertEquals(dias, 20);
	}
	
	
	@Test
	void testInmueblesAlquilados() {
		Set<Inmueble> resultadoEsperado = new HashSet();
		resultadoEsperado.add(inmueble);
		when(reserva1.getInmueble()).thenReturn(inmueble);
		when(reserva2.getInmueble()).thenReturn(inmueble);
		Set<Inmueble> inmueblesReservados = perfil.inmueblesAlquilados();
		assertEquals(resultadoEsperado, inmueblesReservados);
	}
	
	@Test
	void testCantidadDeAlquileres() {
		when(due�o.getReservasConfirmadas()).thenReturn(reservas);
		int resultado = perfil.cantidadDeAlquilieres();
		assertEquals(2, resultado);
	}

}
