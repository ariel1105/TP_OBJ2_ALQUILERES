package politicasDeCancelacion;


import reservas.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {

	@Override
	public void cancelar(Reserva reserva) {
		if (this.noTieneQueAbonar(reserva)) {
			reserva.cancelar();
		}
		if (this.tieneQueAbonar50PorCiento(reserva)) {
			reserva.confirmarPagoPor(reserva.valor()/2);
			reserva.cancelar();
		}
		if (this.tieneQueAbonarTotalidad(reserva)) {
			reserva.confirmarPagoPor(reserva.valor());
			reserva.cancelar();
		}
	}

	public Boolean noTieneQueAbonar(Reserva reserva) {
		return this.diferenciaDeDiasEsMayor(reserva, 19);
	}

	public Boolean tieneQueAbonar50PorCiento(Reserva reserva) {
		return ((!this.noTieneQueAbonar(reserva)) && 
				(this.diferenciaDeDiasEsMayor(reserva, 9)));
	}

	public Boolean tieneQueAbonarTotalidad(Reserva reserva) {
		return ((!this.tieneQueAbonar50PorCiento(reserva))&& 
				(!this.noTieneQueAbonar(reserva)));
	}

}
