package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reservas.Reserva;

class SinCancelacionTestCase {
	
	private Reserva reserva;
	private SinCancelacion politica;
	private LocalDate fecha;
	private LocalDate fecha2;
	private ArrayList<LocalDate> fechas;

	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		politica = new SinCancelacion();
		fecha = mock(LocalDate.class);
		fecha2= mock(LocalDate.class);
		fechas = new ArrayList<LocalDate>();
		fechas.add(fecha);
		fechas.add(fecha2);
	}

	@Test
	void testCancelacionYPagoPorTotalidadDeReserva() {
		when(reserva.getFechas()).thenReturn(fechas);
		politica.cancelar(reserva);
		verify(reserva).confirmarPagoPor(reserva.valorPorDias(2));
	}

}
