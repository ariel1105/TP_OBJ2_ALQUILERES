package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import administradorDeReservas.AdministadorDeReservasInquilino;
import inmueble.DatosDePago;
import inmueble.Inmueble;
import perfiles.PerfilDueño;
import perfiles.PerfilInquilino;
import reservas.Reserva;
import sitio.Categoria;
import sitio.Sitio;

public class Usuario implements PuntuablePorEstadia {
	private String nombreCompleto;
	private String mail;
	private String telefono;
	private AdministadorDeReservasInquilino admin;
	private ArrayList<Reserva>reservasPendientesDeConfirmacion;
	private ArrayList<Reserva>reservasConfirmadasDueño;
	private LocalDate fechaActual;
	private Integer vecesQueAlquilo;
	private Integer inmueblesAlquilados;
	private PerfilInquilino perfilInquilino;
	private PerfilDueño perfilPropietario;
	
	Usuario(String nombreCompleto, String mail, String telefono, AdministadorDeReservasInquilino admin){
	
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.telefono = telefono;
		this.reservasPendientesDeConfirmacion = new ArrayList<Reserva>();
		this.reservasConfirmadasDueño = new ArrayList<Reserva>();
		this.admin = admin;
	}
	
	public ArrayList<Reserva> getReservasPendientes() {
		return this.reservasPendientesDeConfirmacion;
	}

	public ArrayList<Reserva> getReservasConfirmadas() {
		return this.reservasConfirmadasDueño;
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

	public void setPerfilPropietario(PerfilDueño perfil) {
		this.perfilPropietario = perfil;
	}
	
	public AdministadorDeReservasInquilino getAdmin() {
		return this.admin;
	}

	public void registrarse(Sitio sitio) {
		sitio.registrarUsuario(this);
	}

	public void publicar(Inmueble inmueble, Sitio sitio) {
		if (sitio.elUsuarioEstaRegistrado(this)) {
			sitio.publicar(inmueble,this);
		}
	}
	
	public void solicitarReserva(Reserva reserva) {
		if (reserva.getDatosDePago().sonDatosAdmitidosPara(reserva.getInmueble())) {
			reserva.getInmueble().getDueño().recibirSolicitudDeReserva(reserva);
		}
	}

	public void recibirSolicitudDeReserva(Reserva reserva) {
		this.reservasPendientesDeConfirmacion.add(reserva);
	}

	
	public void confirmar(Reserva reserva, Sitio sitio) {
		if (this.reservasPendientesDeConfirmacion.contains(reserva)) {
			reserva.confirmarseEn(sitio);
			reserva.getInquilino().recibirConfirmacion(reserva);
			this.reservasPendientesDeConfirmacion.remove(reserva);
		}
	}

	public void recibirConfirmacion(Reserva reserva) {
		this.admin.ingresar(reserva);
	}

	public Integer vecesQueAlquilaron() {
		return this.admin.cantidadeDeReservas();
	}

	public int tiempoComoUsuario() {
		return 0;
	}

	public void puntuarComoInquilino(PuntuablePorEstadia puntuable, Categoria categoria, int puntos) {
		if(puntuable.puedeRecibirPuntuacionPorEstadiaPor(this)) {
			puntuable.recibirPuntuacionPorEstadia(categoria, puntos);
		}
	}

	@Override
	public boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario inquilino) {
		return inquilino.getAdmin().leAlquiloA(this);
	}

	@Override
	public void recibirPuntuacionPorEstadia(Categoria categoria, int puntos) {
		this.perfilPropietario.recibirPuntuacion(categoria, puntos);
	}

	public void recibirPuntuacionComoInquilino(Categoria cat, int puntos) {
		this.perfilInquilino.recibirPuntuacion(cat, puntos);
	}

	public boolean puedeRecibirPuntuacionComoInquilinoPor(Usuario propietario) {
		return this.admin.leAlquiloA(propietario);
	}

	public void actualizarPrecioAInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		if (this.getInmuebles().contains(inmueble)) {
			
			inmueble.cambiarPrecio();
			
		}
	}


	public void puntuarComoDueño(Usuario inquilino, Categoria cat, int puntos) {
		if(inquilino.puedeRecibirPuntuacionComoInquilinoPor(this)) {
			inquilino.recibirPuntuacionComoInquilino(cat, puntos);
		}
	}
	
	
	
}


