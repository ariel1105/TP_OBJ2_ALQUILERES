package reservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.any;
import sitio.Sitio;

class PendienteDeConfirmacionTestCase {
	
	private Reserva reserva;
	private PendienteDeConfirmacion estado;
	private Sitio sitio;
	private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		estado = new PendienteDeConfirmacion();
		sitio = mock(Sitio.class);
		fecha = mock(LocalDate.class);
	}

	@Test
	void testConfirmarReserva() {
		estado.confirmarEn(reserva,sitio);
		verify(reserva).setEstado(any(Confirmado.class));
		verify(sitio).enviarMailDeConfirmacion(reserva);
	}
	
	@Test
	void testCancelarReserva() {
		estado.cancelarReserva(reserva, fecha);
		verify(reserva, never()).setEstado(any(Estado.class));
	}
	
	@Test
	void testFechaOcupada( ) {
		boolean esFechaOcupada = estado.esfechaOcupada(reserva, fecha);
		assertFalse(esFechaOcupada);
	}

}
