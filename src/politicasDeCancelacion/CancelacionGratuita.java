package politicasDeCancelacion;

import reservas.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {

	@Override
	public void cancelar(Reserva reserva) {
		reserva.cancelar();
	}
}