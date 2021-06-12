package politicasDeCancelacion;

import reservas.Reserva;

public class SinCancelacion extends PoliticaDeCancelacion {

	@Override
	public void cancelar(Reserva reserva) {
		reserva.confirmarPagoPor(reserva.valorPorDias((reserva.getFechas()).size()));
		reserva.cancelar();
	}

}
