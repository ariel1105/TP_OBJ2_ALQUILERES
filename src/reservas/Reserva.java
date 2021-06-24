package reservas;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

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
			resultado = resultado || this.fechas.contains(fechas.get(i));
		}
		return resultado;
	}


	public boolean esReservaQueImposibilita(Reserva reserva) {
		// TODO Auto-generated method stub
		return (this.getInmueble() == reserva.getInmueble() && 
				this.algunaDeLasFechasEstaOcupada(reserva.getFechas()));
	}


	public Object ultimoDia() {
		// TODO Auto-generated method stub
		return null;
	}


}
