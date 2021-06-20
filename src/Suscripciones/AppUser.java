package Suscripciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import inmueble.Inmueble;

public class AppUser implements  INotify{
	
	private List<Inmueble> inmueblesDeInteres;
	
	public AppUser(List <Inmueble> tipoDeInmuebles) {
		
		
		inmueblesDeInteres= tipoDeInmuebles;
		
		for (Inmueble observable : tipoDeInmuebles) {
			
			observable.addObserver(this);
		}
	
		
		
	}
	
		@Override
	public void popUp(String message, String color, int fontSize) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
		@Override
	public  List<Inmueble> getInmueblesConInteres() {
		// TODO Auto-generated method stub
		return inmueblesDeInteres;
	}


	@Override
	public void publish(String string) {
		// TODO Auto-generated method stub
		
	}







	
	

}
