package politicasDeCancelacion;


import java.time.LocalDate;
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
	public void cancelar(Reserva reserva, LocalDate fechaActual) {
		this.accionParaReserva(reserva).realizarAccionDePago(reserva);
	}
	
}
