package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class AbonoTotal extends Abono {
	/**
	 * clase que determina el valor que se debe abonar por una reserva si la diferenca de dias es menor a 10 entre
	 * la fecha actual y el primer dia de la reserva
	 */

	@Override
	public boolean esAbonoParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual) {
		return (!cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 9, fechaActual));
	}
	/**
	 * define si esta clase es la indicada para una reserva en una fecha determinada
	 */

	@Override
	public double monto(Reserva reserva) {
		return reserva.valor();
	}
	/**
	 * retorna el valor que se deberia de abonar en caso de cancelarse la reserva
	 */

}
