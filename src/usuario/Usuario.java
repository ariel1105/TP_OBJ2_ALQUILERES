package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import administradorDeReservas.AdministadorDeReservas;
import inmueble.DatosDePago;
import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Sitio;

public class Usuario  implements Comparable<Usuario> {
	private String nombreCompleto;
	private String mail;
	private String telefono;
	private AdministadorDeReservas admin;
	private ArrayList<Inmueble>inmuebles;
	private ArrayList<Reserva>reservasPendientesDeConfirmacion;
	private LocalDate fechaActual;
	
	
	Usuario(String nombreCompleto, String mail, String telefono){
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.telefono = telefono;
		this.inmuebles = new ArrayList<Inmueble>();
		this.reservasPendientesDeConfirmacion = new ArrayList<Reserva>();
		this.admin = new AdministadorDeReservas(fechaActual);//lo ideal no seria pasarlo por 
														     //parametro, en un programa normal
															 //la fecha se instanciaria de otra manera
	}

	public void registrarse(Sitio sitio) {
		sitio.registrarUsuario(this);
	}

	public void publicar(Inmueble inmueble, Sitio sitio) {
		if (!sitio.elUsuarioEstaRegistrado(this)) {
			sitio.agregar(inmueble);
			this.agregar(inmueble);
		}
	}

	public ArrayList<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	public void agregar(Inmueble inmueble) {
		this.inmuebles.add(inmueble);
	}

	public void solicitarReserva(Reserva reserva) {
		if (reserva.getDatosDePago().sonDatosAdmitidosPara(reserva.getInmueble())) {
			reserva.getInmueble().getDueño().recibirSolicitudDeReserva(reserva);
		}
	}

	public void recibirSolicitudDeReserva(Reserva reserva) {
		this.reservasPendientesDeConfirmacion.add(reserva);
	}

	public ArrayList<Reserva> getReservasPendientes() {
		return this.reservasPendientesDeConfirmacion;
	}

	public void confirmar(Reserva reserva, Sitio sitio) {
		if (this.reservasPendientesDeConfirmacion.contains(reserva)) {
			reserva.confirmarseEn(sitio);
			this.reservasPendientesDeConfirmacion.remove(reserva);
		}
	}

	public Integer vecesQueAlquilaron() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int compareTo(Usuario o) {
		// TODO Auto-generated method stub
		return new Integer(this.vecesQueAlquilaron()).compareTo(new Integer(o.vecesQueAlquilaron()));
	}


	
	
}


