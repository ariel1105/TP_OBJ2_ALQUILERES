package buscador;

import java.time.LocalDate;
import java.util.ArrayList;

import inmueble.Inmueble;
import sitio.Sitio;

public interface  IBuscador  {



	public ArrayList <Inmueble> filtrar(ArrayList<Inmueble> inmuebles);
}
