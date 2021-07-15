package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class SinAbono extends Abono {
	
	

	@Override
	public boolean esAbonoParaReserva(CancelacionIntermedia cancelacionIntermedia, Reserva reserva, LocalDate fechaActual) {
		return cancelacionIntermedia.diferenciaDeDiasEsMayor(reserva, 19, fechaActual);
	}

	@Override
	public double monto(Reserva reserva) {
		return 0d;
	}

}
