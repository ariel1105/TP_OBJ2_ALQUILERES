package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public class SinCancelacion extends PoliticaDeCancelacion {

	@Override
	public void cancelar(Reserva reserva, LocalDate fechaActual) {
		this.realizarPagoPor(reserva.valor());
	}

}
