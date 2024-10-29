package controle_view;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * A classe abstrata EstadoView representa um estado de visualização em uma aplicação com interface gráfica.
 * Ela é estendida por classes específicas que implementam o comportamento e a transição entre diferentes estados de visualização.
 * 
 * Esta classe possui um método abstrato para definir o próximo estado, 
 * e um atributo booleano para indicar se o estado deve ser alterado.
 * 
 */

public abstract class EstadoView extends JPanel {
	 /** Indica se o estado deve ser alterado. */
	 protected boolean mudarEstado;

	 /**
	  * Retorna o valor do atributo mudarEstado, que indica se o estado atual deve ser alterado.
	  * 
	  * @return true se o estado deve ser alterado; false caso contrário.
	  */
	 public boolean getMudarEstado() {
		 return mudarEstado;
	 }
 
	 /**
	  * Método abstrato que define a transição para o próximo estado.
	  * Este método deve ser implementado pelas subclasses para definir o comportamento
	  * de transição de estados específicos.
	  * 
	  * @return o próximo estado da aplicação.
	  */
	public abstract EstadoView proximoEstado();
}
