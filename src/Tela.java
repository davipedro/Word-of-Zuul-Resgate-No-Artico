package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * Classe que representa a estruturação GUI do World of Zull - Desafio no artico
 *
 */
public class Tela {

    private JFrame telaPrincipal;

    /* Definindo Lado esquerdo da parte superior */
    private JLabel textoInventario;
    private JLabel informacaoesInventario;

    /* Definindo Centro da parte superior */
    private JLabel imagemCampo;
    private ImageIcon imagem;

    /* Definindo Lado direito da parte superior */
    private JLabel textoAmbiente;
    private JLabel informacaoesAmbiente;
    private JLabel informacoesSaida;

    /* Definindo Parte Inferior */
    private JLabel textoBemVindo;
    private JLabel textoAjuda;
    private JLabel textoLocalAtual;
    private JLabel mensagem;
    private JLabel setinha;
    private JTextField entradaDeDados;

    /* Uso de Padrão Singleton */
    private static Tela instanciaTela;

    private Jogo jogo;

    /**
     * Construtor da Interface Gráfica
     */
    Tela() {
        jogo = new Jogo();
        /* Parte esquerda Superior */
        telaPrincipal = new JFrame("World of Zull - Desafio no artico");
        textoInventario = new JLabel("<html><h2> Inventário: </h2></html>");
        informacaoesInventario = new JLabel(Inventario.olharInventario());
        /* Parte central Superior */
        imagemCampo = new JLabel();
        String imagePath = "img/mapa.png";
        URL imageUrl = getClass().getClassLoader().getResource(imagePath);

        if (imageUrl != null) {
            imagem = new ImageIcon(
                    new ImageIcon(imageUrl).getImage().getScaledInstance(650, 400, Image.SCALE_DEFAULT));
        } else {
            System.out.println("Imagem não encontrada: " + imagePath);
        }

        /* Parte direita Superior */
        textoAmbiente = new JLabel("<html><h2>Ambiente:</h2></html>");
        informacaoesAmbiente = new JLabel(jogo.getAmbienteAtual().getDescricaoLonga());
        informacoesSaida = new JLabel();

        /* Parte Inferior */
        textoBemVindo = new JLabel("Bem-Vindo ao World of Zull - Desafio no artico!");
        textoAjuda = new JLabel("Digite 'ajuda' se você precisar de ajuda.");
        textoLocalAtual = new JLabel(jogo.mostrarComandos());
        mensagem = new JLabel();
        setinha = new JLabel(">");
        entradaDeDados = new JTextField();
        // Evento responsável por tratar a tecla "Enter" do teclado
        entradaDeDados.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    setMensagem("");

                    String[] resultadoJogada = jogo.jogar(entradaDeDados.getText());

                    if (resultadoJogada.length > 0) {
                        switch (resultadoJogada[0]) {
                            case "BANCADA":
                                menuBancada();
                                break;
                            case "VASCULHAR":
                                vasculhar(resultadoJogada[1]);
                                break;
                            case "SAIR_BASE":
                                sairDaBase();
                                break;
                            case "ERRO":
                                comandoErro(resultadoJogada[1]);
                                break;
                            case "SAIR":
                                sair();
                                break;
                            case "AJUDA":
                                ajuda();
                                break;
                            default:
                                break;
                        }
                    }

                    setTela();
                }
            }
        });
        montarTela();
        telaPrincipal.pack();
    }

    /**
     * Esse método permite uma única instanciação de um único objeto da classe e o
     * retorna
     *
     * @return Tela
     */
    public static Tela getInstance() {
        if (instanciaTela == null) {
            instanciaTela = new Tela();
        }
        return instanciaTela;
    }

    /**
     * Método Responsável por montar o layout da Interface Gráfica
     */
    private void montarTela() {
        telaPrincipal.setSize(1000, 500);
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaPrincipal.setLayout(new BorderLayout());

        /* Configurando Lado Esquerdo Superior */
        JPanel campoEsquerdoSuperior = new JPanel();
        campoEsquerdoSuperior.setPreferredSize(new Dimension(250, 350));
        // Aribuindo borda
        campoEsquerdoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
        campoEsquerdoSuperior.setLayout(new BoxLayout(campoEsquerdoSuperior, BoxLayout.Y_AXIS));
        campoEsquerdoSuperior.setBackground(Color.WHITE);
        // Setando formato de texto inventário
        textoInventario.setFont(new Font("Courier New", Font.BOLD, 18));
        campoEsquerdoSuperior.add(textoInventario);
        textoInventario.setFont(new Font("Courier New", Font.BOLD, 18));
        campoEsquerdoSuperior.add(informacaoesInventario);

        /* Configurando Lado central Superior */
        JPanel campoCentralSuperior = new JPanel();
        campoCentralSuperior.setPreferredSize(new Dimension(500, 350));
        campoCentralSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
        campoCentralSuperior.setLayout(new BorderLayout());
        campoCentralSuperior.setBackground(Color.WHITE);
        imagemCampo.setIcon(imagem);
        imagemCampo.setHorizontalAlignment(SwingConstants.CENTER);
        imagemCampo.setVerticalAlignment(SwingConstants.CENTER);
        campoCentralSuperior.add(imagemCampo, BorderLayout.CENTER);

        /* Configurando Lado Direito Superior */
        JPanel campoDireitoSuperior = new JPanel();
        campoDireitoSuperior.setPreferredSize(new Dimension(400, 350));
        campoDireitoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
        campoDireitoSuperior.setLayout(new BoxLayout(campoDireitoSuperior, BoxLayout.Y_AXIS));
        campoDireitoSuperior.setBackground(Color.WHITE);
        campoDireitoSuperior.add(textoAmbiente);

        // Setando formato de informacoes
        informacaoesAmbiente.setBorder(new EmptyBorder(0, 10, 0, 0));
        campoDireitoSuperior.add(informacaoesAmbiente);
        informacoesSaida.setBorder(new EmptyBorder(0, 10, 0, 0));
        campoDireitoSuperior.add(informacoesSaida);

        /* Configurando parte inferior */
        JPanel campoInferior = new JPanel();
        JPanel campoInferiorUltimaLinha = new JPanel();
        campoInferior.setPreferredSize(new Dimension(1000, 150));
        JPanel ultimoEspaco = new JPanel();
        ultimoEspaco.setPreferredSize(new Dimension(1000, 10));
        ultimoEspaco.setBackground(Color.WHITE);
        campoInferior.setLayout(new BoxLayout(campoInferior, BoxLayout.Y_AXIS));
        campoInferiorUltimaLinha.setPreferredSize(new Dimension(1000, 30));
        campoInferiorUltimaLinha.setLayout(new FlowLayout());
        campoInferiorUltimaLinha.setBackground(Color.WHITE);
        /* Setando a parte da entrada de dados (Terminal) */
        campoInferiorUltimaLinha.add(setinha);
        campoInferiorUltimaLinha.add(entradaDeDados);
        /* Setando a borda do campo inferior */
        campoInferior.setBorder(BorderFactory.createLineBorder(Color.black));
        /* Setando os textos da interface */
        campoInferior.setBackground(Color.WHITE);
        textoBemVindo.setBorder(new EmptyBorder(0, 10, 0, 0));
        textoBemVindo.setFont(new Font("Courier New", Font.ITALIC, 14));
        textoAjuda.setBorder(new EmptyBorder(0, 10, 10, 0));
        textoAjuda.setFont(new Font("Courier New", Font.ITALIC, 14));
        textoLocalAtual.setBorder(new EmptyBorder(5, 10, 0, 0));
        textoLocalAtual.setFont(new Font("Courier New", Font.BOLD, 14));
        mensagem.setBorder(new EmptyBorder(0, 10, 0, 0));
        mensagem.setFont(new Font("Courier New", Font.BOLD, 14));
        /* Estilizando campo input da tela */
        entradaDeDados.setBorder(BorderFactory.createLineBorder(Color.white));
        entradaDeDados.setPreferredSize(new Dimension(1200, 30));
        /* Construíndo campo inferior da tela */
        campoInferior.add(textoBemVindo);
        campoInferior.add(textoAjuda);
        campoInferior.add(textoLocalAtual);
        campoInferior.add(mensagem);
        campoInferior.add(campoInferiorUltimaLinha);
        campoInferior.add(ultimoEspaco);
        /* construindo tela principal */
        telaPrincipal.add(campoEsquerdoSuperior, BorderLayout.WEST);
        telaPrincipal.add(campoCentralSuperior, BorderLayout.CENTER);
        telaPrincipal.add(campoDireitoSuperior, BorderLayout.EAST);
        telaPrincipal.add(campoInferior, BorderLayout.SOUTH);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definindo icone da aplicação
        ImageIcon imgTituloJanela = new ImageIcon("img/mapa-casa.png");
        telaPrincipal.setIconImage(imgTituloJanela.getImage());
    }

    /**
     * Método responsável por deixar a janela visível
     */
    public void exibirTela() {
        telaPrincipal.setVisible(true);
        mensagemBoasVindas();
    }

    /**
     * Método que seta localização atual e também atualiza o inventário
     *
     */
    private void setTela() {
        // Texto que gera descrição da atual localização
        textoLocalAtual.setText(jogo.mostrarComandos());
        entradaDeDados.setText("");

        informacaoesAmbiente.setText(jogo.getAmbienteAtual().getDescricaoLonga());
        informacaoesInventario.setText(Inventario.olharInventario());
    }

    /**
     * Método que verifica a opção do usuário de sair da base
     *
     * @return String que retorna a opção escolhida pelo usuário (sim/nao)
     */
    private void sairDaBase() {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "<html> Tem certeza que deseja sair da base? Não poderá voltar.<br> Saia apenas quando estiver pronto! É sua decisão. </html> ",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, TextoHistoria.getFinal());
            System.exit(0);
        }
    }

    private void comandoErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void ajuda() {
        JOptionPane.showMessageDialog(null, TextoHistoria.gerarBilhete(), "Ajuda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void sair() {
        int resposta = JOptionPane.showConfirmDialog(null,
                "<html> Deseja sair do jogo? <br> Ao sair irá perder todo o progresso! </html>",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void vasculhar(String nomeMovel) {
        if (nomeMovel != null) {
            nomeMovel = nomeMovel.toUpperCase();
            if (jogo.getAmbienteAtual().validaMovel(nomeMovel)) {
                String itensMovel = jogo.getAmbienteAtual().getDescricaoItensMovel(nomeMovel);

                if (itensMovel != null) {
                    // Utilize JOptionPane para exibir informações sobre os itens
                    JOptionPane.showMessageDialog(null, "Você procura por itens interessantes:\n" + itensMovel,
                            "Vasculhar",
                            JOptionPane.INFORMATION_MESSAGE);

                    Inventario.adicionarArrayItens(jogo.getAmbienteAtual().transferirItensMovel(nomeMovel));

                    // Exiba uma mensagem usando JOptionPane
                    JOptionPane.showMessageDialog(null, "Você levou todos os itens", "Vasculhar",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Exiba uma mensagem usando JOptionPane
                    JOptionPane.showMessageDialog(null, "Não há nada útil aqui", "Vasculhar",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Exiba uma mensagem usando JOptionPane
                JOptionPane.showMessageDialog(null, "Não existe esse móvel aqui", "Vasculhar",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            // Exiba uma mensagem usando JOptionPane
            JOptionPane.showMessageDialog(null, "Escreva vasculhar + (nome do móvel)", "Vasculhar",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void menuBancada() {
        Bancada bancada = new Bancada();

        // Exibir descrição e comandos usando JOptionPane
        JOptionPane optionPane = new JOptionPane(
                bancada.getDescricao() + "\n",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[] {},
                null);

        JButton botaoCompor = new JButton("Compor");
        JButton botaoAjuda = new JButton("Ajuda");
        JButton botaoVoltar = new JButton("Voltar");

        botaoCompor.addActionListener(e -> acaoCompor(bancada, optionPane));
        botaoAjuda.addActionListener(e -> acaoAjuda(bancada, optionPane));
        botaoVoltar.addActionListener(e -> acaoVoltar(bancada, optionPane));

        optionPane.setOptions(new Object[] { botaoCompor, botaoAjuda, botaoVoltar });

        JDialog dialog = optionPane.createDialog("Menu Bancada");
        dialog.setVisible(true);
    }

    private void acaoCompor(Bancada bancada, JOptionPane optionPane) {
        String nomeItem = JOptionPane.showInputDialog(null, "Digite o nome do item que deseja compor ou 'cancelar'",
                "Compor Item", JOptionPane.PLAIN_MESSAGE);
        if (nomeItem != null) {
            exibirMensagem("Compor Item", bancada.comporItem(nomeItem), JOptionPane.INFORMATION_MESSAGE);
            setTela();
        }
    }

    private void acaoAjuda(Bancada bancada, JOptionPane optionPane) {
        exibirMensagem("Ajuda", bancada.ListaItensCompostos(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void acaoVoltar(Bancada bancada, JOptionPane optionPane) {
        exibirMensagem("Voltar", "Você saiu de perto da bancada", JOptionPane.INFORMATION_MESSAGE);

        // Feche o diálogo
        optionPane.setValue(JOptionPane.CLOSED_OPTION);
    }

    private void exibirMensagem(String titulo, String mensagem, int tipo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }

    public void mensagemBoasVindas() {
        JOptionPane.showMessageDialog(telaPrincipal, TextoHistoria.HISTORIA, "Boas Vindas", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Método que configura o último status do útimo comando digitado
     *
     * @param s texto do útimo status descrito
     */
    public void setMensagem(String s) {
        mensagem.setText(s);
    }

    public void setInformacoesAmbiente(String s) {
        informacaoesAmbiente.setText(s);
    }

    public void setInformacoesSaida(String s) {
        informacoesSaida.setText(s);
    }

    /**
     * Método que encerra o Jogo
     *
     * @param text texto relativo ao status do útimo comando
     */
    public void sair(String text) {
        JOptionPane.showMessageDialog(telaPrincipal, text, "Fim de Jogo", JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
}