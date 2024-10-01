import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Container extends JFrame{
	public Container() {
		this.setTitle("Cata Fruta");
		this.setSize(800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
		
		Janela janela = new Janela();
		add(janela);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			Container container = new Container();
		});

		
	}
}
