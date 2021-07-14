package reservas;

import java.time.LocalDate;

import sitio.Sitio;

public abstract class Estado {
	

	protected abstract void confirmarEn(Reserva reserva, Sitio sitio);

	protected abstract void cancelarReserva(Reserva reserva, LocalDate fechaACtual);

	protected abstract Boolean esfechaOcupada(Reserva reserva ,LocalDate dia);

	protected abstract boolean estaConfirmada();

}
