package perfiles;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Categorias.Categoria;
import inmueble.Inmueble;
import reservas.Reserva;
import usuario.Usuario;

class PerfilInmuebleTestCase {

	private PerfilInmueble perfil;
	private PerfilPropietario perfilDueño;
	private Inmueble inmueble;
	private Usuario dueño;
	private Reserva reserva1;
	private Reserva reserva2;
	private ArrayList<Reserva> reservas;
	private ArrayList<Categoria>categorias;
	
	
	@BeforeEach
	void setUp() throws Exception {
		categorias = new ArrayList<Categoria>();
		reservas = new ArrayList<Reserva>();
		perfilDueño = mock(PerfilPropietario.class);
		inmueble = mock(Inmueble.class);
		dueño = mock(Usuario.class);
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		perfil = new PerfilInmueble(categorias, inmueble, perfilDueño);
		reservas.add(reserva1);
		reservas.add(reserva2);
	}

	@Test
	void testComentarios() {
		ArrayList<String> comentariosDueño = new ArrayList<String>();
		ArrayList<String> comentariosEsperados = new ArrayList<String>();
		comentariosEsperados.add("comentario inmueble");
		comentariosEsperados.add("comentario");
		comentariosDueño.add("comentario");
		when(perfilDueño.getComentarios()).thenReturn(comentariosDueño);
		perfil.recibirComentarios("comentario inmueble");
		ArrayList<String> comentariosPerfil = perfil.getComentarios();
		assertEquals(comentariosEsperados, comentariosPerfil);
	}
	
	@Test
	void testVecesQueSeAlquiloLaPropiedad() {
		when(inmueble.getPropietario()).thenReturn(dueño);
		when(dueño.getReservasConfirmadas()).thenReturn(reservas);
		when(reserva1.getInmueble()).thenReturn(inmueble);
		when(reserva2.getInmueble()).thenReturn(inmueble);
		int veces = perfil.vecesQueSeAlquiloEstaPropiedad();
		assertEquals(2, veces);
	}
	

}
