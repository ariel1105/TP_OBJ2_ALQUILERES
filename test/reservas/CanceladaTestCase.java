package reservas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanceladaTestCase {

	private Cancelada estado;
	private Reserva reserva;
	private Dia dia;

	@BeforeEach
	void setUp() throws Exception {
		estado = new Cancelada();
		reserva = mock(Reserva.class);
		dia = mock(Dia.class);
	}

	@Test
	void testLosDiasQuedanReservados() {
		Boolean diaOcupado = estado.diaOcupadoEn(dia, reserva);
		assertFalse(diaOcupado);
		
	}

}
