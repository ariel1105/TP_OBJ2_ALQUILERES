package reservas;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import inmueble.DatosDePago;
import inmueble.Inmueble;
import politicasDeCancelacion.PoliticaDeCancelacion;
import sitio.Sitio;
import usuario.Usuario;

public class Reserva {

	private Inmueble inmueble;
	private LocalDate diaInicio;
	private LocalDate diaFin;
	private DatosDePago datosDePago;
	private Usuario inquilino;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private List<Reserva> colaDeReservas;
	private Estado estado;
	
	public Reserva(Usuario in, Inmueble i, LocalDate diaInicio, LocalDate diaFin, DatosDePago f, PoliticaDeCancelacion p) {
		this.inquilino = in;
		this.inmueble = i;
		this.diaInicio = diaInicio;
		this.diaFin = diaFin;
		this.datosDePago = f;
		this.politicaDeCancelacion = p;
		this.estado = new PendienteDeConfirmacion();
		this.colaDeReservas = new ArrayList<Reserva>();
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}	
	
	public void confirmarseEn(Sitio sitio) {
		this.estado.confirmarEn(this, sitio);
	}
	
	public Usuario getInquilino() {
		return this.inquilino;
	}

	public LocalDate getDiaInicio() {
		return this.diaInicio;
	}

	public LocalDate getDiaFin() {
		return this.diaFin;
	}
	
	public Boolean ocupaFecha(LocalDate dia) {
		return this.estado.esfechaOcupada(this, dia);
	}
	
	public Boolean ocupaAlgunaFechaDeRango(LocalDate fechaInicio, LocalDate fechaFin) {
		return this.estado.ocupaFechaDeRango(this, fechaInicio, fechaFin);
	}


	public void iniciarCancelacion(LocalDate fechaActual) {
		this.politicaDeCancelacion.cancelar(this, fechaActual);
		inmueble.cancelarReserva(this);
	}
	
	public void cancelar(LocalDate fechaACtual) {
		this.estado.cancelarReserva(this, fechaACtual);
	}


	public Double valor() {
		return this.inmueble.valorPorRangoDeFechas(diaInicio, diaFin);
	}


	public boolean esDeCiudad(String ciudad) {
		return (this.inmueble.getCiudad().equals(ciudad));
	}

	public String ciudad() {
		return this.inmueble.getCiudad();
	}

	public PoliticaDeCancelacion getPolitica() {
		return this.politicaDeCancelacion;
	}


	public Inmueble getInmueble() {
		return this.inmueble;
	}

	public DatosDePago getDatosDePago() {
		return this.datosDePago;
	}


	public boolean esReservaFutura(LocalDate fechaActual) {
		return fechaActual.isBefore(this.diaInicio);
	}
	
	public Estado getEstado() {
		return this.estado;
	}

	public boolean estaConfirmada() {
		return this.estado.estaConfirmada();
	}
	
	public void encolarReserva(Reserva reserva) {
		this.colaDeReservas.add(reserva);
	}
	
	public boolean imposibilitaReserva(Reserva reserva) {
		return this.ocupaAlgunaFechaDeRango(reserva.getDiaInicio(), reserva.getDiaFin());
	}
	
	public List<Reserva>getCola(){
		return this.colaDeReservas;
	}

	public Reserva primeraDeLaCola() {
		return this.colaDeReservas.get(0);
	}

	public boolean tieneColaDeReservas() {
		return !this.colaDeReservas.isEmpty();
	}

	public void sacarPrimeroDeLaCola() {
		if(this.tieneColaDeReservas()) {
			this.colaDeReservas.remove(0);
		}
	}

	public void recibirCola(Reserva reserva) {
		reserva.sacarPrimeroDeLaCola();
		List<Reserva>nuevaCola = reserva.getCola();
		this.colaDeReservas.addAll(nuevaCola);
	}
	

}
