package controle_view;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

/**
 * A classe {@code TelaSelecao} representa a tela de seleção do jogo "Cata Fruta".
 * Nesta tela, o usuário pode escolher entre carregar um arquivo de terreno ou inserir
 * configurações de terreno manualmente.
 * 
 * <p>A tela contém opções de seleção apresentadas como botões de rádio, além de botões
 * para navegar entre as telas.</p>
 */
public class TelaSelecao extends EstadoView {

		  /**
     * Enumeração que representa as opções disponíveis na tela de seleção.
     */
	private enum opcao {
		ARQUIVO,
		CONFIGURACOES
	}

		/**
     * Enumeração que representa os botões disponíveis na tela de seleção.
     */
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

	/**
     * Construtor da classe {@code TelaSelecao}. Inicializa a interface gráfica
     * da tela de seleção, incluindo as opções de seleção e os botões de navegação.
     */
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

		 /**
     * Este método determina o próximo estado da aplicação com base na seleção do usuário.
     * Se o usuário escolher voltar, o método retorna uma nova instância da classe 
     * {@link TelaInicial}. Se o usuário selecionar a opção de carregar um arquivo, 
     * o método retorna uma nova instância da classe {@link EscolherArquivo}. 
     * Se a opção de configurações for selecionada, retorna uma nova instância da 
     * classe {@link TelaConfiguracoes}. Se nenhuma mudança de estado ocorrer, 
     * retorna a tela atual.
     * 
     * @return Uma instância de {@link EstadoView} que representa o próximo estado da aplicação.
     */
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
