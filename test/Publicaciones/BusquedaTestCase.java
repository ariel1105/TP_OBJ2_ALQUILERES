package Publicaciones;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Busqueda.Buscador;
import Busqueda.BuscadorCompleto;
import Busqueda.Busqueda;
import Busqueda.BusquedaConHuespedes;
import Busqueda.BusquedaConParametrosObligatorios;
import Busqueda.BusquedaConPrecios;
import inmueble.Inmueble;
import reservas.Publicacion;
import reservas.Sitio2;

class BusquedaTestCase {

		private Publicacion publicacion1;
		private Publicacion publicacion2;
		private Publicacion publicacion3;
		private Publicacion publicacion4;
		private Publicacion publicacion5;
		
		private Inmueble inmueble1;
		private Inmueble inmueble2;
		private Inmueble inmueble3;
		private Inmueble inmueble4;
		private Inmueble inmueble5;
		
		private Sitio2 sitio;
		private Busqueda busqueda1;
		
		private Buscador buscadorObligatorio;
		private Buscador buscadorHuespedes;
		private Buscador buscadorPrecios;
		private Buscador buscadorCompleto;

		@BeforeEach
			void setUp() throws Exception {
			
			publicacion1 = mock(Publicacion.class);
			publicacion2 = mock(Publicacion.class);
			publicacion3 = mock(Publicacion.class);
			publicacion4 = mock(Publicacion.class);
			publicacion5 = mock(Publicacion.class);
			
			inmueble1= mock(Inmueble.class);
			inmueble2= mock(Inmueble.class);
			inmueble3= mock(Inmueble.class);
			inmueble4= mock(Inmueble.class);
			inmueble5= mock(Inmueble.class);
			
		 	busqueda1= new Busqueda("Pinamar", LocalDate.of(2022, 1, 14), LocalDate.of(2022, 1, 21), 4, 20000.0, 50000.0);
			

			//sitio= new Sitio();
		 	
			sitio.agregarPublicacion(publicacion1);
			sitio.agregarPublicacion(publicacion2);
			sitio.agregarPublicacion(publicacion3);
			sitio.agregarPublicacion(publicacion4);
			sitio.agregarPublicacion(publicacion5);
			
			buscadorObligatorio= new BusquedaConParametrosObligatorios();
			buscadorHuespedes= new BusquedaConHuespedes();
			buscadorPrecios= new BusquedaConPrecios();
			buscadorCompleto= new BuscadorCompleto();
			
			
			when(publicacion1.getInmuebleAsignado()).thenReturn(inmueble1);
			when(publicacion2.getInmuebleAsignado()).thenReturn(inmueble2);
			when(publicacion3.getInmuebleAsignado()).thenReturn(inmueble3);
			when(publicacion4.getInmuebleAsignado()).thenReturn(inmueble4);
			when(publicacion5.getInmuebleAsignado()).thenReturn(inmueble5);
			
			when(publicacion1.getInmuebleAsignado().getCiudad()).thenReturn("Pinamar");
			when(publicacion2.getInmuebleAsignado().getCiudad()).thenReturn("Pinamar");
			when(publicacion3.getInmuebleAsignado().getCiudad()).thenReturn("asd");
			when(publicacion4.getInmuebleAsignado().getCiudad()).thenReturn("Pinamar");
			when(publicacion5.getInmuebleAsignado().getCiudad()).thenReturn("asd");
			
			when(publicacion1.getInmuebleAsignado().estaDisponible(LocalDate.of(2022, 1, 14), LocalDate.of(2022, 1, 21))).thenReturn(true);
			when(publicacion2.getInmuebleAsignado().estaDisponible(LocalDate.of(2022, 1, 14), LocalDate.of(2022, 1, 21))).thenReturn(true);
			when(publicacion4.getInmuebleAsignado().estaDisponible(LocalDate.of(2022, 1, 14), LocalDate.of(2022, 1, 21))).thenReturn(true);
			
		}
		
		@Test
		void agregadoDePublicaciones() {
			assertEquals(5, sitio.getPublicaciones().size());
		}
		@Test
		void buscarConParametroObligatorio() {
			sitio.setBuscador(buscadorObligatorio);
			assertEquals(sitio.getBuscadorActual(), buscadorObligatorio);
						
		List <Publicacion> publicacionesCon= sitio.busquedaDeInmuebles(busqueda1);
			assertEquals(publicacionesCon.size(), 3);
			
		}
		@Test
		void buscarConHuespedes() {
			sitio.setBuscador(buscadorHuespedes);
			assertEquals(sitio.getBuscadorActual(), buscadorHuespedes);
			
			when(publicacion1.getInmuebleAsignado().getCapacidad()).thenReturn(4);
			when(publicacion2.getInmuebleAsignado().getCapacidad()).thenReturn(5);
			when(publicacion3.getInmuebleAsignado().getCapacidad()).thenReturn(4);
			when(publicacion4.getInmuebleAsignado().getCapacidad()).thenReturn(8);
			when(publicacion5.getInmuebleAsignado().getCapacidad()).thenReturn(4);
			
			List <Publicacion> publicacionesCon= sitio.busquedaDeInmuebles(busqueda1);
			assertEquals(publicacionesCon.size(), 3);
			
			
		}
		
		@Test
		void buscarConPrecio() {
			
			sitio.setBuscador(buscadorPrecios);
			assertEquals(sitio.getBuscadorActual(), buscadorPrecios);
			
			when(publicacion1.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(40000.0);
			when(publicacion2.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(20000.0);
			when(publicacion3.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(10000.0);
			when(publicacion4.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(15000.0);
			when(publicacion5.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(50000.0);
			
			List <Publicacion> publicacionesCon= sitio.busquedaDeInmuebles(busqueda1);
			assertEquals(publicacionesCon.size(), 2);
		
			
		}
		
		@Test
		void buscarCompleto() {
			sitio.setBuscador(buscadorCompleto);
			assertEquals(sitio.getBuscadorActual(), buscadorCompleto);
			
			when(publicacion1.getInmuebleAsignado().getCapacidad()).thenReturn(4);
			when(publicacion2.getInmuebleAsignado().getCapacidad()).thenReturn(5);
			when(publicacion3.getInmuebleAsignado().getCapacidad()).thenReturn(4);
			when(publicacion4.getInmuebleAsignado().getCapacidad()).thenReturn(8);
			when(publicacion5.getInmuebleAsignado().getCapacidad()).thenReturn(4);
			
			when(publicacion1.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(40000.0);
			when(publicacion2.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(20000.0);
			when(publicacion3.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(10000.0);
			when(publicacion4.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(15000.0);
			when(publicacion5.getInmuebleAsignado().getPrecioPorDefecto()).thenReturn(50000.0);
			
			List <Publicacion> publicacionesCon= sitio.busquedaDeInmuebles(busqueda1);
			assertEquals(publicacionesCon.size(), 2);
			
			
		}
		
		@Test
		void inmuebleDisponible() {
			
			when (inmueble1.estaDisponible(LocalDate.of(2022, 1, 21), LocalDate.of(2022, 1, 21))).thenReturn(true);
		}
		
		
		
		
}
