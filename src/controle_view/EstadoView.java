package controle_view;
import javax.swing.*;
/**
 * A classe abstrata EstadoView representa um estado genérico na interface gráfica
 * de uma aplicação, estendendo JPanel. Ela define o comportamento básico para a mudança
 * de estados entre diferentes telas da aplicação.
 */
public abstract class EstadoView extends JPanel {
	  /**
     * Indica se o estado da aplicação deve ser alterado.
     */
	protected boolean mudarEstado;
	 /**
     * Retorna o valor de mudarEstado, indicando se o estado da aplicação deve ser alterado.
     *
     * @return true se o estado deve ser alterado, false caso contrário.
     */
	public boolean getMudarEstado() {
		return mudarEstado;
	}

	  /**
     * Método abstrato responsável por retornar o próximo estado da aplicação.
     * Cada implementação concreta de EstadoView deve definir como a transição de estados ocorre.
     *
     * @return O próximo estado da aplicação.
     */
	public abstract EstadoView proximoEstado(); 
}
