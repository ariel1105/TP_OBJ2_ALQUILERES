package reservas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfirmadaTestCase {
	
	private Confirmada estado;
	private Reserva reserva;
	private Dia dia;

	@BeforeEach
	void setUp() throws Exception {
		estado = new Confirmada();
		reserva = mock(Reserva.class);
		dia = mock(Dia.class);
	}

	@Test
	void testLosDiasQuedanReservados() {
		ArrayList<Dia> diaDeReserva = new ArrayList <Dia>();
		diaDeReserva.add(dia);
		when(reserva.getDias()).thenReturn(diaDeReserva);
		Boolean diaOcupado = estado.diaOcupadoEn(dia, reserva);
		assertTrue(diaOcupado);
		
	}

}
