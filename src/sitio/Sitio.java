package sitio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import inmueble.Inmueble;
import reservas.Reserva;

import usuario.Usuario;

public class Sitio {
	private ArrayList<Usuario> usuariosRegistrados;
	private ArrayList<Inmueble> inmueblesPublicados;
	private ArrayList<Reserva> reservasConfirmadas;
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
	}

	public ArrayList<Usuario> obtenerUsuariosRegistrados() {
		// TODO Auto-generated method stub
		return usuariosRegistrados;
	}

	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		if( this.elUsuarioEstaRegistrado(usuario)) {
			usuariosRegistrados.add(usuario);
		}
	}

	public boolean elUsuarioEstaRegistrado(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuariosRegistrados.contains(usuario);
	}
	

	public void agregar(Inmueble inmueble) {
		this.inmueblesPublicados.add(inmueble);
	}

	public void agegarReserva(Reserva reserva) {
		this.reservasConfirmadas.add(reserva);
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

	public List<Usuario> usuariosQueAlquilaron() {
		// TODO Auto-generated method stub
		
		List <Usuario> usuarios = new ArrayList<Usuario>();
		for (int i=0; i< usuariosRegistrados.size(); i++) {
			if (usuariosRegistrados.get(i).vecesQueAlquilaron() > 0)
				usuarios.add(usuariosRegistrados.get(i));
			
		}
		return  usuarios;
	}

	public ArrayList<Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}
	

	public ArrayList<Inmueble> getInmueblesPublicados() {
		return inmueblesPublicados;
	}

	public void addObserver(SitioWeb trivago) {
		// TODO Auto-generated method stub
		this.listenersPaginas.add(trivago);
	}

	public void notificarBajaDePrecio(String tipoDeInmueble, Double precio) {
		// TODO Auto-generated method stub
		
		for (SitioWeb listener : this.listenersPaginas) {
				
			if (listener.getInmueblesConInteres().contains(tipoDeInmueble)) {
				listener.publish("El inmueble " + tipoDeInmueble + " esta a solo " + precio + " pesos!.");
			}
		}
		
	}

	
	
	public ArrayList<Reserva> getResevasConfirmadas() {
		return this.reservasConfirmadas;
	}
	



}
