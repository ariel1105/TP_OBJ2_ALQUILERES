package formaDePago;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.FormaDePago;

class FormaDePagoTestCase {

	
	private FormaDePago efectivo;
	private FormaDePago tarjetaDeDebito;
	private FormaDePago tarjetaDeCredito;
	
	@BeforeEach
	void setUp() {
		efectivo= new FormaDePago("Efectivo");
		tarjetaDeDebito= new FormaDePago("Tarjeta de debito");
		tarjetaDeCredito= new FormaDePago("Tarjeta de credito");
		
		
	}
	
	
	@Test
	void testGetter() {
		assertEquals(efectivo.getFormaDePago(), "Efectivo");
		assertEquals(tarjetaDeDebito.getFormaDePago(), "Tarjeta de debito");
		assertEquals(tarjetaDeCredito.getFormaDePago(), "Tarjeta de credito");
	}

}
