package politicasDeCancelacion;
import java.time.LocalDate;
import java.time.LocalDateTime;

import inmueble.Inmueble;
import reservas.Reserva;

public class CancelacionGratuita extends PoliticaDeCancelacion {

	public boolean tieneQueAbonar(Reserva reserva, LocalDate fechaActual) {
		return !this.diferenciaDeDiasEsMayor(reserva, 10, fechaActual);
	}

	
	@Override
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		double importe = 0;
		if (this.tieneQueAbonar(reserva, fechaActual)) {
			importe = this.valorPenalizacionPorCancelacion(reserva);
		}
		return importe;
	}


	public double valorPenalizacionPorCancelacion(Reserva reserva) {
		double importe = reserva.getInmueble().obtenerElPrecioParaLaFecha(reserva.getDiaInicio());
		LocalDate segundoDiaDeReserva = reserva.getDiaInicio().plusDays(1);
		if (reserva.ocupaFecha(segundoDiaDeReserva)) {
			importe = importe + reserva.getInmueble().obtenerElPrecioParaLaFecha(segundoDiaDeReserva);
		}
		return importe;
	}
}