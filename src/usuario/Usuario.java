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
	/**
     * Esta clase se utilizará para saber atributos del usuario y hacer acciones como registrarse en un sitio, solicitar reserva, confirmar reserva
     * puntuar inquilinos y propietarios.
     * Es importante saber que el usuario puede ser tanto propietario como inquilino al mismo tiempo
     */
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
	/**
     * Constructor de la clase, se setearan los atributos dados como parametros
     */
	public String getTelefono() {
		return this.telefono;
	}
	/**
     * Metodo que retorna el telefono del usuario
     */
	public String getMail() {
		return this.mail;
	}
	/**
     * Metodo que retorna el mail del usuario
     */
	public void setPerfilInquilino(PerfilInquilino perfil) {
		this.perfilInquilino = perfil;
	}
	/**
     * Metodo setea el perfil inquilino dado como parametro
     */
	public void setPerfilPropietario(PerfilPropietario perfil) {
		this.perfilPropietario = perfil;
	}
	/**
     * Metodo que setea el perfil propietario dado como parametro
     */
	public AdministadorDeReservasInquilino getAdmin() {
		return this.admin;
	}

	public void registrarse(Sitio sitio, LocalDate fechaActual) {
		sitio.registrarUsuario(this);
		this.fechaQueSeRegistro = fechaActual;
	}
	/**
     * Metodo que registra al usuario dentro de un sitio y setea la fecha en la cual se registro.
     */
	public LocalDate getFechaQueSeRegistro() {
		return fechaQueSeRegistro;
	}
	/**
     * Metodo que retorna la fecha en la cual se registro
     */
	public int tiempoComoUser(LocalDate fechaActual) {
		return fechaActual.compareTo(fechaQueSeRegistro);
	}
	/**
     * Metodo que retorna la cantidad de tiempo que tiene como usuario
     */
	
	public void solicitarReserva(Reserva reserva, Inmueble inmueble) {
		if (reserva.getDatosDePago().sonDatosAdmitidosPara(inmueble)) {
			inmueble.getPropietario().recibirSolicitudDeReserva(reserva);
			this.admin.ingresar(reserva);
		}
	}
	/**
     * Metodo que solicita una reserva de cierto inmueble dado como parametro
     */
	public void recibirSolicitudDeReserva(Reserva reserva) {
		reserva.getInmueble().agregarReserva(reserva);
	}
	/**
     * Metodo que recibe una reserva dada como parametro, esta luego debe aceptarse.
     */
	
	public void confirmar(Reserva reserva, Sitio sitio) {
		if (reserva.getInmueble().tieneReserva(reserva)) {
			reserva.confirmarseEn(sitio);
		}
	}
	/**
     * Metodo que confirma o no una reserva
     */

	public Integer vecesQueAlquilaron() {
		return this.admin.cantidadeDeReservas();
	}
	/**
     * Metodo que retorna la cantidad de veces que alquilaron un inmueble (inquilinos)
     */
	
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
	/**
     * Metodo que actualiza el precio de uno de sus inmuebles teniendo en cuenta la fecha actual
     */
	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}
	/**
     * Metodo que retorna los inmuebles que posee
     */
	public void puntuarComoPropietario(Usuario inquilino, Categoria cat, int puntos, LocalDate fechaActual) {
		if(inquilino.puedeRecibirPuntuacionComoInquilinoPor(this, fechaActual)) {
			inquilino.recibirPuntuacionComoInquilino(cat, puntos);
		}
	}

	public void agregarInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		this.inmuebles.add(inmueble);
	}
	/**
     * Metodo que agrega un inmueble a los inmuebles del usuario
     */
	@Override
	public boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario inquilino, LocalDate fechaActual) {
		return inquilino.getAdmin().leAlquiloA(this, fechaActual);

	}

}


