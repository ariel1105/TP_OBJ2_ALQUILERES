package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public abstract class PoliticaDeCancelacion {

	public void cancelar(Reserva reserva, LocalDate fechaActual) {
		this.realizarPagoPor(valorPara(reserva, fechaActual));
	}
	
	public Boolean diferenciaDeDiasEsMayor(Reserva reserva, int cantidadDeDias, LocalDate fechaActual) {
		return (reserva.getDiaInicio().compareTo(fechaActual)>cantidadDeDias);
	}
	
	public void realizarPagoPor(Double importe) {
		System.out.println("Se debitaron:" + importe + " por cancelacion de reserva");
	}
	
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		return 0;
	}

}
