package dados;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe responsável por carregar imagens.
 */
public class RolarDados {
    private static final double REDIMENSIONAR_PROPORCAO = 0.3; // Proporção de redimensionamento (30%)

    /**
     * Carrega uma imagem a partir de um caminho e redimensiona para a proporção especificada.
     * @param caminho Caminho da imagem.
     * @return JLabel com a imagem carregada e redimensionada.
     */
    public static JLabel carregarImagens(String caminho) {
        BufferedImage imagem;
        JLabel imagemContainer;
        try {
            // Carrega a imagem
            InputStream imagemCaminho = RolarDados.class.getResourceAsStream(caminho);
            imagem = ImageIO.read(imagemCaminho); // leitura da imagem

            // Redimensiona a imagem para a proporção especificada
            Image imagemRedimensionada = redimensionarImagem(imagem, REDIMENSIONAR_PROPORCAO);

            // Cria o JLabel com a imagem redimensionada
            imagemContainer = new JLabel(new ImageIcon(imagemRedimensionada));
            return imagemContainer; // retorna a imagem redimensionada
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e);
            return null;
        }
    }

    /**
     * Atualiza a imagem de um JLabel dado um caminho, redimensionando-a para a proporção especificada.
     * @param imagemContainer JLabel a ser atualizado.
     * @param caminho Caminho da nova imagem.
     */
    public static void atualizarImagemDado(JLabel imagemContainer, String caminho) {
        BufferedImage imagem;
        try {
            InputStream imagemCaminho = RolarDados.class.getResourceAsStream(caminho);
            imagem = ImageIO.read(imagemCaminho);

            // Redimensiona a imagem para a proporção especificada
            Image imagemRedimensionada = redimensionarImagem(imagem, REDIMENSIONAR_PROPORCAO);

            // Atualiza o JLabel com a imagem redimensionada
            imagemContainer.setIcon(new ImageIcon(imagemRedimensionada));
        } catch (Exception e) {
            System.out.println("Erro ao atualizar imagem do dado: " + e);
        }
    }

    /**
     * Redimensiona uma imagem para a proporção especificada.
     * @param imagem Imagem a ser redimensionada.
     * @param proporcao Proporção para redimensionar (0.0 - 1.0).
     * @return A imagem redimensionada.
     */
    private static Image redimensionarImagem(BufferedImage imagem, double proporcao) {
        int novaLargura = (int) (imagem.getWidth() * proporcao);
        int novaAltura = (int) (imagem.getHeight() * proporcao);
        return imagem.getScaledInstance(novaLargura, novaAltura, Image.SCALE_SMOOTH);
    }
}
