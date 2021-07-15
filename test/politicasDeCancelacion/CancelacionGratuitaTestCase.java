package politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reservas.Reserva;

class CancelacionGratuitaTestCase {
	
	private Reserva reserva;
	private CancelacionGratuita politica;
	private LocalDate fechaActual;
	private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		reserva = mock(Reserva.class);
		fechaActual = mock(LocalDate.class);
		politica = new CancelacionGratuita();
		inmueble = mock(Inmueble.class);
	}

	@Test
	void testNoTieneQueAbonar() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 20));
		boolean tieneQueAbonar = politica.tieneQueAbonar(reserva, LocalDate.of(2021, 1, 1));
		assertFalse(tieneQueAbonar);
	}
	
	@Test
	void testTieneQueAbonar() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 5));
		boolean tieneQueAbonar = politica.tieneQueAbonar(reserva, LocalDate.of(2021, 1, 1));
		assertTrue(tieneQueAbonar);
	}
	
	@Test
	void testValorPenalizacionPorCancelacionDeUnDia() {
		LocalDate fecha = LocalDate.of(2021, 1, 1);
		when(reserva.getDiaInicio()).thenReturn(fecha);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.obtenerElPrecioParaLaFecha(fecha)).thenReturn(1000d);
		when(reserva.ocupaFecha(fecha.plusDays(1))).thenReturn(false);
		double valor = politica.valorPenalizacionPorCancelacion(reserva);
		assertEquals(1000d, valor);
	}
	
	@Test
	void testValorPenalizacionPorCancelacionDeDosDias() {
		LocalDate fecha = LocalDate.of(2021, 1, 1);
		when(reserva.getDiaInicio()).thenReturn(fecha);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.obtenerElPrecioParaLaFecha(fecha)).thenReturn(1000d);
		when(inmueble.obtenerElPrecioParaLaFecha(fecha.plusDays(1))).thenReturn(2000d);
		when(reserva.ocupaFecha(fecha.plusDays(1))).thenReturn(true);
		double valor = politica.valorPenalizacionPorCancelacion(reserva);
		assertEquals(3000d, valor);
	}
	
	@Test
	void testValor0() {
		when(reserva.getDiaInicio()).thenReturn(LocalDate.of(2021, 1, 20));
		double valor = politica.valorPara(reserva, LocalDate.of(2021, 1, 1));
		assertEquals(0, valor);
	}
	
	@Test
	void testValorConPenalizacion() {
		LocalDate fecha = LocalDate.of(2021, 1, 1);
		when(reserva.getDiaInicio()).thenReturn(fecha);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.obtenerElPrecioParaLaFecha(fecha)).thenReturn(1000d);
		when(inmueble.obtenerElPrecioParaLaFecha(fecha.plusDays(1))).thenReturn(2000d);
		when(reserva.ocupaFecha(fecha.plusDays(1))).thenReturn(true);
		double valor = politica.valorPara(reserva, LocalDate.of(2020, 12, 30));
		assertEquals(3000d, valor);
	}

}
