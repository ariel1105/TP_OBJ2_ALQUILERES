package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public abstract class PoliticaDeCancelacion {
	
	private LocalDate fechaActual;

	public abstract void cancelar(Reserva reserva);

	public LocalDate getFechaActual() {
		return this.fechaActual;
	}
	
	public void actualizarFecha(LocalDate dia) {
		this.fechaActual = dia;
	}
	
	

}
