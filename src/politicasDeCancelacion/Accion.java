	package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public abstract class Accion {

	public abstract boolean esAccionParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual);
	
	public abstract void realizarAccionDePago(Reserva reserva);

}
