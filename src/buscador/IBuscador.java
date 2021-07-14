package buscador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;
import sitio.Sitio;

public interface  IBuscador  {



	public List <Inmueble> filtrar(List<Inmueble> inmuebles);
}
