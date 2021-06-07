package reservas;

import java.util.ArrayList;

public class Reserva {

	private Inmueble inmueble;
	private ArrayList<Dia> dias;
	private String formaDePago;
	private Estado estado;
	private Usuario inquilino;
	
	public Reserva(Usuario in, Inmueble i, ArrayList<Dia> d, String f) {
		this.inmueble = i;
		this.dias = d;
		this.formaDePago = f;
		this.inquilino = in;
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

	public Boolean ocupaDia(Dia dia) {
		return (this.estado.diaOcupadoEn(dia, this));
		
	}

	public ArrayList<Dia> getDias() {
		return this.dias;
	}

	public void cancelar() {
		this.estado = new Cancelada();
	}

}
