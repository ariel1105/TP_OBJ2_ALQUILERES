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
	
	/**
     * Clase encargada de las reservas del inquilino
     */
	private List<Reserva> reservas;
	
	public void ingresar(Reserva reserva) {
		reservas.add(reserva);
	}
	/**
     * Metodo que agrega una reserva a la lista de reservas
     */
	public AdministadorDeReservasInquilino() {
		this.reservas = new ArrayList<Reserva>();
	}
	/**
     * Constructor de la clase
     * No tiene parametros, solo se intancia la lista a un array
     */
	public List<Reserva> getReservas(){
		return this.reservas;
	}
	/**
     * Metodo que retorna todas las reservas
     */
	public List<Reserva> reservasConfirmadas(){
		return this.reservas
				   .stream()
				   .filter(r -> r.estaConfirmada())
				   .collect(Collectors.toList());
	}
	/**
     * Metodo que retorna todas las reservas confirmadas
     */
	public List<Reserva> reservasFuturas(LocalDate fechaActual) {
		List<Reserva> reservasFuturas = this.reservasConfirmadas().stream()
																  .filter(r -> r.esReservaFutura(fechaActual))
																  .collect(Collectors.toList());
		return reservasFuturas;
		
	}
	/**
     * Metodo que retorna todas las reservas futuras
     */
	public List<Reserva> reservasConcretadas(LocalDate fechaActual) {
		List<Reserva> reservasConcretadas = this.reservasConfirmadas().stream()
																	  .filter(r -> !r.esReservaFutura(fechaActual))
																	  .collect(Collectors.toList());
		return reservasConcretadas;
	}
	/**
     * Metodo que retorna todas las reservas que ya fueron concretadas
     */
	public List<Reserva> reservasDeCiudad(String ciudad) {
		List<Reserva> reservasDeCiudad = this.reservasConfirmadas().stream()
													               .filter(r -> r.esDeCiudad(ciudad))
													               .collect(Collectors.toList());
		return reservasDeCiudad;
	}
	/**
     * Metodo que retorna todas las reservas de una ciudad dada como paremetro
     */
	public List<String> ciudadesConReserva() {
		List<String> ciudades = this.reservasConfirmadas().stream()
														  .map(r -> r.getInmueble().getCiudad())
														  .collect(Collectors.toList());
		return ciudades;
	}
	/**
     * Metodo que retorna todas las ciudades con reservas
     */
	public void cancelarReserva(Reserva reserva, LocalDate fechaActual) {
		if (this.reservas.contains(reserva)) {
			reserva.iniciarCancelacion(fechaActual);
		}
	}
	/**
     * Metodo que cancela una reserva
     */
	public Integer cantidadeDeReservas() {
		return this.reservasConfirmadas().size();
	}
	/**
     * Metodo que retorna la cantidad total de reservas confirmadas
     */
	public boolean leAlquiloA(Usuario propietario, LocalDate fechaActual) {
		return this.reservasConcretadas(fechaActual).stream()
										 			.anyMatch(r -> r.getInmueble().getPropietario().equals(propietario));
										 
	}
	/**
     * Metodo que verifica si le alquilo a un usuario dado como parametro
     */
	public boolean alquilo(Inmueble inmueble, LocalDate fechaActual) {
		return this.reservasConcretadas(fechaActual).stream()
													.anyMatch(r -> r.getInmueble().equals(inmueble));
														
	}
	/**
     * Metodo que verifica si alquiló un inmueble pasado como parametro
     */
}
