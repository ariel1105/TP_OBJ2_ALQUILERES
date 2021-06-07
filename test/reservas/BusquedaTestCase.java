package reservas;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusquedaTestCase {

		private Publicacion publicacionDepartamento;
		private Sitio sitio;
		private Inmueble departamento;
		private Busqueda busqueda1;


		@BeforeEach
			void setUp() throws Exception {
			
			busqueda1= new Busqueda("Villa Gesell", LocalDate.of(2022, 1, 14), LocalDate.of(2022, 1, 21), 4, 20000.0, 50000.0);
			sitio= mock(Sitio.class);
			publicacionDepartamento = mock(Publicacion.class);
			departamento= mock(Inmueble.class); 
			
		}
		@Test
		
		void buscarPublicaciones() {
			
			List <Publicacion> publicacionesFiltradas = sitio.busquedaDeInmuebles(busqueda1);
			
		}
}
