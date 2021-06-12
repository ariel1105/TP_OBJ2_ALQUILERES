package inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.FormaDePago;
import inmueble.Inmueble;

class DatosDePagoTestCase {
	
	private Inmueble inmueble;
	private DatosDePago datosDePago;
	private ArrayList<FormaDePago> formasDePagoLista;
	private FormaDePago formaDePago;

	@BeforeEach
	void setUp() throws Exception {
		inmueble = mock(Inmueble.class);
		formaDePago = mock(FormaDePago.class);
		formasDePagoLista = new ArrayList<FormaDePago>();
		formasDePagoLista.add(formaDePago);
		datosDePago = new DatosDePago(formaDePago, "nombre", "direccion", "email");
	}

	@Test
	void testSonDatosDePagoAdmitidos() {
		when(inmueble.getFormasDePago()).thenReturn(formasDePagoLista);
		assertTrue(datosDePago.sonDatosAdmitidosPara(inmueble));
	}

}
