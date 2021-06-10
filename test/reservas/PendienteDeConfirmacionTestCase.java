package reservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PendienteDeConfirmacionTestCase {
	
	private PendienteDeConfirmacion estado;
	private Reserva reserva;
	private LocalDate dia;

	@BeforeEach
	void setUp() throws Exception {
		estado = new PendienteDeConfirmacion();
		reserva = mock(Reserva.class);
		dia = mock(LocalDate.class);
	}

	@Test
	void testNoOcupaLosDiasDeLaReserva() {
		Boolean diaOcupado = estado.fechaOcupadaEn(dia, reserva);
		assertFalse(diaOcupado);
	}

}
