	package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public abstract class Abono {

	/**
	 * clase abstracta de los abonos que van a definir el valor correspondiente a una cancelacion de reserva
	 * dependiendo de la fecha y el valor de la reserva
	 */
	public abstract boolean esAbonoParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual);
	
	public abstract double monto(Reserva reserva);
}
