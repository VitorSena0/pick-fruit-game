package controle_view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.*;

/**
 * A classe TelaConfiguracoes é responsável pela criação da interface gráfica onde o usuário pode
 * definir as configurações do terreno do jogo. O usuário pode inserir diversos parâmetros como 
 * dimensão do terreno, número de árvores e frutas, e a probabilidade de frutas bichadas.
 * 
 * Essa classe herda de EstadoView e implementa uma lógica de transição entre diferentes estados
 * da aplicação, incluindo a navegação para a próxima tela, o salvamento das configurações em um arquivo 
 * de texto e a possibilidade de voltar para a tela anterior.
 */

public class TelaConfiguracoes extends EstadoView {
	    /**
     * Enumeração dos botões disponíveis na interface: VOLTAR, SALVAR e PROXIMO.
     */
	private enum botao {
		VOLTAR,
		SALVAR,
		PROXIMO
	}
	private JTextField campoBichada;
	private JTextField campoMochila;
	private JTextField campoDimensao;
	private JTextField campoPedras;
	private JTextField campoMaracujas;
	private JTextField campoMaracujasChao;
	private JTextField campoLaranjeiras;
	private JTextField campoLaranjas;
	private JTextField campoAbacateiros;
	private JTextField campoAbacates;
	private JTextField campoCoqueiros;
	private JTextField campoCoco;
	private JTextField campoPesDeAcerola;
	private JTextField campoAcerolas;
	private JTextField campoAmoeiras;
	private JTextField campoAmora;
	private JTextField campoGoiabeiras;
	private JTextField campoGoiabas;
	
	private JTextArea mensagem;
	
	private botao botaoSelecionado;
	
	private JButton botaoVoltar;
	private JButton botaoSalvar;
	private JButton botaoProximo;

	    /**
     * Construtor da classe TelaConfiguracoes. 
     * Responsável pela criação e posicionamento dos componentes gráficos na tela.
     */
	TelaConfiguracoes() {
		setBounds(355, 310, 986, 732);
		setLayout(null);
		
		mudarEstado = false;
		
		JLabel lblNewLabel_2_1 = new JLabel("Defina as configurações do terreno");
		lblNewLabel_2_1.setBounds(10, 5, 328, 28);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Probablidade de fruta vir bichada");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(10, 43, 203, 23);
		add(lblNewLabel_3_1);
		
		campoBichada = new JTextField();
		campoBichada.setColumns(10);
		campoBichada.setBounds(223, 46, 115, 19);
		add(campoBichada);
		
		JLabel lblNewLabel_4 = new JLabel("Dimensão");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 76, 64, 23);
		add(lblNewLabel_4);
		
		campoDimensao = new JTextField();
		campoDimensao.setColumns(10);
		campoDimensao.setBounds(84, 76, 83, 19);
		add(campoDimensao);
		
		JLabel lblNewLabel_4_1 = new JLabel("Pedras");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_1.setBounds(177, 76, 64, 23);
		add(lblNewLabel_4_1);
		
		campoPedras = new JTextField();
		campoPedras.setColumns(10);
		campoPedras.setBounds(251, 76, 83, 19);
		add(campoPedras);
		
		JLabel lblNewLabel_5 = new JLabel("Laranjas");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 106, 64, 23);
		add(lblNewLabel_5);
		
		campoLaranjas = new JTextField();
		campoLaranjas.setColumns(10);
		campoLaranjas.setBounds(84, 106, 83, 19);
		add(campoLaranjas);
		
		JLabel lblNewLabel_5_1 = new JLabel("Laranjeiras");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5_1.setBounds(177, 106, 64, 23);
		add(lblNewLabel_5_1);
		
		campoLaranjeiras = new JTextField();
		campoLaranjeiras.setColumns(10);
		campoLaranjeiras.setBounds(251, 106, 83, 19);
		add(campoLaranjeiras);
		
		JLabel lblNewLabel_6 = new JLabel("Abacates");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 136, 64, 23);
		add(lblNewLabel_6);
		
		campoAbacates = new JTextField();
		campoAbacates.setColumns(10);
		campoAbacates.setBounds(84, 136, 83, 19);
		add(campoAbacates);
		
		JLabel lblNewLabel_6_1 = new JLabel("Abacateiros");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6_1.setBounds(177, 136, 64, 23);
		add(lblNewLabel_6_1);
		
		campoAbacateiros = new JTextField();
		campoAbacateiros.setColumns(10);
		campoAbacateiros.setBounds(251, 136, 83, 19);
		add(campoAbacateiros);
		
		JLabel lblNewLabel_7 = new JLabel("Cocos");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 166, 64, 23);
		add(lblNewLabel_7);
		
		campoCoco = new JTextField();
		campoCoco.setColumns(10);
		campoCoco.setBounds(84, 166, 83, 19);
		add(campoCoco);
		
		JLabel lblNewLabel_7_1 = new JLabel("Coqueiros");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7_1.setBounds(177, 166, 64, 23);
		add(lblNewLabel_7_1);
		
		campoCoqueiros = new JTextField();
		campoCoqueiros.setColumns(10);
		campoCoqueiros.setBounds(251, 166, 83, 19);
		add(campoCoqueiros);
		
		JLabel lblNewLabel_8 = new JLabel("Acerolas");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(10, 196, 64, 23);
		add(lblNewLabel_8);
		
		campoAcerolas = new JTextField();
		campoAcerolas.setColumns(10);
		campoAcerolas.setBounds(84, 196, 83, 19);
		add(campoAcerolas);
		
		JLabel lblNewLabel_8_1 = new JLabel("Pés de acerola");
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8_1.setBounds(177, 196, 84, 23);
		add(lblNewLabel_8_1);
		
		campoPesDeAcerola = new JTextField();
		campoPesDeAcerola.setColumns(10);
		campoPesDeAcerola.setBounds(271, 196, 63, 19);
		add(campoPesDeAcerola);
		
		JLabel lblNewLabel_9 = new JLabel("Amora");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(10, 226, 64, 23);
		add(lblNewLabel_9);
		
		campoAmora = new JTextField();
		campoAmora.setColumns(10);
		campoAmora.setBounds(84, 226, 83, 19);
		add(campoAmora);
		
		JLabel lblNewLabel_9_1 = new JLabel("Amoeiras");
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9_1.setBounds(177, 226, 64, 23);
		add(lblNewLabel_9_1);
		
		campoAmoeiras = new JTextField();
		campoAmoeiras.setColumns(10);
		campoAmoeiras.setBounds(251, 226, 83, 19);
		add(campoAmoeiras);
		
		JLabel lblNewLabel_10 = new JLabel("Goiabas");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(10, 256, 64, 23);
		add(lblNewLabel_10);
		
		campoGoiabas = new JTextField();
		campoGoiabas.setColumns(10);
		campoGoiabas.setBounds(84, 256, 83, 19);
		add(campoGoiabas);
		
		JLabel lblNewLabel_10_1 = new JLabel("Goiabeiras");
		lblNewLabel_10_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10_1.setBounds(177, 256, 64, 23);
		add(lblNewLabel_10_1);
		
		campoGoiabeiras = new JTextField();
		campoGoiabeiras.setColumns(10);
		campoGoiabeiras.setBounds(251, 256, 83, 19);
		add(campoGoiabeiras);
		
		JLabel lblNewLabel_11 = new JLabel("Maracujás");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(10, 286, 64, 23);
		add(lblNewLabel_11);
		
		campoMaracujas = new JTextField();
		campoMaracujas.setColumns(10);
		campoMaracujas.setBounds(84, 286, 83, 19);
		add(campoMaracujas);
		
		JLabel lblNewLabel_11_1 = new JLabel("Maracujás no chão");
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11_1.setBounds(177, 286, 107, 23);
		add(lblNewLabel_11_1);
		
		campoMaracujasChao = new JTextField();
		campoMaracujasChao.setColumns(10);
		campoMaracujasChao.setBounds(294, 286, 40, 19);
		add(campoMaracujasChao);
		
		JLabel lblNewLabel_12_1 = new JLabel("Mochila");
		lblNewLabel_12_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_12_1.setBounds(10, 316, 203, 23);
		add(lblNewLabel_12_1);
		
		campoMochila = new JTextField();
		campoMochila.setColumns(10);
		campoMochila.setBounds(223, 316, 115, 19);
		add(campoMochila);
		
		mensagem = new JTextArea();
		mensagem.setForeground(new Color(255, 0, 0));
		mensagem.setWrapStyleWord(true);
		mensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		mensagem.setLineWrap(true);
		mensagem.setBounds(10, 346, 219, 31);
		mensagem.setEditable(false);
		mensagem.setOpaque(false);
		add(mensagem);
		
		botaoProximo = new JButton("Próximo");
		botaoProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoProximo.setBounds(265, 376, 79, 21);
		botaoProximo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
				botaoSelecionado = botao.PROXIMO;
			}
		});
		add(botaoProximo);
		
		botaoSalvar = new JButton("Salvar Configurações");
		botaoSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoSalvar.setBounds(104, 376, 146, 21);
		botaoSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
				botaoSelecionado = botao.SALVAR;
			}
		});
		add(botaoSalvar);
		
		botaoVoltar = new JButton("Voltar");
		botaoVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoVoltar.setBounds(10, 376, 79, 21);
		botaoVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
				botaoSelecionado = botao.VOLTAR;
			}
		});
		add(botaoVoltar);
	}
	/**
 * Este método determina o próximo estado da aplicação com base no botão selecionado
 * e nos dados inseridos pelo usuário nos campos de texto da interface gráfica.
 *
 * <p>Se o botão "PROXIMO" for selecionado, o método tenta obter os valores dos campos de texto,
 * convertê-los em inteiros e, em seguida, cria uma nova instância da classe {@link TelaTerreno}
 * com esses valores. Caso ocorra uma exceção durante a conversão, o estado não é alterado e o
 * método retorna a tela atual.</p>
 *
 * <p>Se o botão "SALVAR" for selecionado, o método realiza a mesma conversão de valores,
 * mas em vez de mudar para a próxima tela, ele grava as informações em um arquivo de texto
 * chamado "terreno.txt". Novamente, se ocorrer uma exceção, o estado não é alterado.</p>
 *
 * <p>Se o botão "VOLTAR" for selecionado, o método retorna uma nova instância da classe
 * {@link TelaSelecao}.</p>
 *
 * @return Uma instância de {@link EstadoView} que representa o próximo estado da aplicação,
 *         que pode ser a tela de terreno, a tela inicial ou a tela de seleção, dependendo
 *         do botão pressionado. Se houver um erro, retorna a tela atual.
 */
	@Override
	public EstadoView proximoEstado() {
		if (mudarEstado && botaoSelecionado == botao.PROXIMO) {
			boolean erroMapeado = false;
			String mensagemErro = "";
			try {
				int dimensao = Integer.parseInt(campoDimensao.getText());
				int pedras = Integer.parseInt(campoPedras.getText());
				int laranjeiras = Integer.parseInt(campoLaranjeiras.getText());
				int laranjas = Integer.parseInt(campoLaranjas.getText());
				int abacateiros = Integer.parseInt(campoAbacateiros.getText());
				int abacates = Integer.parseInt(campoAbacates.getText());
				int cocos = Integer.parseInt(campoCoco.getText());
				int coqueiros = Integer.parseInt(campoCoqueiros.getText());
				int amoras = Integer.parseInt(campoAmora.getText());
				int amoeira = Integer.parseInt(campoAmoeiras.getText());
				int acerolas = Integer.parseInt(campoAcerolas.getText());
				int pesDeAcerola = Integer.parseInt(campoPesDeAcerola.getText());
				int goiabas = Integer.parseInt(campoGoiabas.getText());
				int goiabeiras = Integer.parseInt(campoGoiabeiras.getText());
				int maracujas = Integer.parseInt(campoMaracujas.getText());
				int maracujasChao = Integer.parseInt(campoMaracujasChao.getText());
				int pBichadas = Integer.parseInt(campoBichada.getText());
				int elementos = laranjeiras + abacateiros + coqueiros + amoeira + pesDeAcerola + goiabeiras + pedras;
				int posicoes = dimensao*dimensao;
				if (maracujasChao > maracujas) {
			    	  erroMapeado = true;
			    	  mensagemErro = "Erro: Número total de maracujás deve ser maior que número de maracujás no chão";
			    	  throw new Exception();
			      }
			      else if (pBichadas < 0 || pBichadas > 100) {
			    	  erroMapeado = true;
			    	  mensagemErro = "Erro: probabilidade dever ser entre 0 e 100";
			    	  throw new Exception();
			      }
			      else if (elementos >= posicoes) {
			    	  erroMapeado = true;
			    	  mensagemErro = "Erro: número de pedras e árvores muito grande, deve haver pelo menos uma célula de grama";
			    	  throw new Exception();
			      }
				return new TelaTerreno(dimensao, pedras, maracujas, maracujasChao, laranjeiras, laranjas, abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeira, amoras, goiabeiras, goiabas, pBichadas);
			}
			catch (Exception e) {
				mudarEstado = false;
				mensagem.setForeground(new Color(255, 0, 0));
				if (e.getClass().equals(NumberFormatException.class)) {
					mensagem.setText("Erro: parâmetro inválido, todos os parâmetros devem ser números inteiros");
				}
				else if (erroMapeado) {
					mensagem.setText(mensagemErro);
				}
				else {
					mensagem.setText("Erro: não foi possível salvar as configurações");
				}
				return this;
			}
		}
		else if (mudarEstado && botaoSelecionado == botao.SALVAR) {
			mudarEstado = false;
			boolean erroMapeado = false;
			String mensagemErro = "";
			try {
				int dimensao = Integer.parseInt(campoDimensao.getText());
				int pedras = Integer.parseInt(campoPedras.getText());
				int laranjeiras = Integer.parseInt(campoLaranjeiras.getText());
				int laranjas = Integer.parseInt(campoLaranjas.getText());
				int abacateiros = Integer.parseInt(campoAbacateiros.getText());
				int abacates = Integer.parseInt(campoAbacates.getText());
				int cocos = Integer.parseInt(campoCoco.getText());
				int coqueiros = Integer.parseInt(campoCoqueiros.getText());
				int amoras = Integer.parseInt(campoAmora.getText());
				int amoeira = Integer.parseInt(campoAmoeiras.getText());
				int acerolas = Integer.parseInt(campoAcerolas.getText());
				int pesDeAcerola = Integer.parseInt(campoPesDeAcerola.getText());
				int goiabas = Integer.parseInt(campoGoiabas.getText());
				int goiabeiras = Integer.parseInt(campoGoiabeiras.getText());
				int maracujas = Integer.parseInt(campoMaracujas.getText());
				int maracujasChao = Integer.parseInt(campoMaracujasChao.getText());
				int pBichadas = Integer.parseInt(campoBichada.getText());
				int mochila = Integer.parseInt(campoMochila.getText());
				int elementos = laranjeiras + abacateiros + coqueiros + amoeira + pesDeAcerola + goiabeiras + pedras;
				int posicoes = dimensao*dimensao;
				if (maracujasChao > maracujas) {
			    	  erroMapeado = true;
			    	  mensagemErro = "Erro: Número total de maracujás deve ser maior que número de maracujás no chão";
			    	  throw new Exception();
			      }
			      else if (pBichadas < 0 || pBichadas > 100) {
			    	  erroMapeado = true;
			    	  mensagemErro = "Erro: probabilidade dever ser entre 0 e 100";
			    	  throw new Exception();
			      }
			      else if (elementos >= posicoes) {
			    	  erroMapeado = true;
			    	  mensagemErro = "Erro: número de pedras e árvores muito grande, deve haver pelo menos uma célula de grama";
			    	  throw new Exception();
			      }
				FileWriter arq = new FileWriter("terreno.txt");
			    PrintWriter gravarArq = new PrintWriter(arq);
			    gravarArq.println("dimensao " + dimensao);
			    gravarArq.println("pedras " + pedras);
			    gravarArq.println("maracuja " + maracujas + " " + maracujasChao);
			    gravarArq.println("laranja " + laranjeiras + " " + laranjas);
			    gravarArq.println("abacate " + abacateiros + " " + abacates);
			    gravarArq.println("coco " + coqueiros + " " + cocos);
			    gravarArq.println("acerola " + pesDeAcerola + " " + acerolas);
			    gravarArq.println("amora " + amoeira + " " + amoras);
			    gravarArq.println("goiaba " + goiabeiras + " " + goiabas);
			    gravarArq.println("bichadas " + pBichadas);
			    gravarArq.println("mochila " + mochila);
			    arq.close();
			    mensagem.setForeground(new Color(0, 0, 0));
			    mensagem.setText("Configurações salvas com sucesso em terreno.txt");
			    return this;
			}
			catch (Exception e) {
				mudarEstado = false;
				mensagem.setForeground(new Color(255, 0, 0));
				if (e.getClass().equals(NumberFormatException.class)) {
					mensagem.setText("Erro: parâmetro inválido, todos os parâmetros devem ser números inteiros");
				}
				else if (erroMapeado) {
					mensagem.setText(mensagemErro);
				}
				else {
					mensagem.setText("Erro: não foi possível salvar as configurações");
				}
				return this;
			}
		}
		else if (mudarEstado && botaoSelecionado == botao.VOLTAR) {
			return new TelaSelecao();
		}
		else {
			mudarEstado = false;
			return this;
		}
	}
}