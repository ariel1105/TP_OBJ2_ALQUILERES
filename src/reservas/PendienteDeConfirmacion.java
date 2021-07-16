package reservas;

import java.time.LocalDate;

import sitio.Sitio;

public class PendienteDeConfirmacion extends Estado {
	/**
	 * representa el estado de una reserva pendiente de confirmacion
	 */

	@Override
	protected void confirmarEn(Reserva reserva, Sitio sitio) {
		sitio.enviarMailDeConfirmacion(reserva);
		reserva.setEstado(new Confirmado());
	}
	
	/**
	 * cambia el estado de la reserva y dispara el mail de confirmacion
	 */

}
	