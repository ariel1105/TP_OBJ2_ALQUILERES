package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class SinAbono extends Accion {
	
	

	@Override
	public boolean esAccionParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual) {
		return cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 19, fechaActual);
	}

	// error al agregar la annotation override
	public void realizarAccionDePago(Reserva reserva) {
		
	}

}
