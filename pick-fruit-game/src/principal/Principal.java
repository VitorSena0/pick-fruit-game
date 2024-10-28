package principal;
import controle_view.*;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaJogo frame = new JanelaJogo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
