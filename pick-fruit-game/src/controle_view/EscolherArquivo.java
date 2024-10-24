package controle_view;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.AccessDeniedException;
import java.util.LinkedHashMap;
import javax.swing.*;

public class EscolherArquivo extends EstadoView {
    private enum botao {
        VOLTAR,
        PROXIMO
    }
    private botao botaoSelecionado;
    private JTextField textField;
    private JButton botaoProximo;
    private JButton botaoVoltar;
    private JTextArea msgErro;
    private String caminho;

    EscolherArquivo() {
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

        textField = new JTextField();
        textField.setColumns(10);
        gbc.gridy = 1;
        add(textField, gbc);

        msgErro = new JTextArea();
        msgErro.setForeground(new Color(255, 0, 0));
        msgErro.setWrapStyleWord(true);
        msgErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
        msgErro.setLineWrap(true);
        msgErro.setEditable(false);
        msgErro.setOpaque(false);
        gbc.gridy = 2;
        add(msgErro, gbc);

        botaoProximo = new JButton("Próximo");
        botaoProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        botaoProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!textField.getText().isEmpty()) {
                    mudarEstado = true;
                    caminho = textField.getText();
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
            try {
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
                if (parametrosCompostos.get("maracuja")[1] > parametrosCompostos.get("maracuja")[0]) {
                    throw new Exception("arquivo com formato inválido");
                } else if (parametrosSimples.get("bichadas") < 0 || parametrosSimples.get("bichadas") > 100) {
                    throw new Exception("arquivo com formato inválido");
                } else if (elementos >= posicoes) {
                    throw new Exception("arquivo com formato inválido");
                }
                String[] nomes = {"Jogador 1", "Jogador 2"};
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
                } else if (e.getClass().equals(AccessDeniedException.class)) {
                    msgErro.setText("Erro: acesso ao arquivo negado");
                } else {
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
