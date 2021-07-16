package reservas;

import java.time.LocalDate;

import sitio.Sitio;

public class Confirmado extends Estado {
	/**
	 * clase que representa el estado de una reserva cuando esta confirmada
	 */

	@Override
	protected void cancelarReserva(Reserva reserva, LocalDate fechaACtual) {
		reserva.iniciarCancelacion(fechaACtual);
		reserva.setEstado(new Cancelado());
	}
	/**
	 * inicia la cancelacion de la reserva y cambia el estado de la misma
	 */

	@Override
	protected boolean esfechaOcupada(Reserva reserva, LocalDate dia) {
		LocalDate inicio = reserva.getDiaInicio();
		LocalDate fin = reserva.getDiaFin();
		return inicio.datesUntil(fin).anyMatch(l -> l.equals(dia))||fin.equals(dia);
	}
	/**
	 * dada una fecha LocalDate determina si existe dentro del rango de la reserva
	 */
	
	@Override
	public boolean estaConfirmada() {
		return true;
	}
	/**
	 * al ser el estado Confirmado siempre va a devolver true
	 */

	@Override
	protected boolean ocupaFechaDeRango(Reserva reserva, LocalDate fechaInicio, LocalDate fechaFin) {
		return fechaInicio.datesUntil(fechaFin).anyMatch(f -> this.esfechaOcupada(reserva, f))
			|| this.esfechaOcupada(reserva, fechaFin);
	}
	/**
	 * dada una reserva determina si ocupa alguna fecha en un rango
	 */

}
