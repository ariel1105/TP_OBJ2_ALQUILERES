package reservas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservaTestCase {

	Reserva reserva;
	Dia dia; 
	Inmueble inmueble;
	ArrayList<Dia> dias;
	Sitio sitio;
	String formaDePago;
	Usuario inquilino;
	Estado estado;
	
	@BeforeEach
	void setUp() throws Exception {
		inquilino = mock(Usuario.class);
		dia = mock(Dia.class);
		dias = new ArrayList<Dia>();
		dias.add(dia);
		estado = mock(Estado.class);
		inmueble = mock(Inmueble.class);
		formaDePago = "Efectivo";
		reserva = new Reserva(inquilino, inmueble, dias, formaDePago);
		sitio = mock(Sitio.class);
		
		
	}

	@Test
	void testIngresarReservaEnSitio() {
		reserva.ingresarEnSitio(sitio);
		verify(sitio).agegarReserva(reserva);
	}

	@Test 
	void testConfirmarReserta() {
		reserva.confirmarseEn(sitio);
		verify(sitio).enviarMailDeConfirmacion(reserva);
	}
	
	@Test 
	void testDiaNoExisteEnReservaPorqueNoEstaConfirmada() {
		Boolean diaOcupado = reserva.ocupaDia(dia);
		assertFalse(diaOcupado);
	}
	
	@Test
	void testDiaOcupado() {
		reserva.confirmarseEn(sitio);
		Boolean diaOcupado = reserva.ocupaDia(dia);
		assertTrue(diaOcupado);
	}
	
	
}
