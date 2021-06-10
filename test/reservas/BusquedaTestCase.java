package reservas;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusquedaTestCase {

		private Publicacion publicacionDepartamento;
		private Sitio sitio;
		private Inmueble departamento;
		private Busqueda busqueda1;
		private Busqueda busqueda2ConParametrosObligatorios;

		@BeforeEach
			void setUp() throws Exception {
			
		 	busqueda1= new Busqueda("Villa Gesell", LocalDate.of(2022, 1, 14), LocalDate.of(2022, 1, 21), 4, 20000.0, 50000.0);
			busqueda2ConParametrosObligatorios = new Busqueda("Pinamar", LocalDate.of(2022, 2, 14), LocalDate.of(2022, 2, 21));

			//sitio= new Sitio();
			publicacionDepartamento = mock(Publicacion.class);
			departamento= mock(Inmueble.class); 
			
		}
		
		@Test 
		void compararPublicaciones() {
			when(publicacionDepartamento.getInmuebleAsignado()).thenReturn(departamento);
			when(publicacionDepartamento.getInmuebleAsignado().getCiudad()).thenReturn("Pinamar");
			boolean booleano =sitio.compararPublicacion(busqueda2ConParametrosObligatorios, publicacionDepartamento);
			assertEquals(true, sitio.compararPublicacion(busqueda2ConParametrosObligatorios, publicacionDepartamento));
			
		}
		@Test
		
		void buscarPublicaciones() {
			
			//List <Publicacion> publicacionesFiltradas = sitio.busquedaDeInmuebles(busqueda1);
			
			
		}
}
