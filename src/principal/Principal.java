package principal;
import controle_view.*;
import java.awt.EventQueue;

import controle_view.JanelaTeste;
public class Principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaTeste frame = new JanelaTeste();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
