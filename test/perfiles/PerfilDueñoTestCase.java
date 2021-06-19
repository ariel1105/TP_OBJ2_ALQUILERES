package perfiles;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Categoria;
import usuario.Usuario;

public class PerfilDueñoTestCase {

	private PerfilPropietario perfil;
	private Inmueble inmueble;
	private Usuario dueño;
	private ArrayList<Categoria> categorias;
	private ArrayList<Reserva> reservas;
	private Reserva reserva1;
	private Reserva reserva2;
	
	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		dueño = mock(Usuario.class);
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		when(inmueble.getPropietario()).thenReturn(dueño);
		when(dueño.getReservasConfirmadas()).thenReturn(reservas);
		perfil = new PerfilPropietario(categorias, dueño);
		
	}
	
	@Test
	void testTiempoComoUsuario() {
		when(dueño.tiempoComoUsuario()).thenReturn(20);
		int dias = perfil.tiempoComoUsuario();
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
		when(dueño.getReservasConfirmadas()).thenReturn(reservas);
		int resultado = perfil.cantidadDeAlquilieres();
		assertEquals(2, resultado);
	}

}
