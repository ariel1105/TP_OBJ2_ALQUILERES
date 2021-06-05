package reservas;

public class Confirmada extends Estado {

	@Override
	public Boolean diaOcupadoEn(Dia dia, Reserva reserva) {
		return (reserva.getDias().contains(dia));
	}

}
