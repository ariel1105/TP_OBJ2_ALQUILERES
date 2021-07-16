package politicasDeCancelacion;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import reservas.Reserva;

public class CancelacionIntermedia extends PoliticaDeCancelacion {
	
	/**
	 * estrategia concreta correspondiente a la cancelacion de una reserva
	 * dispone de una lista de abonos que determinaran el valor que deberia de abonarse
	 * dependiendo de la cantidad de dias de diferencia entre la fecha actual y la primer
	 * fecha de reserva
	 */
	
	private List<Abono> abonosPosibles;
	
	/**
	 * los tipos de abonos ya vienen predefinidos, no se pueden agregar ni quitar 
	 */
	public CancelacionIntermedia() {
		this.abonosPosibles = new ArrayList<Abono>();
		this.abonosPosibles.add(new SinAbono());
		this.abonosPosibles.add(new AbonoDel50PorCiento());
		this.abonosPosibles.add(new AbonoTotal());
	}
	
	/**
	 * retorna el abono correspondiente dependiendo de la diferencia de fechas
	 */
		
	public Abono abonoParaReserva(Reserva reserva, LocalDate fechaActual) {
		return this.abonosPosibles.stream()
								  .filter(a -> a.esAbonoParaReserva(this, reserva, fechaActual))
								  .findFirst()
								  .get();
	}
	/**
	 * una vez definido el abono, se le delega la responsabilidad de definir el importe
	 */
	@Override
	public double valorPara(Reserva reserva, LocalDate fechaActual) {
		return this.abonoParaReserva(reserva, fechaActual).monto(reserva);
	}
	
}
