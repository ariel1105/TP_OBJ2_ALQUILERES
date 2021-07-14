	package administradorDeReservas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import inmueble.Inmueble;
import reservas.Estado;
import reservas.Reserva;
import usuario.Usuario;

public class AdministadorDeReservasInquilino {
	private List<Reserva> reservas;
	
	public void ingresar(Reserva reserva) {
		reservas.add(reserva);
	}

	public AdministadorDeReservasInquilino() {
		this.reservas = new ArrayList<Reserva>();
	}
	
	public List<Reserva> getReservas(){
		return this.reservas;
	}

	public List<Reserva> reservasConfirmadas(){
		return this.reservas
				   .stream()
				   .filter(r -> r.estaConfirmada())
				   .collect(Collectors.toList());
	}

	public List<Reserva> reservasFuturas(LocalDate fechaActual) {
		List<Reserva> reservasFuturas = this.reservasConfirmadas().stream()
																  .filter(r -> r.esReservaFutura(fechaActual))
																  .collect(Collectors.toList());
		return reservasFuturas;
		
	}
	
	public List<Reserva> reservasConcretadas(LocalDate fechaActual) {
		List<Reserva> reservasConcretadas = this.reservasConfirmadas().stream()
																	  .filter(r -> !r.esReservaFutura(fechaActual))
																	  .collect(Collectors.toList());
		return reservasConcretadas;
	}

	public List<Reserva> reservasDeCiudad(String ciudad) {
		List<Reserva> reservasDeCiudad = this.reservasConfirmadas().stream()
													               .filter(r -> r.esDeCiudad(ciudad))
													               .collect(Collectors.toList());
		return reservasDeCiudad;
	}

	public List<String> ciudadesConReserva() {
		List<String> ciudades = this.reservasConfirmadas().stream()
														  .map(r -> r.getInmueble().getCiudad())
														  .collect(Collectors.toList());
		return ciudades;
	}

	public void cancelarReserva(Reserva reserva, LocalDate fechaActual) {
		if (this.reservas.contains(reserva)) {
			reserva.iniciarCancelacion(fechaActual);
		}
	}

	public Integer cantidadeDeReservas() {
		return this.reservasConfirmadas().size();
	}

	public boolean leAlquiloA(Usuario propietario, LocalDate fechaActual) {
		return this.reservasConcretadas(fechaActual).stream()
										 			.anyMatch(r -> r.getInmueble().getPropietario().equals(propietario));
										 
	}

	public boolean alquilo(Inmueble inmueble, LocalDate fechaActual) {
		return this.reservasConcretadas(fechaActual).stream()
													.anyMatch(r -> r.getInmueble().equals(inmueble));
														
	}
}
