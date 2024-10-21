package controle_view;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class TelaInicial extends EstadoView {
	
	TelaInicial() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(0, 0, 572, 895);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cata Fruta");
		lblNewLabel.setBounds(313, 10, 239, 67);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Source Serif Pro", Font.BOLD | Font.ITALIC, 48));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clique na tela para iniciar");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Light", Font.ITALIC, 28));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 524, 852, 49);
		add(lblNewLabel_1);

		PainelDesenho maracuja = new PainelDesenho();
		maracuja.setAtributos(313, 179, 253, 189, "res"+ System.getProperty("file.separator") +"Maracuja.png");
		add(maracuja);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
			}
		});
	}
	@Override
	public EstadoView proximoEstado() {
		if (mudarEstado) {
			return new TelaSelecao();
		}
		else {
			return this;
		}
	}
}
