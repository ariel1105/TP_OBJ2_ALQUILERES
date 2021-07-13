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
	private DatosDePago datosDePago;
	private Usuario inquilino;
	private PoliticaDeCancelacion politicaDeCancelacion;
	
	
	public Reserva(Usuario in, Inmueble i, ArrayList<LocalDate> dias, DatosDePago f, PoliticaDeCancelacion p) {
		this.inquilino = in;
		this.inmueble = i;
		this.fechas = dias;
		this.datosDePago = f;
		this.politicaDeCancelacion = p;
	}

	
	public void confirmarseEn(Sitio sitio) {
		sitio.enviarMailDeConfirmacion(this);
	}
	
	public Usuario getInquilino() {
		return this.inquilino;
	}

	

	public Boolean ocupaFecha(LocalDate dia) {
		return (this.fechas.contains(dia));
		
	}



	public ArrayList<LocalDate> getFechas() {
		return this.fechas;
	}

	public void iniciarCancelacion(LocalDate fechaActual) {
		this.politicaDeCancelacion.actualizarFecha(fechaActual);
		this.politicaDeCancelacion.cancelar(this);
		inmueble.cancelarReserva();
	}
	
	public void cancelar() {
		this.getInmueble().getPropietario().eliminarReserva(this);
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


	public boolean esReservaQueImposibilita(Reserva reserva) {
		// TODO Auto-generated method stub
		return (this.getInmueble() == reserva.getInmueble() && 
				this.algunaDeLasFechasEstaOcupada(reserva.getFechas()));
	}


}
