package inmueble;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Categorias.Categoria;
import Suscripciones.INotify;
import Suscripciones.SitioWeb;
import perfiles.PerfilInmueble;
import periodo.PeriodoPrecio;
import politicasDeCancelacion.PoliticaDeCancelacion;
import reservas.Reserva;
import usuario.PuntuablePorEstadia;
import usuario.Usuario;

public class Inmueble  implements PuntuablePorEstadia {

	private Usuario propietario;
	private String tipoDeInmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private List<String> servicios;
	private int capacidad;
	private List<Foto> fotos;
	private Hora horarioCheckIn;
	private Hora horarioCheckOut;
	private List<FormaDePago> formasDePago;
	private List<PeriodoPrecio> periodosConPrecios;
	private double precioPorDefecto;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private PerfilInmueble perfil;
	private double precioActual;	
	private List <INotify> listenersPaginas;
	private List <Reserva> reservas;
	
	public Inmueble(Usuario propietario,  String tipoDeInmueble, double superficie, String pais, String ciudad, String direccion, List<String> servicios,
			 int capacidad, List<Foto> fotos, Hora horarioCheckIn,
			Hora horarioCheckOut, List<FormaDePago> formasDePago, double precio, 
			PoliticaDeCancelacion politicaDeCancelacion) {
		this.propietario = propietario;
		this.tipoDeInmueble = tipoDeInmueble;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = servicios;
		this.capacidad = capacidad;
		this.fotos = fotos;
		this.horarioCheckIn = horarioCheckIn;
		this.horarioCheckOut = horarioCheckOut;
		this.formasDePago = formasDePago;
		this.periodosConPrecios = new ArrayList<PeriodoPrecio>();
		this.precioPorDefecto = precio;
		this.politicaDeCancelacion = politicaDeCancelacion;
		this.listenersPaginas= new ArrayList<INotify>();
		this.precioActual= precio;
		this.reservas = new ArrayList<Reserva>();
	}

	public List<INotify> getListenersPaginas() {
		return listenersPaginas;
	}

	public String getTipoDeInmueble() {
		return tipoDeInmueble;
	}
	
	public double getPrecioPorDefecto() {
		return this.precioPorDefecto;
	}

	public boolean perteneceLaFechaAAlgunPeriodo(LocalDate fecha) {
		return this.periodosConPrecios.stream().anyMatch(p -> p.perteneceLaFecha(fecha));
	}


	public double obtenerElPrecioParaLaFecha(LocalDate fecha) {
		Double precio;
		if (!this.perteneceLaFechaAAlgunPeriodo(fecha)) {
			precio = this.precioPorDefecto;
		}	
		else {
			Optional<PeriodoPrecio> periodo = this.periodosConPrecios.stream().filter( p -> p.perteneceLaFecha(fecha)).findFirst();
			precio = periodo.get().getPrecio();
		}
		return precio;
	}
	

	public void establecerPeriodosConPrecios(PeriodoPrecio periodoPrecio) {
		// TODO Auto-generated method stub
		this.periodosConPrecios.add(periodoPrecio);
	}

	public List<PeriodoPrecio> getPeriodosYPrecios() {
		// TODO Auto-generated method stub
		return periodosConPrecios;
	}

	public double valorPorRangoDeFechas(LocalDate inicio, LocalDate fin) {
		Stream<LocalDate>rango = inicio.datesUntil(fin);
		Double valor = rango.map(dia -> this.obtenerElPrecioParaLaFecha(dia)).reduce(0d,(x,y) -> x+y);
		valor = valor + this.obtenerElPrecioParaLaFecha(fin);
		return valor;
		
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void agregarReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
	public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin) {
		return !(this.reservas.stream()
							 .anyMatch(r -> r.ocupaAlgunaFechaDeRango(fechaInicio, fechaFin)));
	}
	
	
	public int getCapacidad() {
		return this.capacidad;
	}

	public Usuario getPropietario() {
		return this.propietario;
	}

	public List<FormaDePago> getFormasDePago() {
		return this.formasDePago;
	}

	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	public boolean estaReservado() {
		return this.reservas.stream()
							.anyMatch(r -> r.estaConfirmada());
	}

	public void addObserver(INotify notif) {
		// TODO Auto-generated method stub
		this.listenersPaginas.add(notif);
	}

	public void cancelarReserva(Reserva reserva) {
		this.desencolarReserva(reserva);
		this.notificar("Cancelacion");
	}



	public void cambiarPrecio() {
		// TODO Auto-generated method stub
		
		Double precioAnterior= this.getPrecioActual();
			
		precioActual= this.obtenerElPrecioParaLaFecha(LocalDate.now());
		
		if (precioActual < precioAnterior) {
		
		this.notificar("Baja de precio");
		}
		
	} 

	public void notificar(String evento) {
		// TODO Auto-generated method stub
		this.listenersPaginas.stream().
		forEach(listener -> listener.update(this, evento));

	}

	public Double getPrecioActual() {
		// TODO Auto-generated method stub
		return precioActual;
	}

	public void setPerfilInmueble(PerfilInmueble perfil) {
		this.perfil = perfil;
	}

	@Override
	public boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario usuario, LocalDate fechaActual) {
		return usuario.getAdmin().alquilo(this, fechaActual);
	}

	@Override
	public void recibirPuntuacionPorEstadia(Categoria categoria, int puntos) {
		this.perfil.recibirPuntuacion(categoria, puntos);
	}


	public List<String> getServicios() {
		return servicios;
	}

	

	public boolean tieneReserva(Reserva reserva) {
		return this.reservas.contains(reserva)&& reserva.estaConfirmada();
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public int vecesQueFueAlquilado() {
		return this.reservas.stream()
							.filter(r -> r.estaConfirmada())
							.collect(Collectors.toList())
							.size();
	}
	
	public List<Reserva> reservasQueImposibilitan(Reserva reserva){
		return this.reservas.stream()
							.filter(r -> r.imposibilitaReserva(reserva))
							.collect(Collectors.toList());
	}

	public void encolar(Reserva reserva) {
		this.reservasQueImposibilitan(reserva).stream()
											  .forEach(r -> r.encolarReserva(reserva));
	}

	public void desencolarReserva(Reserva reserva) {
		if (this.sePuedeRealizarSolicitudPorReservaEncoladaEn(reserva)) {
			this.realizarSolicitudParaLaPrimeraDeLaCola(reserva);
		}
		else {reserva.sacarPrimeroDeLaCola();}			
	}

	public void realizarSolicitudParaLaPrimeraDeLaCola(Reserva reserva) {
		Reserva nuevaReserva = reserva.primeraDeLaCola();
		Usuario interesado = nuevaReserva.getInquilino();
		nuevaReserva.recibirCola(reserva);
		interesado.solicitarReserva(nuevaReserva, this);
	}

	public boolean noHayReservasQueImposibiliten(Reserva primeraDeLaCola) {
		return this.reservas.stream().noneMatch(r -> r.imposibilitaReserva(primeraDeLaCola));
	}
	
	public boolean sePuedeRealizarSolicitudPorReservaEncoladaEn(Reserva reserva) {
		return this.noHayReservasQueImposibiliten(reserva.primeraDeLaCola()) 
				&& reserva.tieneColaDeReservas()
				&& this.tieneReserva(reserva);
	}
	
}
