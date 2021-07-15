package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class SinCancelacion extends PoliticaDeCancelacion {

	@Override
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		return reserva.valor();
	}

}
