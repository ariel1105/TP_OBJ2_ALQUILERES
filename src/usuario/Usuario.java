package usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
	private ArrayList<Inmueble> inmuebles;
	private ArrayList<Reserva>reservasPendientesDeConfirmacion;
	private ArrayList<Reserva>reservasConfirmadasPropietario;
	private LocalDate fechaActual;
	private PerfilInquilino perfilInquilino;
	private PerfilPropietario perfilPropietario;

	private HashMap<Reserva, ArrayList<Reserva>> reservasConfirmadasYEncoladas;
	private LocalDate fechaQueSeRegistro;
	private AppUser aplicacionMovil;
	
	public Usuario(String nombreCompleto, String mail, String telefono, AdministadorDeReservasInquilino admin, AppUser aplicacion){
	
		this.nombreCompleto = nombreCompleto;
		this.mail = mail;
		this.telefono = telefono;
		this.inmuebles = new ArrayList<Inmueble>();
		this.reservasPendientesDeConfirmacion = new ArrayList<Reserva>();
		this.reservasConfirmadasPropietario = new ArrayList<Reserva>();
		this.reservasConfirmadasYEncoladas = new HashMap<Reserva, ArrayList<Reserva>>();
		this.admin = admin;
		this.aplicacionMovil= aplicacion;
	}
	
	public ArrayList<Reserva> getReservasPendientes() {
		return this.reservasPendientesDeConfirmacion;
	}

	public ArrayList<Reserva> getReservasConfirmadas() {
		return this.reservasConfirmadasPropietario;
	}
	
	public String getTelefono() {
		return this.telefono;
	}

	public String getMail() {
		return this.mail;
	}
	
	public HashMap<Reserva, ArrayList<Reserva>> getReservasConfirmadasYEncoladas(){
		return this.reservasConfirmadasYEncoladas;
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

	public void publicar(Inmueble inmueble, Sitio sitio, ArrayList<String> servicios) { //falta agregar a inmuebles
		if (sitio.elUsuarioEstaRegistrado(this)) {
			sitio.publicar(inmueble,this, servicios);
			inmuebles.add(inmueble);
		}
	}
	
	public void solicitarReserva(Reserva reserva) {
		if (reserva.getDatosDePago().sonDatosAdmitidosPara(reserva.getInmueble())) {
			reserva.getInmueble().getPropietario().recibirSolicitudDeReserva(reserva);
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



	public long tiempoComoUser() {
		long result = ChronoUnit.DAYS.between(this.getFechaQueSeRegistro(), LocalDate.now());
		return result;
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


	public ArrayList<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	public void puntuarComoPropietario(Usuario inquilino, Categoria cat, int puntos) {
		if(inquilino.puedeRecibirPuntuacionComoInquilinoPor(this)) {
			inquilino.recibirPuntuacionComoInquilino(cat, puntos);
		}
	}


	public Reserva obtenerReservaQueImposibilitaReserva(Reserva reserva, ArrayList<Reserva> reservas) {
		// TODO Auto-generated method stub
		Reserva reservaEsperada = null;
		int i = 0;
		while(i < reservas.size()) {
			if (reservas.get(i).esReservaQueImposibilita(reserva)){
				reserva = reservas.get(i);
			}
			i++;
		}
		return reserva;
	}

	public ArrayList<Reserva> obtenerReservasEncoladas(Reserva reserva) {
		// TODO Auto-generated method stub
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		reservas.addAll(reservasConfirmadasYEncoladas.keySet());
		
		Reserva reservaEnLaQueSeEncolara = 
			this.obtenerReservaQueImposibilitaReserva(reserva, reservas);

			return reservasConfirmadasYEncoladas.get(reservaEnLaQueSeEncolara);
	}

	public void encolarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.obtenerReservasEncoladas(reserva).add(reserva);
	}

	public void iniciarTramiteDeReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		if(reserva.getInmueble().estaDisponible(reserva.getFechas())){
			this.solicitarReserva(reserva);
		}
		else {
			this.encolarReserva(reserva);
		}	
	}
	
	public void iniciarTramiteParaElPrimeroDeLaFila(Reserva reserva) {
		
		Reserva reservaATramitar = reservasConfirmadasYEncoladas.get(reserva).get(0);
		ArrayList <Reserva> reservasAActualizar = this.reservasConfirmadasYEncoladas.get(reserva);
		reservasAActualizar.remove(0);
		
		iniciarTramiteDeReserva(reservaATramitar);
		actualizarReservasEncoladas(reservasAActualizar);
	}
	
	public void actualizarReservasEncoladas(ArrayList<Reserva> reservas) {
		
		for(int i=0; i<reservas.size(); i++) {
			Usuario inquilino = reservas.get(i).getInquilino();
			inquilino.iniciarTramiteDeReserva(reservas.get(i));
		}
	}
	
	
	
}


