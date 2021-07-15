package reservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadoTestCase {
	
	private Estado estado;
	private LocalDate fecha;
	private LocalDate fecha2;
	private Reserva reserva;

	@BeforeEach
	void setUp() throws Exception {
		estado = spy(Estado.class);
		fecha = mock(LocalDate.class);
		fecha2 = mock(LocalDate.class);
		reserva = mock(Reserva.class);
	}

	@Test
	void testFechaOcupada() {
		boolean ocupaFecha = estado.esfechaOcupada(reserva, fecha);
		assertFalse(ocupaFecha);
	}
	
	@Test
	void testEstaConfirmada() {
		boolean estaConfirmada = estado.estaConfirmada();
		assertFalse(estaConfirmada);
	}
	
	@Test
	void testOcupaFechaDeRango() {
		boolean ocupaFechaDeRango = estado.ocupaFechaDeRango(reserva, fecha, fecha2);
		assertFalse(ocupaFechaDeRango);
	}

}
