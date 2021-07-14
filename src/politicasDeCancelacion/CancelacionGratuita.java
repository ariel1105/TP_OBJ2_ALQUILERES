package politicasDeCancelacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import reservas.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {

	public Boolean tieneQueAbonar(Reserva reserva, LocalDate fechaActual) {
		return !this.diferenciaDeDiasEsMayor(reserva, 10, fechaActual);
	}

	@Override
	public void cancelar(Reserva reserva, LocalDate fechaActual) {
		if (this.tieneQueAbonar(reserva, fechaActual)) {
			this.realizarPagoPor(reserva.valorPorDias(2));
		}
		
	}
}