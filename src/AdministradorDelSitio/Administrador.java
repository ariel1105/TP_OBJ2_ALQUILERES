package AdministradorDelSitio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Categorias.Categoria;
import inmueble.Inmueble;
import sitio.Sitio;
import usuario.Usuario;

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
		List <Usuario> usuarios= this.usuariosRank(sitio);
		List<Usuario> topTen= new ArrayList<Usuario>();
		
		for(int i=0; i<usuarios.size(); i++) {
			
			if(i<10) {
			
			topTen.add(usuarios.get(i));
			}
		}
		
		return topTen;
	}
	
	public List<Usuario> usuariosRank(Sitio sitio ) {
		
		List<Usuario> users=this.usuariosQueAlquilaron(sitio);
		
		Collections.sort(users, new CompararUsuarios());
		
		return users;
		
}
	
	public List<Usuario> usuariosQueAlquilaron(Sitio sitio) { //Esto tiene que ir en administrador
		// TODO Auto-generated method stub
		
		List <Usuario> usuarios = new ArrayList<Usuario>();
		for (int i=0; i< sitio.obtenerUsuariosRegistrados().size(); i++) {
			if (sitio.obtenerUsuariosRegistrados().get(i).vecesQueAlquilaron() > 0) {
				usuarios.add(sitio.obtenerUsuariosRegistrados().get(i));
			}
		}
		return  usuarios;
	}
	


	public List <Inmueble> inmueblesLibres(Sitio sitio) {
		// TODO Auto-generated method stub
		
		List <Inmueble> inmuebles= sitio.getInmueblesPublicados();
		List <Inmueble> inmueblesLibres= new ArrayList<Inmueble>();
		for (int i=0; i < inmuebles.size(); i++){
			
			if (!inmuebles.get(i).estaReservado()) {
				
				inmueblesLibres.add(inmuebles.get(i));
				
			}
		}
		return inmueblesLibres;
	}

	public Double tasaOcupacion(Sitio sitio) {
		// TODO Auto-generated method stub
		int cantInmuebles= sitio.getInmueblesPublicados().size();
		return (double) (cantInmuebles / inmueblesReservados(sitio));
		
		
	}

	public Integer inmueblesReservados(Sitio sitio) {
		List <Inmueble> inmuebles= sitio.getInmueblesPublicados();
		int cantidadDeInmueblesAlquilados= 0;
		for (int i=0; i < inmuebles.size(); i++){
			
			if (inmuebles.get(i).estaReservado()) {
				
				cantidadDeInmueblesAlquilados++;
				
			}
		}
		return cantidadDeInmueblesAlquilados;
	

		
	}


}