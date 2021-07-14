package usuario;

import java.time.LocalDate;

import Categorias.Categoria;

public interface PuntuablePorEstadia {

	void recibirPuntuacionPorEstadia(Categoria categoria, int puntos);

	boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario usuario, LocalDate fechaActual);


}
