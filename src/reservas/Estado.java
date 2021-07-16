package reservas;

import java.time.LocalDate;

import sitio.Sitio;

public abstract class Estado {
	/**
	 * clase abstracta que representa el estado en que se encuentra una reserva
	 * define metodos por defecto que de ser necesario las subclases van a sobrescribir
	 * 
	 */
	

	protected void confirmarEn(Reserva reserva, Sitio sitio) {
		
	}

	protected void cancelarReserva(Reserva reserva, LocalDate fechaACtual) {
		
	}

	protected boolean esfechaOcupada(Reserva reserva ,LocalDate dia) {
		return false;
	}

	protected boolean estaConfirmada() {
		return false;
	}

	protected boolean ocupaFechaDeRango(Reserva reserva, LocalDate fechaInicio, LocalDate fechaFin) {
		return false;
	}

}
