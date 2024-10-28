package controle_view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class TelaVitoria extends EstadoView {
	
	TelaVitoria(String imgVencedor, String nomeVencedor) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(0, 0, 572, 895);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(nomeVencedor + " Venceu!!!");
		lblNewLabel.setBounds(150, 10, 450, 67);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Source Serif Pro", Font.BOLD | Font.ITALIC, 36));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clique para voltar a tela inicial");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Light", Font.ITALIC, 28));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 524, 852, 49);
		add(lblNewLabel_1);

		PainelDesenho vencedor = new PainelDesenho();
		vencedor.setAtributos(313, 179, 253, 189, imgVencedor);
		add(vencedor);
		
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
			return new TelaInicial();
		}
		else {
			return this;
		}
	}
}
