package reservas;

import java.time.LocalDate;


public class Confirmada extends Estado {

	@Override
	public Boolean fechaOcupadaEn(LocalDate dia, Reserva reserva) {
		return (reserva.getFechas().contains(dia));
	}

}
