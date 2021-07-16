package politicasDeCancelacion;

import java.time.LocalDate;

import reservas.Reserva;

public abstract class PoliticaDeCancelacion {
	
	/**
	 *  Strategy correspondiente a la cancelacion de las reservas
	 */

	public void cancelar(Reserva reserva, LocalDate fechaActual) {
		this.realizarPagoPor(valorPara(reserva, fechaActual));
	}
	/**
	 * template method para la cancelacion de la reserva 
	 */
	
	public Boolean diferenciaDeDiasEsMayor(Reserva reserva, int cantidadDeDias, LocalDate fechaActual) {
		return (reserva.getDiaInicio().compareTo(fechaActual)>cantidadDeDias);
	}
	/**
	 * operacion concreta de la clase abstracta que va a ser utilizada por las subclases
	 */
	
	public void realizarPagoPor(Double importe) {
		System.out.println("Se debitaron:" + importe + " por cancelacion de reserva");
	}
	
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		return 0;
	}
	/**
	 * hook method con valor por defecto el cual va a ser sobrescrito por las subclases
	 */

}
