package reservas;

public interface Sitio {

	void agegarReserva(Reserva r);

	void enviarMailDeConfirmacion(Reserva reserva);

}
