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
	
	/**
     * Clase del inmueble, de aqui podremos saber todos sus atributos, agregar reservas, saber si esta reservado en algun periodo, las veces que fue alquilado
     * encolar y desencolar reservas, agregar observadores, saber el precio de un rango de fechas, entre otras.
     * Ademas su comportamiento sera observado por los listeners
     */

	private Usuario propietario;
	private String tipoDeInmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private List<String> servicios;
	private int capacidad;
	private List<IFoto> fotos;
	private IHora horarioCheckIn;
	private IHora horarioCheckOut;
	private List<FormaDePago> formasDePago;
	private List<PeriodoPrecio> periodosConPrecios;
	private double precioPorDefecto;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private PerfilInmueble perfil;
	private double precioActual;	
	private List <INotify> listenersPaginas;
	private List <Reserva> reservas;
	
	public Inmueble(Usuario propietario,  String tipoDeInmueble, double superficie, String pais, String ciudad, String direccion, List<String> servicios,
			 int capacidad, List<IFoto> fotos, IHora horarioCheckIn,
			IHora horarioCheckOut, List<FormaDePago> formasDePago, double precio, 
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
	/**
     * Constructor de la clase
     * Se setearan los valores que se le pasaron como parametros y se instanciaran las listas como arrays
     */

	public List<INotify> getListenersPaginas() {
		return listenersPaginas;
	}
	/**
     * Metodo que retorna los listeners del inmueble
     */
	public String getTipoDeInmueble() {
		return tipoDeInmueble;
	}
	/**
     * Metodo que retorna el tipo de inmueble
     */
	public double getPrecioPorDefecto() {
		return this.precioPorDefecto;
	}
	/**
     * Metodo que retorna el precio predeterminado
     */
	public boolean perteneceLaFechaAAlgunPeriodo(LocalDate fecha) {
		return this.periodosConPrecios.stream().anyMatch(p -> p.perteneceLaFecha(fecha));
	}
	/**
     * Metodo que verifica si una fecha dada como parametro pertenece a algun periodo del inmueble
     */

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
	/**
     * Metodo que retorna el precio dependiendo la fecha
     */

	public void establecerPeriodosConPrecios(PeriodoPrecio periodoPrecio) {
		// TODO Auto-generated method stub
		this.periodosConPrecios.add(periodoPrecio);
	}
	/**
     * Metodo que agrega un periodo al inmueble
     */
	public List<PeriodoPrecio> getPeriodosYPrecios() {
		// TODO Auto-generated method stub
		return periodosConPrecios;
	}
	/**
     * Metodo que retorna todos los periodos del inmueble
     */
	public double valorPorRangoDeFechas(LocalDate inicio, LocalDate fin) {
		Stream<LocalDate>rango = inicio.datesUntil(fin);
		Double valor = rango.map(dia -> this.obtenerElPrecioParaLaFecha(dia)).reduce(0d,(x,y) -> x+y);
		valor = valor + this.obtenerElPrecioParaLaFecha(fin);
		return valor;
		
	}
	/**
     * Metodo que retorna el precio de un rango determinado por una fecha inicio y fin
     */
	public String getCiudad() {
		return this.ciudad;
	}
	/**
     * Metodo que retorna la ciudad
     */
	public void agregarReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	/**
     * Metodo que agrega una reserva a la lista de reservas
     */
	public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin) {
		return !(this.reservas.stream()
							 .anyMatch(r -> r.ocupaAlgunaFechaDeRango(fechaInicio, fechaFin)));
	}
	/**
     * Metodo que verifica si el inmueble esta disponible en un rango de dos fechas dadas como parametro
     */
	
	public int getCapacidad() {
		return this.capacidad;
	}
	/**
     * Metodo que retorna la capacidad de huespedes que entran en el inmueble
     */
	public Usuario getPropietario() {
		return this.propietario;
	}
	/**
     * Metodo que retorna el dueño de la propiedad
     */
	public List<FormaDePago> getFormasDePago() {
		return this.formasDePago;
	}
	/**
     * Metodo que retorna las formas de pago
     */
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}
	/**
     * Metodo que retorna la politica de cancelacion
     */
	public boolean estaReservado() {
		return this.reservas.stream()
							.anyMatch(r -> r.estaConfirmada());
	}
	/**
     * Metodo que retorna si tiene al menos una reserva
     */
	public void addObserver(INotify notif) {
		// TODO Auto-generated method stub
		this.listenersPaginas.add(notif);
	}
	/**
     * Metodo que agrega un observador a la lista de listeners
     */
	public void cancelarReserva(Reserva reserva) {
		this.desencolarReserva(reserva);
		this.notificar("Cancelacion");
	}
	/**
     * Metodo que cancela una reserva dada como parametro y notifica a los listeners interesados
     */


	public void cambiarPrecio() {
		// TODO Auto-generated method stub
		
		Double precioAnterior= this.getPrecioActual();
			
		precioActual= this.obtenerElPrecioParaLaFecha(LocalDate.now());
		
		if (precioActual < precioAnterior) {
		
		this.notificar("Baja de precio");
		}
		
	} 
	/**
     * Metodo que cambia el precio al actual. En caso de que el actual sea menor que el anterior, esta notifica a los listeners interesados
     */
	public void notificar(String evento) {
		// TODO Auto-generated method stub
		this.listenersPaginas.stream().
		forEach(listener -> listener.update(this, evento));

	}
	/**
     * Metodo que notifica un evento dado como parametro a los listeners
     */

	public Double getPrecioActual() {
		// TODO Auto-generated method stub
		return precioActual;
	}
	/**
     * Metodo que retorna el precio actual que posee el inmueble
     */

	public void setPerfilInmueble(PerfilInmueble perfil) {
		this.perfil = perfil;
	}
	/**
     * Metodo que setea el perfil del inmueble dado como parametro
     */
	@Override
	public boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario usuario, LocalDate fechaActual) {
		return usuario.getAdmin().alquilo(this, fechaActual);
	}
	/**
	 * se delega al administradorDeReservasInquilino del usuario la tarea de determinar si se puede recibir puntuacion
	 * por el usuario que se pasa como parametro
	 */

	@Override
	public void recibirPuntuacionPorEstadia(Categoria categoria, int puntos) {
		this.perfil.recibirPuntuacion(categoria, puntos);
	}
	/**
	 * se delega al perfil la tardea de recibir puntuacion
	 */
	

	public List<String> getServicios() {
		return servicios;
	}
	/**
	 * retorna los servicios de los cuales dispone 
	 */

	public boolean tieneReserva(Reserva reserva) {
		return this.reservas.contains(reserva)&& reserva.estaConfirmada();
	}
	/**
	 * retorna true si se existe la reserva y esta confirmada
	 */

	public List<Reserva> getReservas() {
		return this.reservas;
	}
	/**
	 * retorna la lista de reservas
	 */

	public int vecesQueFueAlquilado() {
		return this.reservas.stream()
							.filter(r -> r.estaConfirmada())
							.collect(Collectors.toList())
							.size();
	}
	/**
	 * retorna la cantidad de veces que fue alquilado mediante un filtro que descarta las reservas que no estan
	 * confirmadas
	 */
	
	public List<Reserva> reservasQueImposibilitan(Reserva reserva){
		return this.reservas.stream()
							.filter(r -> r.imposibilitaReserva(reserva))
							.collect(Collectors.toList());
	}
	/**
	 * retorna la lista de reservas que imposibilitan la reserva que se pasa por parametro
	 */

	public void encolar(Reserva reserva) {
		this.reservasQueImposibilitan(reserva).stream()
											  .forEach(r -> r.encolarReserva(reserva));
	}
	/**
	 * encola la reserva pasada por paramentro a aquellas reservas que la imposibilitan
	 */

	public void desencolarReserva(Reserva reserva) {
		if (this.sePuedeRealizarSolicitudPorReservaEncoladaEn(reserva)) {
			this.realizarSolicitudParaLaPrimeraDeLaCola(reserva);
		}
		else {reserva.sacarPrimeroDeLaCola();}			
	}
	/**
	 * dada una reserva pasada por parametro se desencola descartandola y se solicita su reserva en caso de cumplir con la 
	 * condicion, caso contrario, se elimina la primer reserva en la cola de la reserva pasada por parametro
	 */

	public void realizarSolicitudParaLaPrimeraDeLaCola(Reserva reserva) {
		Reserva nuevaReserva = reserva.primeraDeLaCola();
		Usuario interesado = nuevaReserva.getInquilino();
		nuevaReserva.recibirCola(reserva);
		interesado.solicitarReserva(nuevaReserva, this);
	}
	
	/**
	 * se descarta la reserva pasada por parametro no sin antes pasarle la cola sin el primero al primero en la cola,
	 * luego se solicita la reserva por esta
	 */

	public boolean noHayReservasQueImposibiliten(Reserva primeraDeLaCola) {
		return this.reservas.stream().noneMatch(r -> r.imposibilitaReserva(primeraDeLaCola));
	}
	/**
	 * retorna true sino existe reserva que imposibilite la reserva pasada por parametro
	 */
	
	public boolean sePuedeRealizarSolicitudPorReservaEncoladaEn(Reserva reserva) {
		return this.noHayReservasQueImposibiliten(reserva.primeraDeLaCola()) 
				&& reserva.tieneColaDeReservas()
				&& this.tieneReserva(reserva);
	}
	
}
