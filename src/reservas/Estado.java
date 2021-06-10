package reservas;

import java.time.LocalDate;

public abstract class Estado {

	public abstract Boolean fechaOcupadaEn(LocalDate dia, Reserva reserva);

}

	