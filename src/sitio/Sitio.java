package sitio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import Suscripciones.SitioWeb;
import inmueble.Inmueble;
import perfiles.PerfilPropietario;
import perfiles.PerfilInmueble;
import perfiles.PerfilInquilino;
import reservas.Reserva;

import usuario.Usuario;

public class Sitio {
	private ArrayList<Usuario> usuariosRegistrados;
	private ArrayList<Inmueble> inmueblesPublicados;
	private ArrayList<Categoria> categoriasParaInmueble;
	private ArrayList <Categoria> categoriasParaPropietario;
	private ArrayList <Categoria> categoriasParaInquilino;
	private ArrayList <String> tipoDeInmuebles;
	private ArrayList <String> servicios;
	
	private List <SitioWeb> listenersPaginas;
	
	public Sitio() {
		this.usuariosRegistrados = new ArrayList<Usuario>();
		this.inmueblesPublicados= new ArrayList<Inmueble>();
		this.listenersPaginas= new ArrayList<SitioWeb>();
		this.categoriasParaInmueble= new ArrayList <Categoria>();
		this.categoriasParaPropietario= new ArrayList <Categoria>();
		this.categoriasParaInquilino= new ArrayList <Categoria>();
		this.tipoDeInmuebles= new ArrayList <String>();
		this.servicios= new ArrayList<String>();
	}



	public ArrayList<Usuario> obtenerUsuariosRegistrados() {
		// TODO Auto-generated method stub
		return usuariosRegistrados;
	}


	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		if(!this.elUsuarioEstaRegistrado(usuario)) {
			this.crearPerfilInquilino(usuario);
			usuariosRegistrados.add(usuario);
		}
	}

	public void crearPerfilInquilino(Usuario usuario) {
		PerfilInquilino perfil = new PerfilInquilino(this.categoriasParaInquilino, usuario);
		usuario.setPerfilInquilino(perfil);
	}

	public boolean elUsuarioEstaRegistrado(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuariosRegistrados.contains(usuario);
	}
	

	public void publicar(Inmueble inmueble, Usuario propietario) {
		PerfilPropietario perfil = new PerfilPropietario(categoriasParaPropietario, propietario);
		propietario.setPerfilPropietario(perfil);
		this.crearPerfilInmueble(inmueble, perfil);
		this.inmueblesPublicados.add(inmueble);
	}

	public void crearPerfilInmueble(Inmueble inmueble, PerfilPropietario perfil) {
		PerfilInmueble perfil2 = new PerfilInmueble(this.categoriasParaInmueble, inmueble, perfil);
		inmueble.setPerfilInmueble(perfil2);
		
	}

	public void enviarMailDeConfirmacion(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	public void altaDeCategoriaInmueble(Categoria categoria) {
		// TODO Auto-generated method stub
		if (!categoriasParaInmueble.contains(categoria)) {
		this.categoriasParaInmueble.add(categoria);
		}
		
	}

	public void altaDeCategoriaPropietario(Categoria categoria) {
		// TODO Auto-generated method stub
		if (!categoriasParaPropietario.contains(categoria)) {
		this.categoriasParaPropietario.add(categoria);
		}
	}

	public void altaDeCategoriaInquilino(Categoria categoria) {
		// TODO Auto-generated method stub
		if (!categoriasParaInquilino.contains(categoria)) {
		this.categoriasParaInquilino.add(categoria);
		}
	}

	public void altaDeTipoInmueble(String tipo) {
		// TODO Auto-generated method stub
		if (!tipoDeInmuebles.contains(tipo)) {
		this.tipoDeInmuebles.add(tipo);
		}
	}

	public void altaServicio(String servicio) {
		// TODO Auto-generated method stub
		if (!servicios.contains(servicio)) {
		this.servicios.add(servicio);
		}
	}

	public List<Usuario> usuariosQueAlquilaron() { //Esto tiene que ir en administrador
		// TODO Auto-generated method stub
		
		List <Usuario> usuarios = new ArrayList<Usuario>();
		for (int i=0; i< usuariosRegistrados.size(); i++) {
			if (usuariosRegistrados.get(i).vecesQueAlquilaron() > 0) {
				usuarios.add(usuariosRegistrados.get(i));
			}
		}
		return  usuarios;
	}



	public ArrayList<Inmueble> getInmueblesPublicados() {
		return inmueblesPublicados;
	}



	public ArrayList<Reserva> todasLasResevasConfirmadas() {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		for (Usuario us : this.usuariosRegistrados) {
			reservas.addAll(us.getReservasConfirmadas());
		}
		return reservas;
	}
	


	public ArrayList<Categoria> getCategoriasParaInmueble() {
		return categoriasParaInmueble;
	}

	public ArrayList<Categoria> getCategoriasParaPropietario() {
		return categoriasParaPropietario;
	}

	public ArrayList<Categoria> getCategoriasParaInquilino() {
		return categoriasParaInquilino;
	}
	
	public ArrayList<String> getTipoDeInmuebles() {
		return tipoDeInmuebles;
	}

	public ArrayList<String> getServicios() {
		return servicios;
	}
}
