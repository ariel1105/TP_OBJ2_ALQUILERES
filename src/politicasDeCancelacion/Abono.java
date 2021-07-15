	package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public abstract class Abono {

	public abstract boolean esAbonoParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual);
	
	public abstract double monto(Reserva reserva);
}
