package usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import reservas.Categoria;
import sitio.Sitio;

public class Administrador  {

	public void darDeAltaCategoriaParaInmueble(Sitio sitio, Categoria categoria) {
		// TODO Auto-generated method stub
		sitio.altaDeCategoriaInmueble( categoria);
		
	}

	public void darDeAltaCategoriaParaPropietario(Sitio sitio, Categoria categoria) {
		// TODO Auto-generated method stub
		sitio.altaDeCategoriaPropietario(categoria);
	}

	public void darDeAltaCategoriaParaInquilino(Sitio sitio, Categoria categoria) {
		// TODO Auto-generated method stub
		sitio.altaDeCategoriaInquilino(categoria);
	}

	public void darDeAltaTipoDeInmueble(Sitio sitio, String tipo) {
		// TODO Auto-generated method stub
		sitio.altaDeTipoInmueble(tipo);
	}

	public void darDeAltaServicio(Sitio sitio, String servicio) {
		// TODO Auto-generated method stub
		sitio.altaServicio(servicio);
		
	}

	public List<Usuario> topTenInquilinos(Sitio sitio) {
		// TODO Auto-generated method stub
		List <Usuario> usuarios= sitio.usuarios();
		List<Usuario> topTen= new ArrayList<Usuario>();
		int acumulador= 0;
		
		for(int i=0; i<usuarios.size(); i++) {
			
			if(i<10) {
			
			topTen.add(usuarios.get(i));
			acumulador++;
			}
		}
		
		return topTen;
	}
	
	public ArrayList<Usuario> usuariosRank(Sitio sitio, ArrayList<Usuario> userss) {
		
	ArrayList<Usuario> users=sitio.usuariosQueAlquilaron();
		
	System.out.println(userss);
		
		Collections.sort(userss);

		

		   System.out.println(userss);
		        
		   Collections.sort(userss, Collections.reverseOrder());
		   System.out.println(userss);
		return userss;
		
			
}
	


	public void inmueblesLibres(Sitio sitio) {
		// TODO Auto-generated method stub
		
	}

	public void tasaOcupacion(Sitio sitio) {
		// TODO Auto-generated method stub
		
	}



}
