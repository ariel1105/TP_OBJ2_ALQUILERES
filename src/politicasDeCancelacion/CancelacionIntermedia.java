package politicasDeCancelacion;


import java.util.ArrayList;

import reservas.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
	
	private ArrayList<Accion> accionesPosibles;
	
	public CancelacionIntermedia(ArrayList<Accion> acciones) {
		this.accionesPosibles = acciones;
	}
		//this.accionParaReserva.realizarAccionDePago;
		//reserva.cancelar;
	public Accion accionParaReserva(Reserva reserva) {
		int i = 0;
		while (!this.accionesPosibles.get(i).esAccionParaReserva(this, reserva)) {
			i++;
		}
		return this.accionesPosibles.get(i);
	}
	@Override
	public void cancelar(Reserva reserva) {
		this.accionParaReserva(reserva).realizarAccionDePago(reserva);
		reserva.cancelar();
	}
	
	
	/*	
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
	*/

}
