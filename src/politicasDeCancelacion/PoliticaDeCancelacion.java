package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public abstract class PoliticaDeCancelacion {

	public abstract void cancelar(Reserva reserva, LocalDate fechaActual);
	
	
	public Boolean diferenciaDeDiasEsMayor(Reserva reserva, int cantidadDeDias, LocalDate fechaActual) {
		return (fechaActual.compareTo(reserva.getDiaInicio())>cantidadDeDias);
	}
	
	public void realizarPagoPor(Double importe) {
	
	}

}
