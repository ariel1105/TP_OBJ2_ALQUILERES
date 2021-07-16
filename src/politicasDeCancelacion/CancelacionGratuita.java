package politicasDeCancelacion;
import java.time.LocalDate;
import java.time.LocalDateTime;

import inmueble.Inmueble;
import reservas.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {
	
	/**
	 * clase concreta strategy de la reserva que define el valor de penalidad
	 * en caso de cancelarse
	 */

	public boolean tieneQueAbonar(Reserva reserva, LocalDate fechaActual) {
		return !this.diferenciaDeDiasEsMayor(reserva, 10, fechaActual);
	}
	
	/**
	 * define si se tiene que abonar la cancelacion de la reserva dependiendo de la cantidad
	 * de dias de diferencia entre el primer dia de la reserva y la fecha actual
	 */

	
	@Override
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		double importe = 0;
		if (this.tieneQueAbonar(reserva, fechaActual)) {
			importe = this.valorPenalizacionPorCancelacion(reserva);
		}
		return importe;
	}
	/**
	 * define el valor de la penalidad de la cancelacion de la reserva
	 */


	public double valorPenalizacionPorCancelacion(Reserva reserva) {
		double importe = reserva.getInmueble().obtenerElPrecioParaLaFecha(reserva.getDiaInicio());
		LocalDate segundoDiaDeReserva = reserva.getDiaInicio().plusDays(1);
		if (reserva.ocupaFecha(segundoDiaDeReserva)) {
			importe = importe + reserva.getInmueble().obtenerElPrecioParaLaFecha(segundoDiaDeReserva);
		}
		return importe;
	}
	/**
	 * retorna el valor dependiendo de la cantidad de dias que tiene la reserva
	 * si es uno, es el valor por ese dia, si son mas es el valor por dos dias de la reserva
	 * 
	 */
}