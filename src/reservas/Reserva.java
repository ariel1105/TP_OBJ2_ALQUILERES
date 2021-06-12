package reservas;

import java.time.LocalDate;
import java.util.ArrayList;

import politicasDeCancelacion.PoliticaDeCancelacion;

public class Reserva {

	private Inmueble inmueble;
	private ArrayList<LocalDate> fechas;
	private String formaDePago;
	private Estado estado;
	private Usuario inquilino;
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	
	public Reserva(Usuario in, Inmueble i, ArrayList<LocalDate> dias, String f, PoliticaDeCancelacion p) {
		this.inquilino = in;
		this.inmueble = i;
		this.fechas = dias;
		this.formaDePago = f;
		this.politicaDeCancelacion = p;
		this.estado = new PendienteDeConfirmacion();
	}
 
	public void ingresarEnSitio(Sitio sitio) {
		sitio.agegarReserva(this);
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

}
