package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import administradorDeReservas.AdministadorDeReservasInquilino;
import inmueble.DatosDePago;
import inmueble.Inmueble;
import reservas.Reserva;
import sitio.Sitio;

public class Usuario  {
	private String nombreCompleto;
	private String mail;
	private String telefono;
	private AdministadorDeReservasInquilino admin;
	private ArrayList<Inmueble>inmuebles;
	private ArrayList<Reserva>reservasPendientesDeConfirmacion;
	private ArrayList<Reserva>reservasConfirmadas;
	private LocalDate fechaActual;
	private Integer vecesQueAlquilo;
	private Integer inmueblesAlquilados;
	
	
public	Usuario(String nombreCompleto, String mail, String telefono){
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.telefono = telefono;
		this.inmuebles = new ArrayList<Inmueble>();
		this.reservasPendientesDeConfirmacion = new ArrayList<Reserva>();
		this.reservasConfirmadas = new ArrayList<Reserva>();
		this.admin = new AdministadorDeReservasInquilino(fechaActual);//lo ideal no seria pasarlo por 
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
		return vecesQueAlquilo;
	}






	public int tiempoComoUsuario() {
		return 0;
	}

	public ArrayList<Reserva> getReservasConfirmadas() {
		return this.reservasConfirmadas;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public String getMail() {
		return this.mail;
	}


	public void actualizarPrecioAInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		if (this.getInmuebles().contains(inmueble)) {
			
			inmueble.cambiarPrecio();
			
		}
	}



	
	
}


