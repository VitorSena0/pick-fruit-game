package controle_view;

import java.awt.*;

import javax.swing.JPanel;

import dados.*;

/**
 * A classe {@code TelaTerreno} representa a tela de visualização do terreno no jogo "Cata Fruta".
 * Esta tela exibe o estado do terreno, incluindo as dimensões e a distribuição de frutas e pedras.
 * 
 * <p>A classe utiliza um painel {@link Janela} para desenhar o terreno com base nos parâmetros fornecidos.</p>
 */
public class TelaTerreno extends EstadoView {

    /**
     * Construtor da classe {@code TelaTerreno}. Inicializa a interface gráfica da tela de terreno
     * com as configurações fornecidas.
     * 
     * @param dimensao          A dimensão do terreno.
     * @param pedras            O número de pedras no terreno.
     * @param maracujas        O número de maracujás no terreno.
     * @param maracujas_chao   O número de maracujás no chão.
     * @param laranjeiras      O número de laranjeiras no terreno.
     * @param laranjas         O número de laranjas no terreno.
     * @param abacateiros      O número de abacateiros no terreno.
     * @param abacates         O número de abacates no terreno.
     * @param coqueiros        O número de coqueiros no terreno.
     * @param cocos            O número de cocos no terreno.
     * @param pesDeAcerola     O número de pés de acerola no terreno.
     * @param acerolas         O número de acerolas no terreno.
     * @param amoeiras         O número de ameixeiras no terreno.
     * @param amoras           O número de amoras no terreno.
     * @param goiabeiras       O número de goiabeiras no terreno.
     * @param goiabas          O número de goiabas no terreno.
     * @param probabidade_bichadas A probabilidade de bichadas no terreno.
     */
    TelaTerreno(int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas,
                int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas,
                int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas) {
          
        setBounds(0, 0, 986, 732);
        setLayout(new BorderLayout());

        // Cria o painel principal para o jogo
        DadosInterface dados = new DadosInterface();
        Janela janela = new Janela(600, dimensao, pedras, maracujas, maracujas_chao, laranjeiras, laranjas,
                abacateiros, abacates, coqueiros, cocos, pesDeAcerola, acerolas, amoeiras, amoras,
                goiabeiras, goiabas, probabidade_bichadas, dados);
        
        // Cria a interface de dados
        
        // Painel para manter a interface de dados à direita e centralizada
        JPanel panelDados = new JPanel(new GridBagLayout());
        panelDados.add(dados);
        
        // Adiciona o painel de dados ao lado direito
        add(panelDados, BorderLayout.EAST);
        add(janela, BorderLayout.CENTER);

        revalidate();  // Atualiza o layout
        repaint();     // Redesenha o componente


    }

    /**
     * Este método determina o próximo estado da aplicação. No caso da tela de terreno,
     * não há mudança de estado, portanto, ele retorna a própria instância.
     * 
     * @return Uma instância de {@link EstadoView} que representa o próximo estado da aplicação (neste caso, a própria tela).
     */
    @Override
    public EstadoView proximoEstado() {
        return this;
    }
}
