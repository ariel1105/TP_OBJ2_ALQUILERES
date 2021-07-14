package AdministradorDelSitio;

import java.util.Comparator;

import usuario.Usuario;

public class CompararUsuarios  implements Comparator<Usuario> {
	/**
     * Esta clase se utilizará para ordenar a los inquilinos que mas alquilaron
     * en forma ascendente (metodo usuariosRank de Administrador), implementando la interfaz Comparator<Usuario>.
     */


	@Override
	public int compare(Usuario u1, Usuario u2) {
		// TODO Auto-generated method stub
		if 	(u1.vecesQueAlquilaron() > u2.vecesQueAlquilaron()) {
			return -1;
		}else if(u1.vecesQueAlquilaron()>u2.vecesQueAlquilaron()) {
			return 0;
		}
		else {
			return 1;
		}
	}

	/**
     * Método que devuelve un entero comparando las veces que alquilaron dos usuarios dados como parametro.
     */

}
