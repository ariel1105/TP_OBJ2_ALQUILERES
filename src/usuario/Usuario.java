package usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Categorias.Categoria;
import Suscripciones.AppUser;
import administradorDeReservas.AdministadorDeReservasInquilino;
import inmueble.DatosDePago;
import inmueble.Inmueble;
import perfiles.PerfilPropietario;
import perfiles.PerfilInquilino;
import perfiles.PerfilPropietario;
import reservas.Reserva;
import sitio.Sitio;

public class Usuario implements PuntuablePorEstadia {
	private String nombreCompleto;
	private String mail;
	private String telefono;
	private AdministadorDeReservasInquilino admin;
	private List<Inmueble> inmuebles;

	private PerfilInquilino perfilInquilino;
	private PerfilPropietario perfilPropietario;
	private LocalDate fechaQueSeRegistro;
	private AppUser aplicacionMovil;
	
	public Usuario(String nombreCompleto, String mail, String telefono, AdministadorDeReservasInquilino admin, AppUser aplicacion){
	
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.telefono = telefono;
		this.inmuebles = new ArrayList<Inmueble>();
		this.admin = admin;
		this.aplicacionMovil= aplicacion;
	}
	
	public String getTelefono() {
		return this.telefono;
	}

	public String getMail() {
		return this.mail;
	}
	
	public void setPerfilInquilino(PerfilInquilino perfil) {
		this.perfilInquilino = perfil;
	}

	public void setPerfilPropietario(PerfilPropietario perfil) {
		this.perfilPropietario = perfil;
	}
	
	public AdministadorDeReservasInquilino getAdmin() {
		return this.admin;
	}

	public void registrarse(Sitio sitio) {
		sitio.registrarUsuario(this);
		fechaQueSeRegistro= LocalDate.now();
	}

	public LocalDate getFechaQueSeRegistro() {
		return fechaQueSeRegistro;
	}

	
	public void solicitarReserva(Reserva reserva, Inmueble inmueble) {
		if (reserva.getDatosDePago().sonDatosAdmitidosPara(inmueble)) {
			inmueble.getPropietario().recibirSolicitudDeReserva(reserva);
			this.admin.ingresar(reserva);
		}
	}

	public void recibirSolicitudDeReserva(Reserva reserva) {
		reserva.getInmueble().agregarReserva(reserva);
	}

	
	public void confirmar(Reserva reserva, Sitio sitio) {
		if (reserva.getInmueble().tieneReserva(reserva)) {
			reserva.confirmarseEn(sitio);
		}
	}


	public Integer vecesQueAlquilaron() {
		return this.admin.cantidadeDeReservas();
	}

	public long tiempoComoUser() {
		long result = ChronoUnit.DAYS.between(this.getFechaQueSeRegistro(), LocalDate.now());
		return result;
	}
	
	public void puntuarComoInquilino(PuntuablePorEstadia puntuable, Categoria categoria, int puntos, LocalDate fechaActual) {
		if(puntuable.puedeRecibirPuntuacionPorEstadiaPor(this, fechaActual)) {
			puntuable.recibirPuntuacionPorEstadia(categoria, puntos);
		}
	}

	@Override
	public void recibirPuntuacionPorEstadia(Categoria categoria, int puntos) {
		this.perfilPropietario.recibirPuntuacion(categoria, puntos);
	}

	public void recibirPuntuacionComoInquilino(Categoria cat, int puntos) {
		this.perfilInquilino.recibirPuntuacion(cat, puntos);
	}

	public boolean puedeRecibirPuntuacionComoInquilinoPor(Usuario propietario, LocalDate fechaActual) {
		return this.admin.leAlquiloA(propietario, fechaActual);
	}

	public void actualizarPrecioAInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		if (this.getInmuebles().contains(inmueble)) {			
			inmueble.cambiarPrecio();
		}
	}

	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	public void puntuarComoPropietario(Usuario inquilino, Categoria cat, int puntos, LocalDate fechaActual) {
		if(inquilino.puedeRecibirPuntuacionComoInquilinoPor(this, fechaActual)) {
			inquilino.recibirPuntuacionComoInquilino(cat, puntos);
		}
	}

	public void agregarInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		this.inmuebles.add(inmueble);
	}
	
	@Override
	public boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario inquilino, LocalDate fechaActual) {
		return inquilino.getAdmin().leAlquiloA(this, fechaActual);

	}

}


