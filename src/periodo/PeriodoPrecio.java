package periodo;

import java.time.LocalDate;
import java.util.ArrayList;

public class PeriodoPrecio {
	private double precio;
	private ArrayList<LocalDate> periodo;

	public PeriodoPrecio(double precio, ArrayList<LocalDate> periodo) {
		// TODO Auto-generated constructor stub
		this.precio = precio;
		this.periodo = periodo;
	}


	public boolean perteneceLaFecha(LocalDate fecha) {

		return periodo.contains(fecha);

	}


	private boolean sonLaMismaFecha(LocalDate fecha, LocalDate fecha2) {
			int dia1 = fecha.getDayOfMonth();
			int mes1 = fecha.getMonthValue();
			int año1 = fecha.getYear();
			int dia2 = fecha2.getDayOfMonth();
			int mes2 = fecha2.getMonthValue();
			int año2 = fecha2.getYear();
			
			return (dia1 == dia2 && mes1 == mes2 && año1 == año2);
	}


	public double getPrecio() {
		// TODO Auto-generated method stub
		return precio;
	}


	public ArrayList<LocalDate> getPeriodo() {
		// TODO Auto-generated method stub
		return periodo;
	}


	public void agregarFecha(LocalDate fecha) {
		// TODO Auto-generated method stub
		this.periodo.add(fecha);
	}
}
