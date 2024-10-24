package controle_view;
import java.awt.event.KeyEvent;

import javax.swing.*;
public abstract class EstadoView extends JPanel {
	protected boolean mudarEstado;
	public boolean getMudarEstado() {
		return mudarEstado;
	}
	public abstract EstadoView proximoEstado();
}
