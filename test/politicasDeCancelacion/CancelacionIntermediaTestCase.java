package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reservas.Reserva;

class CancelacionIntermediaTestCase {
	
	private Reserva reserva;
	private CancelacionIntermedia politica;
	private LocalDate fecha;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		politica = new CancelacionIntermedia();
	}
	
}
	