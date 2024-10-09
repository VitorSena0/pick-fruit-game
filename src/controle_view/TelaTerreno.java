package controle_view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import javax.swing.*;

public class TelaTerreno extends EstadoView {

	TelaTerreno(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas) {
		setBounds(0, 0, 632, 632);
		setLayout(null);
		Janela janela = new Janela(632, dimensao, pedras, maracujas, maracujas_chao, laranjeiras, laranjas, abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeiras, amoras, goiabeiras, goiabas, probabidade_bichadas);
		setLayout(new BorderLayout());
		add(janela, BorderLayout.CENTER);
		revalidate();  // Atualiza o layout
		repaint();     // Redesenha o componente
	}
	
	@Override
	public EstadoView proximoEstado() {
		return this;
	}
	
	
}
