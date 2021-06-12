package inmueble;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import periodo.PeriodoPrecio;
import usuario.Usuario;

class InmuebleTestCase {
	private Inmueble casa;
	String tipoDeInmueble;
	double superficie;
	String pais;
	String ciudad;
	String direccion;
	int capacidad;
	double precio;
	private ArrayList<String> servicios ;
	private ArrayList<Foto> fotos;
	private Hora horarioCheckIn;
	private Hora horarioCheckOut;
	private ArrayList<String> formasDePago;
	private PeriodoPrecio periodoPrecio1;
	private PeriodoPrecio periodoPrecio2;
	private LocalDate fecha1;
	private LocalDate fecha2;
	private LocalDate fecha3;
	private LocalDate fecha4;
	
	@BeforeEach
	void setUp() throws Exception {;
		
		fecha1 = mock(LocalDate.class);
		fecha2 = mock(LocalDate.class);
		periodoPrecio1 = mock(PeriodoPrecio.class);
		
		fecha3 = mock(LocalDate.class);
		fecha4 = mock(LocalDate.class);
		periodoPrecio2 = mock(PeriodoPrecio.class);
		
		casa = new Inmueble(tipoDeInmueble, superficie,pais,ciudad,direccion,servicios,capacidad,fotos,horarioCheckIn, horarioCheckOut, formasDePago,precio);
		
		casa.establecerPeriodosConPrecios(periodoPrecio1);
		casa.establecerPeriodosConPrecios(periodoPrecio2);
		
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		when(periodoPrecio2.getPrecio()).thenReturn(4000d);
	}

	
	@Test 
	void establecerPeriodosConPreciosTestCase(){
		ArrayList <PeriodoPrecio> periodosYPrecios = new ArrayList<PeriodoPrecio>();
		periodosYPrecios.add(periodoPrecio1);
		periodosYPrecios.add(periodoPrecio2);
		
		assertEquals(periodosYPrecios, casa.getPeriodosYPrecios());
	}
	
	@Test
	void perteneceLaFechaAAlgunPeriodoTestCase() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(true);
		
		assertTrue(casa.perteneceLaFechaAAlgunPeriodo(fecha3));
	}
	
	@Test
	void LaFechaNoPerteneceANingunPeriodoTestCase() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(false);
		
		assertFalse(casa.perteneceLaFechaAAlgunPeriodo(fecha3));
	}
	
	@Test
	void testPrecioParaLaFechaCuandoPerteneceAlPeriodo1() {
		
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		
		double precio = casa.obtenerElPrecioParaLaFecha(fecha1);
		
		assertEquals(precio,2000d);
	}
	
	@Test
	void TestPrecioPorDefecto() {
		
		double precio = casa.getPrecioPorDefecto();
		
		assertEquals(precio,3000d);
	}
	
	@Test
	void TestPrecioParaLaFechaCuandoPerteneceAlPeriodo2() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(true);
		
		double precio = casa.precioParaLaFecha(fecha3);
		
		assertEquals(precio,4000d);
	}
	
	@Test
	void TestPrecioParaFechasDelPeriodo1() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fecha1);
		fechas.add(fecha2);
		
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio1.perteneceLaFecha(fecha2)).thenReturn(true);
		
		double precio = casa.valorPorDias(fechas);
		
		assertEquals(precio, 4000d);
	}
	
	@Test
	void TestPrecioParaFechaPeriodo1YFechaPeriodo2() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fecha1);
		fechas.add(fecha3);
		
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(true);
		
		double precio = casa.valorPorDias(fechas);
		
		assertEquals(precio, 6000d);
	}
	
	@Test
	void TestPrecioParaFechasDelPeriodo1YQueNoPertenecenANingunPeriodo() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fecha3);
		fechas.add(fecha4);
		
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(true);
		when(periodoPrecio1.perteneceLaFecha(fecha4)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha4)).thenReturn(false);
		
		double precio = casa.valorPorDias(fechas);
		
		assertEquals(precio, 5000d);
	}
	
	@Test
	void TestPrecioParaFechasQueNoPertenecenANingunPeriodo() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fecha3);
		fechas.add(fecha4);
		
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio1.perteneceLaFecha(fecha4)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha4)).thenReturn(false);
		
		double precio = casa.valorPorDias(fechas);
		
		assertEquals(precio, 6000d);
	}
	

}
