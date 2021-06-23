	package administradorDeReservas;

import java.time.LocalDate;
import java.util.ArrayList;

import inmueble.Inmueble;
import reservas.Reserva;
import usuario.Usuario;

public class AdministadorDeReservasInquilino {
	private ArrayList<Reserva> reservas;
	private LocalDate fechaActual;
	
	public void ingresar(Reserva reserva) {
		reservas.add(reserva);
	}

	public AdministadorDeReservasInquilino(LocalDate fechaActual) {
		this.reservas = new ArrayList<Reserva>();
		this.fechaActual = fechaActual;
	}

	public boolean esReservaFutura(Reserva reserva) {
		return (this.fechaActual.isBefore(reserva.primerDia()));
	}


	public ArrayList<Reserva> getTodasLasReservas() {
		return this.reservas;
	}

	public ArrayList<Reserva> reservasFuturas() {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		ArrayList<Reserva> todasLasReservas = this.reservas;
		for(int i = 0; i < todasLasReservas.size(); i++) {
			if(this.esReservaFutura(todasLasReservas.get(i))) {
				reservas.add(todasLasReservas.get(i));
			}
		}
		return reservas;
	}
	
	public ArrayList<Reserva> reservasConcretadas() {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		ArrayList<Reserva> todasLasReservas = this.reservas;
		for(int i = 0; i < todasLasReservas.size(); i++) {
			if(!this.esReservaFutura(todasLasReservas.get(i))) {
				reservas.add(todasLasReservas.get(i));
			}
		}
		return reservas;
	}

	public ArrayList<Reserva> reservasDeCiudad(String ciudad) {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		ArrayList<Reserva> todasLasReservas = this.reservas;
		for(int i = 0; i < todasLasReservas.size(); i++) {
			if(todasLasReservas.get(i).esDeCiudad(ciudad)) {
				reservas.add(todasLasReservas.get(i));
			}
		}
		return reservas;
	}

	public ArrayList<String> ciudadesConReserva() {
		ArrayList<Reserva> reservas = this.reservas;
		ArrayList<String> ciudades = new ArrayList<String>();
		for (int i = 0; i < reservas.size(); i++) {
			ciudades.add(reservas.get(i).ciudad());
		}
		return ciudades;
	}

	public void cancelarReserva(Reserva reserva, LocalDate fechaActual) {
		if (this.reservas.contains(reserva)) {
			reserva.iniciarCancelacion(fechaActual);
		}
	}

	public Integer cantidadeDeReservas() {
		return this.reservas.size();
	}

	public boolean leAlquiloA(Usuario propietario) {
		ArrayList <Reserva> reservasPasadas = this.reservasConcretadas();
		boolean resultado = false;
		for (int i = 0; i < reservasPasadas.size(); i++) {
			if (reservasPasadas.get(i).getInmueble().getPropietario().equals(propietario)) {
				resultado = true;
			}
		}
		return resultado;
	}

	public boolean alquilo(Inmueble inmueble) {
		ArrayList <Reserva> reservasPasadas = this.reservasConcretadas();
		boolean resultado = false;
		for (int i = 0; i < reservasPasadas.size(); i++) {
			if (reservasPasadas.get(i).getInmueble().equals(inmueble)) {
				resultado = true;
			}
		}
		return resultado;
	}
}
