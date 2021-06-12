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
	private Inmueble casa1;
	private ArrayList<String> servicios ;
	private ArrayList<Foto> fotos;
	private Hora horarioCheckIn;
	private Hora horarioCheckOut;
	private ArrayList<String> formasDePago;
	private Usuario dueño;
	private PeriodoPrecio periodoPrecio1;
	private PeriodoPrecio periodoPrecio2;
	private LocalDate fecha1;
	private LocalDate fecha2;
	private LocalDate fecha3;
	private LocalDate fecha4;
	
	@BeforeEach
	void setUp() throws Exception {
		String tipoDeInmueble = "casa";
		double superficie = 100d;
		String pais = "Argentina";
		String ciudad = "Quilmes";
		String direccion = "Alsina 200";
		servicios = new ArrayList<String>();
		int capacidad = 4;
		fotos = new ArrayList<Foto>();
		horarioCheckIn = mock(Hora.class);
		horarioCheckOut = mock(Hora.class);
		formasDePago = new ArrayList<String>();
		double precio = 10000;
		
		servicios.add("agua");
		servicios.add("luz");
		
		formasDePago.add("tarjetaDeCredito");
		
		dueño = mock(Usuario.class);
		
		fecha1 = mock(LocalDate.class);
		fecha2 = mock(LocalDate.class);
		periodoPrecio1 = mock(PeriodoPrecio.class);
		
		fecha3 = mock(LocalDate.class);
		fecha4 = mock(LocalDate.class);
		periodoPrecio2 = mock(PeriodoPrecio.class);
		
		
		casa1 = new Inmueble(tipoDeInmueble, superficie,pais,ciudad,direccion,servicios,capacidad,fotos,horarioCheckIn, horarioCheckOut, formasDePago,precio);
		
	}
	
	@Test 
	void establecerPeriodosConPreciosTestCase(){
		ArrayList <PeriodoPrecio> periodosYPrecios = new ArrayList<PeriodoPrecio>();
		periodosYPrecios.add(periodoPrecio1);
		periodosYPrecios.add(periodoPrecio2);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		assertEquals(periodosYPrecios, casa1.getPeriodosYPrecios());
	}
	
	@Test
	void perteneceLaFechaAAlgunPeriodoTestCase() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(true);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		assertTrue(casa1.perteneceLaFechaAAlgunPeriodo(fecha3));
	}
	
	@Test
	void LaFechaNoPerteneceANingunPeriodoTestCase() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(false);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		assertFalse(casa1.perteneceLaFechaAAlgunPeriodo(fecha3));
	}
	
	@Test
	void testPrecioParaLaFechaCuandoPerteneceAlPeriodo1() {
		
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		double precio = casa1.obtenerElPrecioParaLaFecha(fecha1);
		
		assertEquals(precio,2000d);
	}
	
	@Test
	void TestPrecioPorDefecto() {
		
		double precio = casa1.getPrecioPorDefecto();
		
		assertEquals(precio,3000d);
	}
	
	@Test
	void TestPrecioParaLaFechaCuandoPerteneceAlPeriodo2() {
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(true);
		
		when(periodoPrecio2.getPrecio()).thenReturn(4000d);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		double precio = casa1.precioParaLaFecha(fecha3);
		
		assertEquals(precio,4000d);
	}
	
	@Test
	void TestPrecioParaFechasDelPeriodo1() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fecha1);
		fechas.add(fecha2);
		
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio1.perteneceLaFecha(fecha2)).thenReturn(true);
		
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		double precio = casa1.valorPorDias(fechas);
		
		assertEquals(precio, 4000d);
	}
	
	@Test
	void TestPrecioParaFechaPeriodo1YFechaPeriodo2() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fecha1);
		fechas.add(fecha3);
		
		when(periodoPrecio1.perteneceLaFecha(fecha1)).thenReturn(true);
		when(periodoPrecio2.perteneceLaFecha(fecha3)).thenReturn(true);
		
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		when(periodoPrecio2.getPrecio()).thenReturn(3000d);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		double precio = casa1.valorPorDias(fechas);
		
		assertEquals(precio, 5000d);
	}
	
	@Test
	void TestPrecioParaFechasDelPeriodo1YQueNoPertenecenANingunPeriodo() {
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
		fechas.add(fecha3);
		fechas.add(fecha4);
		
		when(periodoPrecio1.perteneceLaFecha(fecha3)).thenReturn(true);
		when(periodoPrecio1.perteneceLaFecha(fecha4)).thenReturn(false);
		when(periodoPrecio2.perteneceLaFecha(fecha4)).thenReturn(false);
		
		when(periodoPrecio1.getPrecio()).thenReturn(2000d);
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		double precio = casa1.valorPorDias(fechas);
		
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
		
		casa1.establecerPeriodosConPrecios(periodoPrecio1);
		casa1.establecerPeriodosConPrecios(periodoPrecio2);
		
		double precio = casa1.valorPorDias(fechas);
		
		assertEquals(precio, 6000d);
	}
	

}
