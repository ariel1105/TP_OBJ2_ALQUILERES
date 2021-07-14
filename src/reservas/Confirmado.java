package reservas;

import java.time.LocalDate;

import sitio.Sitio;

public class Confirmado extends Estado {

	@Override
	protected void confirmarEn(Reserva reserva, Sitio sitio) {
		
	}

	@Override
	protected void cancelarReserva(Reserva reserva, LocalDate fechaACtual) {
		reserva.iniciarCancelacion(fechaACtual);
		reserva.setEstado(new Cancelado());
	}

	@Override
	protected boolean esfechaOcupada(Reserva reserva, LocalDate dia) {
		LocalDate inicio = reserva.getDiaInicio();
		LocalDate fin = reserva.getDiaFin();
		return inicio.datesUntil(fin).anyMatch(l -> l.equals(dia))||fin.equals(dia);
	}

	@Override
	public boolean estaConfirmada() {
		return true;
	}

	@Override
	protected boolean ocupaFechaDeRango(Reserva reserva, LocalDate fechaInicio, LocalDate fechaFin) {
		return fechaInicio.datesUntil(fechaFin).anyMatch(f -> reserva.ocupaFecha(f)) || reserva.ocupaFecha(fechaFin);
	}

}
