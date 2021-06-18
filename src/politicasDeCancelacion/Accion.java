package politicasDeCancelacion;

import reservas.Reserva;

public abstract class Accion {

	public abstract boolean esAccionParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva);
	
	public abstract void realizarAccionDePago(Reserva reserva);

}
