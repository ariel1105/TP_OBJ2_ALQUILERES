package usuario;

import Categorias.Categoria;

public interface PuntuablePorEstadia {

	boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario usuario);

	void recibirPuntuacionPorEstadia(Categoria categoria, int puntos);


}
