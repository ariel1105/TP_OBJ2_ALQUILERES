package reservas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ConfirmadaTestCase {
	
	private Confirmada estado;
	private Reserva reserva;
	private LocalDate dia;

	@BeforeEach
	void setUp() throws Exception {
		estado = new Confirmada();
		reserva = mock(Reserva.class);
		dia = mock(LocalDate.class);
	}

	@Test
	void testLosDiasQuedanReservados() {
		ArrayList<LocalDate> diaDeReserva = new ArrayList <LocalDate>();
		diaDeReserva.add(dia);
		when(reserva.getFechas()).thenReturn(diaDeReserva);
		Boolean diaOcupado = estado.fechaOcupadaEn(dia, reserva);
		assertTrue(diaOcupado);
		
	}

}
