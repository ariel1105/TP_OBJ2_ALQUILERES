package reservas;


import java.time.LocalDate;
import java.util.ArrayList;

import inmueble.DatosDePago;
import inmueble.Inmueble;
import politicasDeCancelacion.PoliticaDeCancelacion;
import sitio.Sitio;
import usuario.Usuario;

public class Reserva {

	private Inmueble inmueble;
	private ArrayList<LocalDate> fechas;
	private LocalDate diaInicio;
	private LocalDate diaFin;
	private DatosDePago datosDePago;
	private Usuario inquilino;
	private PoliticaDeCancelacion politicaDeCancelacion;
	private Estado estado;
	
	public Reserva(Usuario in, Inmueble i, LocalDate diaInicio, LocalDate diaFin, DatosDePago f, PoliticaDeCancelacion p) {
		this.inquilino = in;
		this.inmueble = i;
		this.diaInicio = diaInicio;
		this.diaFin = diaFin;
		this.datosDePago = f;
		this.politicaDeCancelacion = p;
		this.estado = new PendienteDeConfirmacion();
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


	public void iniciarCancelacion(LocalDate fechaActual) {
		this.politicaDeCancelacion.cancelar(this, fechaActual);
		inmueble.cancelarReserva();
	}
	
	public void cancelar(LocalDate fechaACtual) {
		this.estado.cancelarReserva(this, fechaACtual);
	}


	public Double valor() {
		return this.inmueble.valorPorDias(this.fechas);
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

	public void confirmarPagoPor(double monto) {
		this.datosDePago.abonar(this.inmueble.getPropietario(),monto);
	}

	public double valorPorDias(int cantDias) {
		ArrayList<LocalDate>primerosDias = new ArrayList<LocalDate>();
		for(int i = 0; i < cantDias; i++) {
			primerosDias.add(this.fechas.get(i));
		}
		return this.inmueble.valorPorDias(primerosDias);
	}

	public Inmueble getInmueble() {
		return this.inmueble;
	}

	public DatosDePago getDatosDePago() {
		return this.datosDePago;
	}


	public boolean algunaDeLasFechasEstaOcupada(ArrayList<LocalDate> fechas) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		for(int i = 0; i < fechas.size(); i++){
			resultado = resultado || this.ocupaFecha(fechas.get(i));
		}
		return resultado;
	}

}
