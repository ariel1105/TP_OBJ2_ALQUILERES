package inmueble;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Suscripciones.INotify;
import Suscripciones.SitioWeb;
import periodo.PeriodoPrecio;
import politicasDeCancelacion.PoliticaDeCancelacion;
import usuario.Usuario;

public class Inmueble {
	private Usuario dueño;
	private String tipoDeInmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private ArrayList<String> servicios;
	private int capacidad;
	private ArrayList<Foto> fotos;
	private Hora horarioCheckIn;
	private Hora horarioCheckOut;
	private ArrayList<FormaDePago> formasDePago;
	private ArrayList<PeriodoPrecio> periodosConPrecios;
	private double precioPorDefecto;
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	public List <INotify> listenersPaginas;
	
	public Inmueble(Usuario dueño, String tipoDeInmueble, double superficie, String pais, String ciudad, String direccion,
			ArrayList<String> servicios, int capacidad, ArrayList<Foto> fotos, Hora horarioCheckIn,
			Hora horarioCheckOut, ArrayList<FormaDePago> formasDePago, double precio, PoliticaDeCancelacion politicaDeCancelacion) {
		// TODO Auto-generated constructor stub
		this.dueño = dueño;
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
	}

	public List<INotify> getListenersPaginas() {
		return listenersPaginas;
	}

	public String getTipoDeInmueble() {
		return tipoDeInmueble;
	}

	public double precioParaLaFecha(LocalDate fecha) {
		// TODO Auto-generated method stub
		if (!this.perteneceLaFechaAAlgunPeriodo(fecha)) {
			return this.getPrecioPorDefecto();
		}
		else {
			return this.obtenerElPrecioParaLaFecha(fecha);
		}
	}
	
	public double getPrecioPorDefecto() {
		return this.precioPorDefecto;
	}

	public boolean perteneceLaFechaAAlgunPeriodo(LocalDate fecha) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		for(int i = 0; i < periodosConPrecios.size(); i++) {
			resultado = resultado ||  periodosConPrecios.get(i).perteneceLaFecha(fecha);
		}
		return resultado;
		
	}


	public double obtenerElPrecioParaLaFecha(LocalDate fecha) {
		int i = 0;
		while (i < this.periodosConPrecios.size() && !(this.periodosConPrecios.get(i)).perteneceLaFecha(fecha)) {
			i++;
		}
		return this.periodosConPrecios.get(i).getPrecio();
	}
	

	public void establecerPeriodosConPrecios(PeriodoPrecio periodoPrecio) {
		// TODO Auto-generated method stub
		this.periodosConPrecios.add(periodoPrecio);
	}

	public ArrayList<PeriodoPrecio> getPeriodosYPrecios() {
		// TODO Auto-generated method stub
		return periodosConPrecios;
	}

	public double valorPorDias(ArrayList<LocalDate> fechas) {
		// TODO Auto-generated method stub
		double precio = 0;
		for(int i = 0; i < fechas.size(); i++){
			precio = precio + this.precioParaLaFecha(fechas.get(i));
		}
		return precio;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public Boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin) {
		return true;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public Usuario getDueño() {
		return this.dueño;
	}

	public ArrayList<FormaDePago> getFormasDePago() {
		return this.formasDePago;
	}

	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return this.politicaDeCancelacion;
	}

	public boolean estaReservado() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addObserver(INotify notif) {
		// TODO Auto-generated method stub
		this.listenersPaginas.add(notif);
	}

	public void cancelarReserva() {
		// TODO Auto-generated method stub
		this.notificarCancelado();
	}

	public void notificarCancelado() {
		// TODO Auto-generated method stub
		for (INotify listener : this.getListenersPaginas()) {
		listener.popUp("El/la "+this.getTipoDeInmueble()+ " que te interesa se ha liberado! Corre a reservarlo!", "Rojo", 3);
	}
	}

	public Double precioParaRango(LocalDate fechaInicio, LocalDate fechaFin) {
		return 0d;
	}

	

	public void cambiarPrecio() {
		// TODO Auto-generated method stub
		this.notificarBajaDePrecio();
		
		
	} 

	public void notificarBajaDePrecio() {
		// TODO Auto-generated method stub
		for (INotify listener : this.getListenersPaginas()) {
		listener.publish("No te pierdas esta oferta: Un inmueble " + this.getTipoDeInmueble()+ " a tan solo " + this.getPrecioActual() + " pesos!");
		
		}
		
	}

	private Double getPrecioActual() {
		// TODO Auto-generated method stub
		return 50000.0;
	}
}
