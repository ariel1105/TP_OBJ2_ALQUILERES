package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class SinAbono extends Abono {
	/**
	 * define el valor de la cancelacion de una reserva cuando la diferencia de dias entre la fecha actual y la del primer
	 * dia de la reserva son de mas de 20 dias
	 */
	
	@Override
	public boolean esAbonoParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual) {
		return cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 19, fechaActual);
	}
	/**
	 * define si esta clase es la indicada para una reserva en una fecha determinada
	 */
	

	@Override
	public double monto(Reserva reserva) {
		return 0d;
	}

	/**
	 * retorna el valor que se deberia de abonar en caso de cancelarse la reserva
	 */

}
