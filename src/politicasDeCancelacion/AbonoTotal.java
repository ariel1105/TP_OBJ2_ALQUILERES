package politicasDeCancelacion;

import reservas.Reserva;

public class AbonoTotal extends Accion {

	@Override
	public boolean esAccionParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva) {
		return (!cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 9));
	}

	// error al agregar la annotation override
	public void realizarAccionDePago(Reserva reserva) {
		reserva.confirmarPagoPor(reserva.valor());
	}

}
