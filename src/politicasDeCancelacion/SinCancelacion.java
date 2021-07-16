package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class SinCancelacion extends PoliticaDeCancelacion {
	
	/**
	 * concret strategy en el cual define que el valor a abonar por cancelacion de reserva
	 * es el valor total de la reserva
	 */

	@Override
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		return reserva.valor();
	}

}
