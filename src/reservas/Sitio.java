package reservas;

import java.util.List;

public interface Sitio {

	void agegarReserva(Reserva r);

	void enviarMailDeConfirmacion(Reserva reserva);

	List<Publicacion> busquedaDeInmuebles(Busqueda busqueda1);

}
