package sitio;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import inmueble.Inmueble;
import reservas.Reserva;
import usuario.Usuario;

public class Sitio {
	private ArrayList<Usuario> usuariosRegistrados;
	private ArrayList<Inmueble> inmueblesPublicados;
	private ArrayList<Reserva> reservasConfirmadas;
	
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

}
