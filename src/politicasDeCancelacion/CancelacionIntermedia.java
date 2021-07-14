package politicasDeCancelacion;


import java.time.LocalDate;
import java.util.ArrayList;

import reservas.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
	
	private ArrayList<Accion> accionesPosibles;
	
	public CancelacionIntermedia() {
		this.accionesPosibles = new ArrayList<Accion>();
		this.accionesPosibles.add(new SinAbono());
		this.accionesPosibles.add(new AbonoDel50PorCiento());
		this.accionesPosibles.add(new AbonoTotal());
	}
		//this.accionParaReserva.realizarAccionDePago;
		//reserva.cancelar;
	public Accion accionParaReserva(Reserva reserva, LocalDate fechaActual) {
		int i = 0;
		while (!this.accionesPosibles.get(i).esAccionParaReserva(this, reserva, fechaActual)) {
			i++;
		}
		return this.accionesPosibles.get(i);
	}
	@Override
	public void cancelar(Reserva reserva, LocalDate fechaActual) {
		this.accionParaReserva(reserva, fechaActual).realizarAccionDePago(reserva);
	}
	
}
