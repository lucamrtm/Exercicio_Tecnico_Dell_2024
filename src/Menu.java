import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

//Classe que contém o fluxo e os métodos do Menu do sistema.
public class Menu {

    private static int idAtual = 999;// id inicial das cartelas para comecar em 1000(999+1)
    private static int numRodadas = 25;

    /**
     * Inicia o processo de apostas do sistema de apostas.
     * Permite aos usuários adicionar apostadores, podendo escolher números para
     * suas cartelas de aposta
     * ou optar pela surpresinha.
     * 
     * @return O resultado das apostas realizado através da classe ResultadoApostas.
     */
    public static ResultadoApostas iniciaApostas() {

        boolean quit = false; // cria a variavel "quit", que dita quando o usuario encerra o loop do programa.
        ArrayList<Apostador> listaDeApostadores = new ArrayList<>();
        GerenciadorSistemaDeApostas gerenciador = new GerenciadorSistemaDeApostas(listaDeApostadores);
        Scanner scanner = new Scanner(System.in); // cria o scanner pra receber os inputs do usuario.
        loopExterno: while (!quit) {// cria a flag para retornar para o inicio

            boolean encerrarApostas = false;
            // Chama o método imprimeMenuInicial para exibir o menu inicial do Sistema de
            // Apostas e capturar a escolha do usuário.
            // A escolha é armazenada na variável 'quit', indicando se o programa deve ser
            // encerrado (false) ou iniciado (true).
            quit = imprimeMenuInicial(gerenciador, scanner);

            Apostando: // flag para voltar para a tela de adicionar apostadores
            while (!encerrarApostas) {// falso enquanto estiver adicionando apostadores

                System.out.println("|          MENU          |");
                System.out.println(
                        "|------------------------|");
                System.out.println("|1- Novo apostador       |");
                System.out.println("|2- Encerrar             |");
                System.out.println(
                        "|------------------------|");
                System.out.println(
                        "Insira sua escolha:");

                while (!scanner.hasNext("[1-2]")) {
                    System.out.println(
                            "Entrada inválida. Por favor, digite apenas 1 para adicionar um novo apostador ou 2 para encerrar o programa:");
                    scanner.nextLine();
                }
                int entradaDiretaMenu = scanner.nextInt();

                if (entradaDiretaMenu == 2) {

                    quit = true;
                    break loopExterno;

                }
                scanner.nextLine(); // descartando resquícios de entrada do Scanner

                criaNovaAposta(scanner, gerenciador);// chama o método que coleta as informações do apostador e cria as
                                                     // suas apostas

                gerenciador.mostraListaApostadores();

                // logica para encerrar apostas

                System.out.println("|          MENU          |");
                System.out.println(
                        "|------------------------|");
                System.out.println("|1- Encerrar Apostas     |");
                System.out.println("|2- Adicionar Apostador  |");
                System.out.println(
                        "|------------------------|");
                System.out.println(
                        "Insira sua escolha:");

                boolean continuarApostando = capturaRespostaMenu(
                        "Entrada inválida. Por favor, digite apenas 1 para encerrar as apostas ou 2 para adicionar um novo apostador:",
                        scanner);

                if (continuarApostando) {

                    continue Apostando;

                }
                scanner.nextLine(); // descartando resquícios do Scanner

                System.out.println(
                        "Deseja iniciar o sorteio?(s/n)\n(OBS: Não será mais possível adicionar apostadores após o início do sorteio)");
                while (true) {
                    String respostaInicioSorteio = scanner.next().toLowerCase(); // Converte a entrada para minúsculas
                    if (!((respostaInicioSorteio.equals("n") || respostaInicioSorteio.equals("s")))) {
                        System.out.println("Entrada inválida. Por favor, responda com 's' ou 'n':");
                        scanner.nextLine();
                    } else if (respostaInicioSorteio.equals("n")) {
                        System.out.println("Cálculando apostas...");// frase para sinalizar o retorno ao campo de
                                                                    // apostas
                        scanner.nextLine();// descarta o "lixo"(espaço em branco) que fica entre um input do scanner e o
                                           // proximo
                        break;
                    } else {
                        encerrarApostas = true;// encerra a fase de apostas
                        System.out.println("Calculando apostas e iniciando Sorteio...");
                        break;
                    }
                }

            }
            quit = true;
        }
        return new ResultadoApostas(listaDeApostadores, scanner, gerenciador);

    }

    /**
     * Método para criar uma nova aposta.
     * O usuário escolhe se deseja escolher manualmente os números da cartela (opção
     * 1) ou se prefere
     * que os números sejam sorteados aleatoriamente (opção 2). Após a escolha, os
     * números são
     * adicionados à cartela e a cartela é adicionada à lista de apostas.
     * Ao final da criação das apostas de um apostador, volta à tela de adicionar
     * apostadores.
     *
     * @param scanner     O scanner para entrada de dados do usuário.
     * @param gerenciador O gerenciador do sistema de apostas.
     */
    private static void criaNovaAposta(Scanner scanner, GerenciadorSistemaDeApostas gerenciador) {
        boolean mesmoDono = true; // cria a variavel que posteriormente dita se o apostador deseja fazer mais de
                                  // uma
                                  // aposta.
        // Inicia a coleta das informações do apostador:
        System.out.println("Por favor, informe seu nome completo:");

        String userName = ValidadorEntradaUsuario.obterNomeValido(scanner);

        System.out.println("Agora, digite seu CPF:");

        String userCPF = ValidadorEntradaUsuario.obterCpfValido(scanner);

        ArrayList<CartelaDeAposta> apostas = new ArrayList<>();// inicializa a lista de apostas deste apostador

        while (mesmoDono) {// loop que indica que um mesmo apostador continua adicionando novas apostas

            System.out
                    .println(
                            "Você deseja escolher os números da sua cartela(1) ou sorteá-los(2)? (digite 1 ou 2)");
            while (!scanner.hasNext("[1-2]")) {
                System.out.println(
                        "Entrada inválida. Por favor, digite apenas o número 1 para escolher os números ou 2 para sorteá-los:");
                scanner.next();
            }
            int resposta = scanner.nextInt();

            if (resposta == 1) {
                ArrayList<Integer> aposta = new ArrayList<>();

                System.out.println("Otimo! Agora, digite o primeiro dos 5 números da sequencia desejada:");
                int primeiroNumero = ValidadorEntradaUsuario.obterNumeroEntreUmECinquenta(scanner, aposta);
                aposta.add(primeiroNumero);

                System.out.println("Agora o segundo número:");
                int segundoNumero = ValidadorEntradaUsuario.obterNumeroEntreUmECinquenta(scanner, aposta);
                aposta.add(segundoNumero);

                System.out.println("Terceiro número:");
                int terceiroNumero = ValidadorEntradaUsuario.obterNumeroEntreUmECinquenta(scanner, aposta);
                aposta.add(terceiroNumero);

                System.out.println("O quarto:");
                int quartoNumero = ValidadorEntradaUsuario.obterNumeroEntreUmECinquenta(scanner, aposta);
                aposta.add(quartoNumero);

                System.out.println("E agora, o quinto e último número da sua cartela:");
                int quintoNumero = ValidadorEntradaUsuario.obterNumeroEntreUmECinquenta(scanner, aposta);
                aposta.add(quintoNumero);

                CartelaDeAposta novaCartela = new CartelaDeAposta(aposta, geraIdAposta());// cria uma nova
                                                                                          // cartela com o ID
                                                                                          // atualizado

                apostas.add(novaCartela);// adiciona a cartela à lista de apostas deste apostador
                System.out.println("Nova Aposta: " + novaCartela);
            } else {
                CartelaDeAposta novaCartela = new CartelaDeAposta(GerenciadorSistemaDeApostas.Surpresinha(),
                        geraIdAposta());// cria uma nova cartela com uma supresinha
                                        // e o ID atualizado

                System.out.println("Nova Aposta: " + novaCartela);
                apostas.add(novaCartela);// adiciona a cartela à lista de apostas deste apostador
            }

            // logica para gerenciar as apostas deste apostador em especifico
            System.out.println("Deseja fazer mais uma aposta?(s/n)");
            while (true) {
                String respostaContinuarApostando = scanner.next().toLowerCase(); // Converte a entrada para
                                                                                  // minúsculas
                if (!((respostaContinuarApostando.equals("n") || respostaContinuarApostando.equals("s")))) {
                    System.out.println("Entrada inválida. Por favor, responda com 's' ou 'n':");
                    scanner.nextLine();
                } else if (respostaContinuarApostando.equals("s")) {
                    System.out.println("Cálculando apostas...");// frase para sinalizar o retorno ao campo de
                                                                // apostas
                    break;
                } else {
                    mesmoDono = false;// Encerra as apostas deste apostador em especifico
                    break;
                }
            }

        }
        gerenciador.addListaDeApostadores(new Apostador(userName, userCPF, apostas)); // adiciona o apostador na
                                                                                      // lista de apostadores
    }

    /**
     * Imprime o menu inicial do Sistema de Apostas.
     * 
     * @param gerenciador O gerenciador do sistema de apostas.
     * @param scanner     O objeto Scanner para receber inputs do usuário.
     * @return true se o usuário escolheu iniciar o programa, false se escolheu
     *         encerrar.
     */
    private static boolean imprimeMenuInicial(GerenciadorSistemaDeApostas gerenciador, Scanner scanner) {
        System.out.println("Bem-vindo ao nosso Sistema de Apostas!");
        System.out.println("Premiação total de R$" + gerenciador.getPremiacaoString());
        System.out.println("|          MENU          |");// menu inicial
        System.out.println(
                "|------------------------|");
        System.out.println("|1- Início               |");
        System.out.println("|2- Encerrar             |");
        System.out.println(
                "|------------------------|");
        System.out.println(
                "Insira sua escolha:");

        return capturaRespostaMenu(
                "Entrada inválida. Por favor, digite apenas 1 para iniciar ou 2 para encerrar o programa:",
                scanner);
    }

    /**
     * Método para gerar um identificador único para uma nova aposta.
     * 
     * @return O próximo identificador de aposta.
     */
    private static int geraIdAposta() {
        return idAtual += 1;
    }

    /**
     * Método para capturar a resposta do usuário conforme o "Menu" atual.
     * 
     * @param fraseErro A mensagem de erro a ser exibida se a entrada do usuário for
     *                  inválida.
     * @param scanner   O scanner para entrada de dados do usuário.
     * @return true se a resposta do menu for 2, indicando que o programa deve ser
     *         encerrado, caso contrário, false.
     */
    private static boolean capturaRespostaMenu(String fraseErro, Scanner scanner) {
        while (!scanner.hasNext("[1-2]")) {
            System.out.println(fraseErro);
            scanner.next();
        }
        int respostaMenu = scanner.nextInt();

        if (respostaMenu == 2) {

            return true;
        }
        return false;
    }

    /**
     * Realiza a apuração do sorteio, identificando os ganhadores e atualizando a
     * lista de ganhadores.
     * 
     * @param listaDeApostadores A lista de todos os apostadores.
     * @param apostaGanhadora    A sequência dos números sorteados que formam a
     *                           aposta ganhadora.
     * @param gerenciador        O gerenciador do sistema de apostas.
     * @return Um TreeMap contendo os ganhadores e suas respectivas cartelas
     *         premiadas.
     */
    private static TreeMap<Apostador, ArrayList<CartelaDeAposta>> apuracaoSorteio(
            ArrayList<Apostador> listaDeApostadores, ArrayList<Integer> apostaGanhadora,
            GerenciadorSistemaDeApostas gerenciador) {

        // Inicializa um TreeMap que qurdara a lista de ganhadores, já ordenada por
        // ordem alfabética dos nomes.
        TreeMap<Apostador, ArrayList<CartelaDeAposta>> listaDeGanhadores = new TreeMap<>();
        // Logica para encontrar as cartelas com 5 acertos
        // Percorre todos os apostadores para verificar se houveram acertos em suas
        // cartelas
        for (Apostador apostador : listaDeApostadores) {
            for (CartelaDeAposta cartela : apostador.getListaDeApostas()) {
                for (int atualCartela : cartela.getSequencia()) {
                    for (int atualGanhadora : apostaGanhadora) {
                        if (atualCartela == atualGanhadora) {
                            cartela.acertou();

                        }
                    }

                }
                // Chama o método que adiciona os ganhadores e suas cartelas premiadas à lista
                // de ganhadores, da classe GerenciadorSistemaDeApostas
                GerenciadorSistemaDeApostas.adicionaGanhadores(listaDeGanhadores, apostador, cartela, gerenciador);

            }
        }
        // Se não houver ganhadores, chama o método que simula novas rodadas até que
        // haja ganhadores
        if (listaDeGanhadores.isEmpty()) {
            return simulaRodadas(listaDeGanhadores, listaDeApostadores, apostaGanhadora, gerenciador);
        }

        return listaDeGanhadores;

    }

    /**
     * Método que simula a apuração das rodadas do sorteio após a primeira, caso não
     * haja ganhadores.
     * 
     * @param listaDeGanhadores  O TreeMap contendo os ganhadores e suas respectivas
     *                           cartelas premiadas.
     * @param listaDeApostadores A lista de todos os apostadores.
     * @param apostaGanhadora    A lista dos números sorteados que formam a aposta
     *                           ganhadora.
     * @param gerenciador        O gerenciador do sistema de apostas.
     * @return Um TreeMap atualizado com os ganhadores e suas cartelas premiadas,
     *         após simular as rodadas adicionais do sorteio.
     */
    private static TreeMap<Apostador, ArrayList<CartelaDeAposta>> simulaRodadas(
            TreeMap<Apostador, ArrayList<CartelaDeAposta>> listaDeGanhadores,
            ArrayList<Apostador> listaDeApostadores, ArrayList<Integer> apostaGanhadora,
            GerenciadorSistemaDeApostas gerenciador) {

        while (numRodadas > 0) {
            if (listaDeGanhadores.isEmpty()) {
                int novaApostaGanhadora = GerenciadorSistemaDeApostas.updateGanhadora(apostaGanhadora);// atualiza a
                                                                                                       // cartela
                // com o novo numero
                // sorteado

                // Percorre todos os apostadores para verificar se houveram acertos em suas
                // cartelas
                for (Apostador apostador : listaDeApostadores) { // logica para encontrar as cartelas com 5 acertos
                    for (CartelaDeAposta cartela : apostador.getListaDeApostas()) {
                        for (int atualCartela : cartela.getSequencia()) {

                            if (atualCartela == novaApostaGanhadora) {
                                cartela.acertou();

                            }

                        }
                        // Chama o método que adiciona os ganhadores e suas cartelas premiadas à lista
                        // de ganhadores, da classe GerenciadorSistemaDeApostas
                        GerenciadorSistemaDeApostas.adicionaGanhadores(listaDeGanhadores, apostador, cartela,
                                gerenciador);

                    }
                }

            } else {// Se a lista conter vencedores, encerra o loop
                break;
            }
            numRodadas--;

        }
        return listaDeGanhadores;
    }

    /**
     * Método responsável por iniciar o sorteio de uma edição.
     * 
     * @param listaDeApostadores A lista de todos os apostadores.
     * @param scanner            O Scanner utilizado para receber inputs do usuário.
     * @param gerenciador        O gerenciador do sistema de apostas.
     */
    public static void iniciaSorteio(ArrayList<Apostador> listaDeApostadores, Scanner scanner,
            GerenciadorSistemaDeApostas gerenciador) {

        // Sorteia a sequência ganhadora
        ArrayList<Integer> apostaGanhadora = new ArrayList<>(gerenciador.sortearApostaGanhadora());

        // Cria o TreeMap onde são armazenados os ganhadores e suas apostas ganhadoras e
        // chama o método que faz a apuração do sorteio e retorna a lista final de
        // ganhadores. Foi utilizado um TreeMap para manter os apostadores ordenados
        // alfabeticamente.
        TreeMap<Apostador, ArrayList<CartelaDeAposta>> listaDeGanhadores = apuracaoSorteio(listaDeApostadores,
                apostaGanhadora, gerenciador);

        // Obtém a quantidade de cartelas vencedoras
        int qtdCartelasVencedoras = gerenciador.getQtdGanhadores();

        // Verifica se houveram ganhadores
        if (listaDeGanhadores.isEmpty()) {
            System.out.println("Não houveram vencedores nesta edição.");
            System.out.println("Sequencia de números sorteada: " + apostaGanhadora);
            System.out.println("Número de rodadas: " + (25 - numRodadas));
            System.out.println("Quantidade de apostas ganhadoras: " + 0);
            GerenciadorSistemaDeApostas.frequenciaNumerosApostados(listaDeApostadores);
        } else {
            // Caso haja ganhadores
            System.out.println("Sequencia de números sorteada: " + apostaGanhadora);
            if ((25 - numRodadas) == 0) {
                System.out.println("Número de rodadas: " + 1);

            } else {
                System.out.println("Número de rodadas: " + (26 - numRodadas));
            }
            // Exibe as informações requeridas
            System.out.println("Quantidade de apostas ganhadoras: " + qtdCartelasVencedoras);
            gerenciador.mostraGanhadores(listaDeGanhadores);
            GerenciadorSistemaDeApostas.frequenciaNumerosApostados(listaDeApostadores);
            // Inicia a premiação
            iniciaPremiacao(qtdCartelasVencedoras, scanner, gerenciador);
        }

        System.out.println("Obrigado por participar!");
    }

    /**
     * Método responsável por iniciar a premiação dos ganhadores.
     * 
     * @param qtdCartelasVencedoras A quantidade de cartelas vencedoras.
     * @param scanner               O Scanner utilizado para receber inputs do
     *                              usuário.
     * @param gerenciador           O gerenciador do sistema de apostas.
     */
    private static void iniciaPremiacao(int qtdCartelasVencedoras, Scanner scanner,
            GerenciadorSistemaDeApostas gerenciador) {
        System.out.println("Hora da Premiação!");
        System.out.println("Premio Bruto: R$" + gerenciador.getPremiacaoString());
        // Calcula e exibe o prêmio líquido individual após todas as tributações e
        // divisão por cartela ganhadora
        double premioLiquido = (Financeiro.calculaTributos(gerenciador.getPremiacaoDouble()))
                / qtdCartelasVencedoras;
        String premioLiquidoFormatado = String.format("%,.2f", premioLiquido);
        System.out.println("Premio individual após todas a tributações e divisão por cartela ganhadora: R$" +
                premioLiquidoFormatado);
        Financeiro.PagamentoPremio(scanner, premioLiquido);
    }

}
