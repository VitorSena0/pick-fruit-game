package controle_view;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import javax.swing.*;

public class TelaEscolherArquivo extends EstadoView {
    private enum botao {
        VOLTAR,
        PROXIMO
    }
    private botao botaoSelecionado;
    private JTextField campoTextoArquivo;
    private JTextField campoTextoNome1;
    private JTextField campoTextoNome2;
    private JButton botaoProximo;
    private JButton botaoVoltar;
    private JTextArea msgErro;
    private String caminho;
    private String[] nomes;

    TelaEscolherArquivo() {
        nomes = new String[2];
    	
    	setBounds(313, 233, 239, 139);
        setLayout(new GridBagLayout()); // Utilizando GridBagLayout para centralização
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir componentes horizontalmente

        // Configurando os componentes
        JLabel lblNewLabel_3 = new JLabel("Digite o caminho do arquivo");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblNewLabel_3, gbc);

        campoTextoArquivo = new JTextField();
        campoTextoArquivo.setColumns(10);
        gbc.gridy = 1;
        add(campoTextoArquivo, gbc);
        
        JLabel lblNewLabel_4 = new JLabel("Digite os nomes dos jogadores");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(lblNewLabel_4, gbc);
        
        campoTextoNome1 = new JTextField();
        campoTextoNome1.setColumns(10);
        gbc.gridy = 3;
        add(campoTextoNome1, gbc);
        
        campoTextoNome2 = new JTextField();
        campoTextoNome2.setColumns(10);
        gbc.gridy = 4;
        add(campoTextoNome2, gbc);

        msgErro = new JTextArea();
        msgErro.setForeground(new Color(255, 0, 0));
        msgErro.setWrapStyleWord(true);
        msgErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
        msgErro.setLineWrap(true);
        msgErro.setEditable(false);
        msgErro.setOpaque(false);
        gbc.gridy = 5;
        gbc.gridx = 0;
        add(msgErro, gbc);

        botaoProximo = new JButton("Próximo");
        botaoProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        botaoProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!campoTextoArquivo.getText().isEmpty()) {
                    mudarEstado = true;
                    caminho = campoTextoArquivo.getText();
                    nomes[0] = campoTextoNome1.getText();
                    nomes[1] = campoTextoNome2.getText();
                    botaoSelecionado = botao.PROXIMO;
                }
            }
        });
        add(botaoProximo, gbc);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        gbc.gridx = 0;
        botaoVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mudarEstado = true;
                botaoSelecionado = botao.VOLTAR;
            }
        });
        add(botaoVoltar, gbc);
    }

    @Override
    public EstadoView proximoEstado() {
        if (mudarEstado && botaoSelecionado == botao.PROXIMO) {
            boolean erroMapeado = false;
        	try {
        		if (nomes[0].isEmpty() || nomes[1].isEmpty()) {
        			erroMapeado = true;
        			throw new Exception("é necessário inserir os nomes dos jogadores");
        		}
        		else if (nomes[0].equals(nomes[1])) {
        			erroMapeado = true;
        			throw new Exception("os nomes dos jogadores precisam ser diferentes");
        		}
                FileReader arq = new FileReader(caminho);
                BufferedReader lerArq = new BufferedReader(arq);
                LinkedHashMap<String, Integer> parametrosSimples = new LinkedHashMap<>(4);
                LinkedHashMap<String, Integer[]> parametrosCompostos = new LinkedHashMap<>(7);
                String linha = lerArq.readLine();
                while (linha != null) {
                    String[] tupla = linha.split(" ");
                    if (tupla.length == 3) {
                        parametrosCompostos.put(tupla[0], new Integer[]{Integer.parseInt(tupla[1]), Integer.parseInt(tupla[2])});
                    } else if (tupla.length == 2) {
                        parametrosSimples.put(tupla[0], Integer.parseInt(tupla[1]));
                    } else {
                    	erroMapeado = true;
                        throw new Exception("arquivo com formato inválido");
                    }
                    linha = lerArq.readLine();
                }
                lerArq.close();
                int elementos = parametrosCompostos.get("laranja")[0] + parametrosCompostos.get("abacate")[0] +
                        parametrosCompostos.get("coco")[0] + parametrosCompostos.get("acerola")[0] +
                        parametrosCompostos.get("amora")[0] + parametrosCompostos.get("goiaba")[0] +
                        parametrosSimples.get("pedras");
                int posicoes = parametrosSimples.get("dimensao") * parametrosSimples.get("dimensao");
                int arvores = parametrosCompostos.get("laranja")[0] + parametrosCompostos.get("abacate")[0] +
                        parametrosCompostos.get("coco")[0] + parametrosCompostos.get("acerola")[0] +
                        parametrosCompostos.get("amora")[0] + parametrosCompostos.get("goiaba")[0];
                int frutasNoChao = parametrosCompostos.get("laranja")[1] + parametrosCompostos.get("abacate")[1] +
                        parametrosCompostos.get("coco")[1] + parametrosCompostos.get("acerola")[1] +
                        parametrosCompostos.get("amora")[1] + parametrosCompostos.get("goiaba")[1] + 
                        parametrosCompostos.get("maracuja")[1];
                int[] listaCampos = {parametrosSimples.get("dimensao"), parametrosSimples.get("pedras"),
                        parametrosCompostos.get("maracuja")[0], parametrosCompostos.get("maracuja")[1],
                        parametrosCompostos.get("laranja")[0], parametrosCompostos.get("laranja")[1],
                        parametrosCompostos.get("abacate")[0], parametrosCompostos.get("abacate")[1],
                        parametrosCompostos.get("coco")[0], parametrosCompostos.get("coco")[1],
                        parametrosCompostos.get("acerola")[0], parametrosCompostos.get("acerola")[1],
                        parametrosCompostos.get("amora")[0], parametrosCompostos.get("amora")[1],
                        parametrosCompostos.get("goiaba")[0], parametrosCompostos.get("goiaba")[1],
                        parametrosSimples.get("bichadas"), parametrosSimples.get("mochila")};
                int gramaLivres = posicoes - elementos;
				if (Arrays.stream(listaCampos).anyMatch(campo -> campo < 0 )){
					erroMapeado = true;
					throw new Exception("arquivo com configurações inválidas, todos os campos devem ser números inteiros positivos");
				}
				else if (parametrosSimples.get("dimensao") < 3) {
					erroMapeado = true;
					throw new Exception("arquivo com configurações inválidas, dimensão do terreno deve ser maior ou igual a 3");
				}
				else if (arvores < 1 && parametrosCompostos.get("maracuja")[0] - parametrosCompostos.get("maracuja")[1] > 0) {
					erroMapeado = true;
					throw new Exception("arquivo com configurações inválidas, deve haver pelo menos uma árvore para gerar os maracujás que não estão no chão");
				}
				else if (parametrosCompostos.get("maracuja")[0] + parametrosCompostos.get("maracuja")[1] < 1) {
					erroMapeado = true;
					throw new Exception("arquivo com configurações inválidas, deve haver pelo menos um maracujá no terreno");
				}
				else if (frutasNoChao > gramaLivres) {
					erroMapeado = true;
					throw new Exception("arquivo com configurações inválidas, existem mais frutas no chão do que células de gramas livre para ocupar");
				}
				else if (posicoes - parametrosSimples.get("pedras") < 3) {
					erroMapeado = true;
					throw new Exception("arquivo com configurações inválidas, devem haver pelo menos 3 células de grama para os jogadores se moverem");
				}
                else if (parametrosCompostos.get("maracuja")[1] > parametrosCompostos.get("maracuja")[0]) {
                	erroMapeado = true;
                    throw new Exception("arquivo com configurações inválidas, número de maracujás deve ser maior ou igual a maracujás no chão");
                } else if (parametrosSimples.get("bichadas") < 0 || parametrosSimples.get("bichadas") > 100) {
                	erroMapeado = true;
                    throw new Exception("arquivo com configurações inválidas, probabilidade deve ser entre 0 e 100");
                } else if (elementos >= posicoes) {
                	erroMapeado = true;
                    throw new Exception("arquivo com configurações inválidas, deve haver pelo menos uma célula de grama livre");
                }
                return new TelaJogo(parametrosSimples.get("dimensao"), parametrosSimples.get("pedras"),
                        parametrosCompostos.get("maracuja")[0], parametrosCompostos.get("maracuja")[1],
                        parametrosCompostos.get("laranja")[0], parametrosCompostos.get("laranja")[1],
                        parametrosCompostos.get("abacate")[0], parametrosCompostos.get("abacate")[1],
                        parametrosCompostos.get("coco")[0], parametrosCompostos.get("coco")[1],
                        parametrosCompostos.get("acerola")[0], parametrosCompostos.get("acerola")[1],
                        parametrosCompostos.get("amora")[0], parametrosCompostos.get("amora")[1],
                        parametrosCompostos.get("goiaba")[0], parametrosCompostos.get("goiaba")[1],
                        parametrosSimples.get("bichadas"), parametrosSimples.get("mochila"), nomes);
            } catch (Exception e) {
                mudarEstado = false;
                if (e.getClass().equals(FileNotFoundException.class)) {
                    msgErro.setText("Erro: arquivo não encontrado");
                }
                else if (e.getClass().equals(AccessDeniedException.class)) {
                    msgErro.setText("Erro: acesso ao arquivo negado");
                }
                else if (erroMapeado) {
                	msgErro.setText("Erro: " + e.getMessage());
                }
                else {
                    msgErro.setText("Erro: arquivo com formato provavelmente invalido");
                }
                return this;
            }
        } else if (mudarEstado && botaoSelecionado == botao.VOLTAR) {
            return new TelaSelecao();
        } else {
            return this;
        }
    }
}
