package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class AbonoDel50PorCiento extends Abono {

	@Override
	public boolean esAbonoParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual) {
		return (!cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 19, fechaActual))
			&& cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 9, fechaActual);
	}

	@Override
	public double monto(Reserva reserva) {
		return reserva.valor()/2;
	}

}
