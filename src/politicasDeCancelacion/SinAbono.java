package politicasDeCancelacion;

import reservas.Reserva;

public class SinAbono extends Accion {
	
	

	@Override
	public boolean esAccionParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva) {
		return cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 19);
	}

	// error al agregar la annotation override
	public void realizarAccionDePago(Reserva reserva) {
		
	}

}
