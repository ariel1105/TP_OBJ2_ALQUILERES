package reservas;

import java.time.LocalDate;

import sitio.Sitio;

public abstract class Estado {
	

	protected abstract void confirmarEn(Reserva reserva, Sitio sitio);

	protected abstract void cancelarReserva(Reserva reserva, LocalDate fechaACtual);

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
