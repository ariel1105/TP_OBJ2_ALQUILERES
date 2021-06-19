package reservas;

import java.time.LocalDate;
import java.util.ArrayList;

import inmueble.DatosDePago;
import inmueble.FormaDePago;
import inmueble.Inmueble;
import politicasDeCancelacion.PoliticaDeCancelacion;
import sitio.Sitio;
import usuario.Usuario;

public class Reserva {

	private Inmueble inmueble;
	private ArrayList<LocalDate> fechas;
	private DatosDePago datosDePago;
	private Estado estado;
	private Usuario inquilino;
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	
	public Reserva(Usuario in, Inmueble i, ArrayList<LocalDate> dias, DatosDePago f, PoliticaDeCancelacion p) {
		this.inquilino = in;
		this.inmueble = i;
		this.fechas = dias;
		this.datosDePago = f;
		this.politicaDeCancelacion = p;
		this.estado = new PendienteDeConfirmacion();
	}

	
	public void confirmarseEn(Sitio sitio) {
		sitio.enviarMailDeConfirmacion(this);
		this.estado = new Confirmada();
	}
	
	public Usuario getInquilino() {
		return this.inquilino;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public Boolean ocupaFecha(LocalDate dia) {
		return (this.estado.fechaOcupadaEn(dia, this));
		
	}

	public ArrayList<LocalDate> getFechas() {
		return this.fechas;
	}

	public void IniciarCancelacion(LocalDate fechaActual) {
		this.politicaDeCancelacion.actualizarFecha(fechaActual);
		this.politicaDeCancelacion.cancelar(this);
	}
	
	public void cancelar() {
		this.estado = new Cancelada();
	}


	public Double valor() {
		return this.inmueble.valorPorDias(this.fechas);
	}

	public LocalDate primerDia() {
		return this.fechas.get(0);
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
		this.datosDePago.abonar(this.inmueble.getDueño(),monto);
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

}
