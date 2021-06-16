package Suscripciones;

import java.util.List;

import inmueble.Inmueble;

public interface INotify extends HomePagePublisher, PopUpWindow {

	
	List<Inmueble> getInmueblesConInteres();

}
