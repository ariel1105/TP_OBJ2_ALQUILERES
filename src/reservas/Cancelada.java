package reservas;

public class Cancelada extends Estado {

	@Override
	public Boolean diaOcupadoEn(Dia dia, Reserva reserva) {
		return false;
	}

}
