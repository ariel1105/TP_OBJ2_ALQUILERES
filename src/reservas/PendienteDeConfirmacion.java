package reservas;

public class PendienteDeConfirmacion extends Estado {

	@Override
	public Boolean diaOcupadoEn(Dia dia, Reserva reserva) {
		return false;
	}

}
