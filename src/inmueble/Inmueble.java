package inmueble;

import java.time.LocalDate;
import java.util.ArrayList;

import periodo.PeriodoPrecio;

public class Inmueble {
	private String tipoDeInmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private ArrayList<String> servicios;
	private int capacidad;
	private ArrayList<Foto> fotos;
	private Hora horarioCheckIn;
	private Hora horarioCheckOut;
	private ArrayList<String> formasDePago;
	private double precio;
	private ArrayList<PeriodoPrecio> periodosConPrecios;
	private double precioPorDefecto;

	public Inmueble(String tipoDeInmueble, double superficie, String pais, String ciudad, String direccion,
			ArrayList<String> servicios, int capacidad, ArrayList<Foto> fotos, Hora horarioCheckIn,
			Hora horarioCheckOut, ArrayList<String> formasDePago, double precio) {
		// TODO Auto-generated constructor stub
		this.tipoDeInmueble = tipoDeInmueble;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = servicios;
		this.capacidad = capacidad;
		this.fotos = fotos;
		this.horarioCheckIn = horarioCheckIn;
		this.horarioCheckOut = horarioCheckOut;
		this.formasDePago = formasDePago;
		this.precio = precio;
		this.periodosConPrecios = new ArrayList<PeriodoPrecio>();
		this.precioPorDefecto = 3000d;
	}

	public double precioParaLaFecha(LocalDate fecha) {
		// TODO Auto-generated method stub
		if (!this.perteneceLaFechaAAlgunPeriodo(fecha)) {
			return this.getPrecioPorDefecto();
		}
		else {
			return this.obtenerElPrecioParaLaFecha(fecha);
		}
	}
	
	public double getPrecioPorDefecto() {
		// TODO Auto-generated method stub
		return this.precioPorDefecto;
	}

	public boolean perteneceLaFechaAAlgunPeriodo(LocalDate fecha) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		for(int i = 0; i < periodosConPrecios.size(); i++) {
			resultado = resultado ||  periodosConPrecios.get(i).perteneceLaFecha(fecha);
		}
		return resultado;
		
	}


	public double obtenerElPrecioParaLaFecha(LocalDate fecha) {
		int i = 0;
		while (i < this.periodosConPrecios.size() && !(this.periodosConPrecios.get(i)).perteneceLaFecha(fecha)) {
			i++;
		}
		return this.periodosConPrecios.get(i).getPrecio();
	}
	

	public void establecerPeriodosConPrecios(PeriodoPrecio periodoPrecio) {
		// TODO Auto-generated method stub
		this.periodosConPrecios.add(periodoPrecio);
	}

	public ArrayList<PeriodoPrecio> getPeriodosYPrecios() {
		// TODO Auto-generated method stub
		return periodosConPrecios;
	}

	public double valorPorDias(ArrayList<LocalDate> fechas) {
		// TODO Auto-generated method stub
		double precio = 0;
		for(int i = 0; i < fechas.size(); i++){
			precio = precio + this.precioParaLaFecha(fechas.get(i));
		}
		return precio;
	}

}
