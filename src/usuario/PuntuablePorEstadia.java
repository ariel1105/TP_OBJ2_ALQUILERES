package usuario;

import sitio.Categoria;

public interface PuntuablePorEstadia {

	boolean puedeRecibirPuntuacionPorEstadiaPor(Usuario usuario);

	void recibirPuntuacionPorEstadia(Categoria categoria, int puntos);


}
