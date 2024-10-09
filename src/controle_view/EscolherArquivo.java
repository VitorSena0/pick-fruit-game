package controle_view;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
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
	private String caminho;
	EscolherArquivo() {
		setBounds(313, 272, 239, 100);
		setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Digite o caminho do arquivo");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 5, 219, 23);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(10, 38, 219, 19);
		add(textField);
		textField.setColumns(10);
		
		botaoProximo = new JButton("Próximo");
		botaoProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoProximo.setBounds(130, 69, 99, 21);
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
		add(botaoProximo);
		
		botaoVoltar = new JButton("Voltar");
		botaoVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botaoVoltar.setBounds(10, 69, 99, 21);
		botaoVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudarEstado = true;
				botaoSelecionado = botao.VOLTAR;
			}
		});
		add(botaoVoltar);
	}
	@Override
	public EstadoView proximoEstado() {
		if (mudarEstado && botaoSelecionado == botao.PROXIMO) {
			try {
			      FileReader arq = new FileReader(caminho);
			      BufferedReader lerArq = new BufferedReader(arq);
			      LinkedHashMap<String, Integer> parametrosSimples = new LinkedHashMap<String, Integer>(4);
			      LinkedHashMap<String, Integer[]> parametrosCompostos = new LinkedHashMap<String, Integer[]>(7);
			      String linha = lerArq.readLine();
			      while (linha != null) {
			    	  String[] tupla = linha.split(" ");
			    	  if (tupla.length == 3) {
			    		 parametrosCompostos.put(tupla[0], new Integer[]{Integer.parseInt(tupla[1]), Integer.parseInt(tupla[2])});
			    	  }
			    	  else if (tupla.length == 2) {
			    		  parametrosSimples.put(tupla[0], Integer.parseInt(tupla[1]));
			    	  }
			    	  else {
			    		  throw new Exception("formato inválido");
			    	  }
			    	  linha = lerArq.readLine();
			      }
			      lerArq.close();
			      return new TelaTerreno(parametrosSimples.get("dimensao"), parametrosSimples.get("pedras"), parametrosCompostos.get("maracuja")[0], parametrosCompostos.get("maracuja")[1], parametrosCompostos.get("laranja")[0], parametrosCompostos.get("laranja")[1], parametrosCompostos.get("abacate")[0], parametrosCompostos.get("abacate")[1], parametrosCompostos.get("coco")[0], parametrosCompostos.get("coco")[1], parametrosCompostos.get("acerola")[0], parametrosCompostos.get("acerola")[1], parametrosCompostos.get("amora")[0], parametrosCompostos.get("amora")[1], parametrosCompostos.get("goiaba")[0], parametrosCompostos.get("goiaba")[1], parametrosSimples.get("dimensao"));
			}
			catch (Exception e) {
				mudarEstado = false;
				return this;
			}
		}
		else if (mudarEstado && botaoSelecionado == botao.VOLTAR) {
			return new TelaSelecao();
		}
		else {
			return this;
		}
	}
}