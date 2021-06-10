package reservas;

import java.time.LocalDate;


public class PendienteDeConfirmacion extends Estado {

	@Override
	public Boolean fechaOcupadaEn(LocalDate dia, Reserva reserva) {
		return false;
	}

}
