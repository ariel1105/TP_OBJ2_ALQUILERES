package reservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PendienteDeConfirmacionTestCase {
	
	private PendienteDeConfirmacion estado;
	private Reserva reserva;
	private Dia dia;

	@BeforeEach
	void setUp() throws Exception {
		estado = new PendienteDeConfirmacion();
		reserva = mock(Reserva.class);
		dia = mock(Dia.class);
	}

	@Test
	void testNoOcupaLosDiasDeLaReserva() {
		Boolean diaOcupado = estado.diaOcupadoEn(dia, reserva);
		assertFalse(diaOcupado);
	}

}
