package sitio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import inmueble.Inmueble;
import reservas.Categoria;
import reservas.Reserva;

import usuario.Usuario;

public class Sitio implements Comparator<Usuario> {
	private ArrayList<Usuario> usuariosRegistrados;
	private ArrayList<Inmueble> inmueblesPublicados;
	private ArrayList<Reserva> reservasConfirmadas;
	private ArrayList<Categoria> categoriasParaInmueble;
	private ArrayList <Categoria> categoriasParaPropietario;
	private ArrayList <Categoria> categoriasParaInquilino;
	private ArrayList <String> tipoDeInmuebles;
	private ArrayList <String> servicios;
	public Sitio() {
		this.usuariosRegistrados = new ArrayList<Usuario>();
	}

	public ArrayList<Usuario> obtenerUsuariosRegistrados() {
		// TODO Auto-generated method stub
		return usuariosRegistrados;
	}

	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		if(! this.elUsuarioEstaRegistrado(usuario)) {
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
		this.categoriasParaInmueble.add(categoria);
		
		
	}

	public void altaDeCategoriaPropietario(Categoria categoria) {
		// TODO Auto-generated method stub
		this.categoriasParaPropietario.add(categoria);
		
	}

	public void altaDeCategoriaInquilino(Categoria categoria) {
		// TODO Auto-generated method stub
		this.categoriasParaInquilino.add(categoria);
	}

	public void altaDeTipoInmueble(String tipo) {
		// TODO Auto-generated method stub
		this.tipoDeInmuebles.add(tipo);
		
	}

	public void altaServicio(String servicio) {
		// TODO Auto-generated method stub
		this.servicios.add(servicio);
		
	}

	public ArrayList<Usuario> usuariosQueAlquilaron() {
		// TODO Auto-generated method stub
		
		ArrayList <Usuario> usuarios = null;
		for (int i=0; i< usuariosRegistrados.size(); i++) {
			if (usuariosRegistrados.get(i).vecesQueAlquilaron() > 0)
				usuarios.add(usuariosRegistrados.get(i));
			
		}
		return  usuarios;
	}

	public ArrayList<Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}
	

	
	public ArrayList<Usuario> usuarios() {
		
		ArrayList<Usuario> users=this.usuariosQueAlquilaron();
		
		users.sort(Comparator.comparing(Usuario::vecesQueAlquilaron));
		
		return users;
			}

	


	@Override
	public int compare(Usuario u1, Usuario u2) {
		// TODO Auto-generated method stub
		return new Integer(u1.vecesQueAlquilaron()).compareTo(new Integer(u2.vecesQueAlquilaron()));
	
	}

}
