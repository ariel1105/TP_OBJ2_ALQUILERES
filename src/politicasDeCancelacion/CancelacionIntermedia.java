package politicasDeCancelacion;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import reservas.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
	
	private List<Abono> abonosPosibles;
	
	public CancelacionIntermedia() {
		this.abonosPosibles = new ArrayList<Abono>();
		this.abonosPosibles.add(new SinAbono());
		this.abonosPosibles.add(new AbonoDel50PorCiento());
		this.abonosPosibles.add(new AbonoTotal());
	}
		
	public Abono abonoParaReserva(Reserva reserva, LocalDate fechaActual) {
		return this.abonosPosibles.stream()
								  .filter(a -> a.esAbonoParaReserva(this, reserva, fechaActual))
								  .findFirst()
								  .get();
	}
	
	@Override
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		return this.abonoParaReserva(reserva, fechaActual).monto(reserva);
	}
	
}
