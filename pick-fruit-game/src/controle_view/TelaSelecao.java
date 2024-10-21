package controle_view;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class TelaSelecao extends EstadoView {
	private enum opcao {
		ARQUIVO,
		CONFIGURACOES
	}
	private enum botao {
		VOLTAR,
		PROXIMO
	}
	private botao botaoSelecionado;
	private opcao opcaoSelecionada;
	private JButton botaoProximo;
	private JButton botaoVoltar;
	private JRadioButton opcaoArquivo;
	private JRadioButton opcaoConfiguracoes;
	TelaSelecao() {
		mudarEstado = false;
		
		setBounds(313, 179, 239, 119);
		
		opcaoArquivo = new JRadioButton("Carregar arquivo de terreno");
		opcaoArquivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		opcaoArquivo.setBounds(14, 39, 219, 21);
		
		opcaoConfiguracoes = new JRadioButton("Inserir configurações de terreno");
		opcaoConfiguracoes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		opcaoConfiguracoes.setBounds(14, 62, 219, 21);
		setLayout(null);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(opcaoConfiguracoes);
		bg.add(opcaoArquivo);
		
		add(opcaoArquivo);
		add(opcaoConfiguracoes);
		
		botaoProximo = new JButton("Próximo");
		botaoProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoProximo.setBounds(130, 89, 99, 21);
		botaoProximo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (opcaoConfiguracoes.isSelected()) {
					opcaoSelecionada = opcao.CONFIGURACOES;
					mudarEstado = true;
				}
				else if (opcaoArquivo.isSelected()) {
					opcaoSelecionada = opcao.ARQUIVO;
					mudarEstado = true;
				}
				botaoSelecionado = botao.PROXIMO;
			}
		});
		add(botaoProximo);
		
		botaoVoltar = new JButton("Voltar");
		botaoVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoVoltar.setBounds(10, 89, 99, 21);
		botaoVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
				botaoSelecionado = botao.VOLTAR;
			}
		});
		add(botaoVoltar);
		
		JLabel lblNewLabel_2 = new JLabel("Escolha uma opção");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 10, 219, 23);
		add(lblNewLabel_2);
	}
	@Override
	public EstadoView proximoEstado() {
		if (mudarEstado && botaoSelecionado == botao.VOLTAR) {
			return new TelaInicial();
		}
		else if(mudarEstado && botaoSelecionado == botao.PROXIMO && opcaoSelecionada == opcao.ARQUIVO) {
			return new EscolherArquivo();
		}
		else if(mudarEstado && botaoSelecionado == botao.PROXIMO && opcaoSelecionada == opcao.CONFIGURACOES) {
			return new TelaConfiguracoes();
		}
		else {
			return this;
		}
	}
}
