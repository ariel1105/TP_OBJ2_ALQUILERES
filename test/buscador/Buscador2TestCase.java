package buscador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import sitio.Sitio;

class Buscador2TestCase {
	

	private BuscadorCompuesto buscadorConHuespedes;
	private BuscadorCompuesto buscadorConPrecioMinimoYMaximo;
	private BuscadorCompuesto buscadorConTodosLosParametros;
	private BuscadorCompuesto buscadorSimple;
	private IBuscador buscadorHuespedes;
	private IBuscador buscadorCiudad;
	private IBuscador buscadorFechaEntrada;
	private IBuscador buscadorPrecioMinimoYMaximo;
	private ArrayList<Inmueble> inmuebles;
	private Sitio sitio;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private Inmueble inmueble4;
	private Inmueble inmueble5;
	private Inmueble inmueble6;
	
	@BeforeEach
	void setUp() {
		sitio= mock(Sitio.class);
		inmueble1= mock(Inmueble.class);
		inmueble2= mock(Inmueble.class);
		inmueble3= mock(Inmueble.class);
		inmueble4= mock(Inmueble.class);
		inmueble5= mock(Inmueble.class);
		inmueble6= mock(Inmueble.class);
		inmuebles= new ArrayList<Inmueble>();
		inmuebles.add(inmueble1);
		inmuebles.add(inmueble2);
		inmuebles.add(inmueble3);
		inmuebles.add(inmueble4);
		inmuebles.add(inmueble5);
		inmuebles.add(inmueble6);
		//buscadorHuespedes= new BuscadorCompuesto("Carilo", LocalDate.of(2019,8,31), LocalDate.of(2019,9,15), 8, null, null);
		buscadorConHuespedes= new BuscadorCompuesto("Carilo", LocalDate.of(2019,8,31), LocalDate.of(2019,9,15));
		//buscadorPrecioMinimoYMaximo= new BuscadorCompuesto("Carilo", LocalDate.of(2019,8,31), LocalDate.of(2019,9,15), null, 30000.0, 60000.0);
		buscadorConPrecioMinimoYMaximo= new BuscadorCompuesto("Carilo", LocalDate.of(2019,8,31), LocalDate.of(2019,9,15));
		//buscadorConTodosLosParametros= new BuscadorCompuesto("Carilo", LocalDate.of(2019,8,31), LocalDate.of(2019,9,15), 8, 30000.0, 60000.0);
		buscadorConTodosLosParametros= new BuscadorCompuesto("Carilo", LocalDate.of(2019,8,31), LocalDate.of(2019,9,15));
		buscadorFechaEntrada= new BuscadorFechas(LocalDate.of(2019,8,31), LocalDate.of(2019,9,15));
		
		buscadorSimple= new BuscadorCompuesto("Carilo", LocalDate.of(2019,8,31), LocalDate.of(2019,9,15));
		buscadorHuespedes= new BuscadorHuespedes(8);
		buscadorCiudad= new BuscadorCiudad("Carilo");
		buscadorPrecioMinimoYMaximo= new BuscadorPrecioMinimoYMaximo(30000.0, 60000.0);
		
		
		
		when(inmueble1.getCiudad()).thenReturn("Carilo");
		when(inmueble2.getCiudad()).thenReturn("Carilo");
		when(inmueble3.getCiudad()).thenReturn("Mar Del Plata");
		when(inmueble4.getCiudad()).thenReturn("Mar Del Plata");
		when(inmueble5.getCiudad()).thenReturn("Carilo");
		when(inmueble6.getCiudad()).thenReturn("Carilo");
		
		when(inmueble1.estaDisponible(LocalDate.of(2019,8,31), LocalDate.of(2019,9,15))).thenReturn(true);
		when(inmueble2.estaDisponible(LocalDate.of(2019,8,31), LocalDate.of(2019,9,15))).thenReturn(true);
		when(inmueble3.estaDisponible(LocalDate.of(2019,8,31), LocalDate.of(2019,9,15))).thenReturn(true);
		when(inmueble4.estaDisponible(LocalDate.of(2019,8,31), LocalDate.of(2019,9,15))).thenReturn(true);
		when(inmueble5.estaDisponible(LocalDate.of(2019,8,31), LocalDate.of(2019,9,15))).thenReturn(true);
		when(inmueble6.estaDisponible(LocalDate.of(2019,8,31), LocalDate.of(2019,9,15))).thenReturn(true);
		
		
		when(sitio.getInmueblesPublicados()).thenReturn(inmuebles);
	}
	
	@Test
	void testBusquedaSimple() {
		List<Inmueble> inmueblesEsperados= new ArrayList<Inmueble>();
		inmueblesEsperados.add(inmueble1);
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble5);
		inmueblesEsperados.add(inmueble6);
	
		assertEquals(inmueblesEsperados, buscadorSimple.realizarBusqueda(sitio));
	}

	@Test
	void testBusquedaConExtraHuespedes() {
		ArrayList<Inmueble> inmueblesEsperados= new ArrayList<Inmueble>();
		inmueblesEsperados.add(inmueble1);
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble5);
		
		when(inmueble1.getCapacidad()).thenReturn(8);
		when(inmueble2.getCapacidad()).thenReturn(9);
		when(inmueble3.getCapacidad()).thenReturn(7);
		when(inmueble4.getCapacidad()).thenReturn(5);
		when(inmueble5.getCapacidad()).thenReturn(11);
		when(inmueble6.getCapacidad()).thenReturn(2);
		
		buscadorConHuespedes.agregarBuscador(buscadorHuespedes);
		
		assertEquals(inmueblesEsperados, buscadorConHuespedes.realizarBusqueda(sitio));

		
	}
	
	@Test
	void testBusquedaConExtraPrecioMinimoYMaximo() {
		ArrayList<Inmueble> inmueblesEsperados= new ArrayList<Inmueble>();
		inmueblesEsperados.add(inmueble2);

		inmueblesEsperados.add(inmueble5);
		inmueblesEsperados.add(inmueble6);
		
		when(inmueble1.getPrecioActual()).thenReturn(29999.0);
		when(inmueble2.getPrecioActual()).thenReturn(50000.0);
		when(inmueble3.getPrecioActual()).thenReturn(50000.0);
		when(inmueble4.getPrecioActual()).thenReturn(20000.0);
		when(inmueble5.getPrecioActual()).thenReturn(50000.0);
		when(inmueble6.getPrecioActual()).thenReturn(50000.0);
		
		buscadorConPrecioMinimoYMaximo.agregarBuscador(buscadorPrecioMinimoYMaximo);
		
		assertEquals(inmueblesEsperados, buscadorConPrecioMinimoYMaximo.realizarBusqueda(sitio));

	}
	@Test
	void testBusquedaConTodosLosParametros() {
		ArrayList<Inmueble> inmueblesEsperados= new ArrayList<Inmueble>();
		inmueblesEsperados.add(inmueble2);
		inmueblesEsperados.add(inmueble5);

		
		when(inmueble1.getCapacidad()).thenReturn(8);
		when(inmueble2.getCapacidad()).thenReturn(9);
		when(inmueble3.getCapacidad()).thenReturn(7);
		when(inmueble4.getCapacidad()).thenReturn(5);
		when(inmueble5.getCapacidad()).thenReturn(11);
		when(inmueble6.getCapacidad()).thenReturn(2);
		
		when(inmueble1.getPrecioActual()).thenReturn(29999.0);
		when(inmueble2.getPrecioActual()).thenReturn(50000.0);
		when(inmueble3.getPrecioActual()).thenReturn(50000.0);
		when(inmueble4.getPrecioActual()).thenReturn(20000.0);
		when(inmueble5.getPrecioActual()).thenReturn(50000.0);
		when(inmueble6.getPrecioActual()).thenReturn(50000.0);
		
	
		buscadorConTodosLosParametros.agregarBuscador(buscadorPrecioMinimoYMaximo);
		buscadorConTodosLosParametros.agregarBuscador(buscadorHuespedes);
		
		assertEquals(inmueblesEsperados, buscadorConTodosLosParametros.realizarBusqueda(sitio));
		
		
	}

}
