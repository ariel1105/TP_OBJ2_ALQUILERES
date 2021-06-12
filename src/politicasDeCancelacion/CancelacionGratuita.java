package politicasDeCancelacion;
import java.time.LocalDateTime;
import reservas.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {

	@Override
	public void cancelar(Reserva reserva) {
		if (this.noTieneQueAbonar(reserva)) {
			reserva.cancelar();
		}
	}

	public Boolean noTieneQueAbonar(Reserva reserva) {
		return this.getFechaActual().compareTo(reserva.primerDia())>10;
	}
}