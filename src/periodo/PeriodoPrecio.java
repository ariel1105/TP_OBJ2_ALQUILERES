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
