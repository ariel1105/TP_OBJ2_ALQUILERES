package reservas;

import java.time.LocalDate;

import sitio.Sitio;

public class PendienteDeConfirmacion extends Estado {

	@Override
	protected void confirmarEn(Reserva reserva, Sitio sitio) {
		sitio.enviarMailDeConfirmacion(reserva);
		reserva.setEstado(new Confirmado());
	}

	@Override
	protected void cancelarReserva(Reserva reserva, LocalDate fechaACtual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Boolean esfechaOcupada(Reserva reserva, LocalDate dia) {
		return false;
	}

	@Override
	protected boolean estaConfirmada() {
		return false;
	}

}
	