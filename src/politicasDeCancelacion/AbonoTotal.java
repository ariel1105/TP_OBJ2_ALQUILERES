package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class AbonoTotal extends Accion {

	@Override
	public boolean esAccionParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual) {
		return (!cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 9, fechaActual));
	}

	// error al agregar la annotation override
	public void realizarAccionDePago(Reserva reserva) {
		reserva.confirmarPagoPor(reserva.valor());
	}

}
