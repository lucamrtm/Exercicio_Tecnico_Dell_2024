import java.util.ArrayList;

import java.util.Map;
import java.util.Random;

import java.util.TreeMap;

//Esta classe engloba as operações relacionadas as apostas, como métodos que fazem a manipulação das listas de apostas, gerar a "surpresinha" para a cartela com números aleatórios, lógica de apuração, entre outros.

public class GerenciadorSistemaDeApostas {
    // Variáveis da classe
    ArrayList<Apostador> listaDeApostadores = new ArrayList<>();// inicializando a lista de apostadores
    int qtdGanhadores;// quantidade de ganhadores
    double premiacao;// valor bruto da premiação total

    /**
     * Construtor para a classe GerenciadorSistemaDeApostas.
     * 
     * @param listaDeApostadores A lista de apostadores para ser gerenciada.
     */
    public GerenciadorSistemaDeApostas(ArrayList<Apostador> listaDeApostadores) {
        this.listaDeApostadores = listaDeApostadores;
        this.qtdGanhadores = 0;// inicializa sempre em 0
        this.premiacao = 8857143;
    }

    /**
     * Método para gerar uma "surpresinha", com uma sequência de 5 números
     * aleatórios de 1 a 50.
     * 
     * @return Uma lista contendo os 5 números da surpresinha.
     */
    public static ArrayList<Integer> Surpresinha() { // metodo surpresinha para gerar uma sequencia de 5 numeros
                                                     // (aposta) aleatoriamente
        ArrayList<Integer> apostaSurpresa = new ArrayList<>();
        Random random = new Random();

        // Gera os 5 numeros da aposta de 1 a 50
        for (int i = 0; i < 5; i++) {
            int numero = random.nextInt(50) + 1;
            apostaSurpresa.add(numero);
        }

        return apostaSurpresa;
    }

    /**
     * Define a quantidade de ganhadores.
     * Método setter para definir a quantidade de ganhadores.
     * 
     * @param qtdGanhadores O novo nome do usuário.
     */
    public void setQtdGanhadores(int qtdGanhadores) {
        this.qtdGanhadores = qtdGanhadores;
    }

    /**
     * Retorna a quantidade de ganhadores do prêmio.
     * 
     * @return A quantidade de ganhadores.
     */
    public int getQtdGanhadores() {
        return qtdGanhadores;
    }

    /**
     * Sorteia uma aposta ganhadora.
     * Este método gera uma aposta aleatória utilizando o método Surpresinha,
     * contido também nesta classe.
     * 
     * @return A aposta ganhadora sorteada.
     */
    public ArrayList<Integer> sortearApostaGanhadora() {
        ArrayList<Integer> apostaGanhadora = new ArrayList<>(GerenciadorSistemaDeApostas.Surpresinha());// gera a
                                                                                                        // apostaganhadora
        return apostaGanhadora;
    }

    /**
     * Retorna o valor do prêmio formatado como uma string em Real (R$).
     * 
     * @return O valor do prêmio formatado como uma string.
     */
    public String getPremiacaoString() {

        String numeroFormatado = String.format("%,.2f", this.premiacao);

        return numeroFormatado;
    }

    /**
     * Retorna o valor do prêmio como um double.
     * 
     * @return O valor do prêmio como double.
     */
    public double getPremiacaoDouble() {

        return this.premiacao;
    }

    /**
     * Atualiza a sequência vencedora adicionando um número aleatório que ainda não
     * esteja presente nela.
     * 
     * @param sequenciaAtual A sequência atual de números vencedores.
     * @return O novo número aleatório adicionado à sequência vencedora.
     */
    public static int updateGanhadora(ArrayList<Integer> sequenciaAtual) {
        // Cria um objeto Random para gerar números aleatórios
        Random random = new Random();
        int numero;
        // Loop para gerar um número aleatório que ainda não esteja na sequência
        // vencedora
        while (true) {
            numero = random.nextInt(50) + 1;

            if (!(sequenciaAtual.contains(numero))) {
                sequenciaAtual.add(numero);
                break;
            }
        }

        return numero;

    }

    /**
     * Retorna a lista de apostadores registradas no sistema até o momento.
     * 
     * @return A lista de apostadores cadastrados.
     */
    public ArrayList<Apostador> getListaDeApostadores() {
        return listaDeApostadores;
    }

    /**
     * Adiciona um novo apostador à lista de apostadores cadastrados no sistema.
     * 
     * @param apostador O apostador a ser adicionado à lista.
     */
    public void addListaDeApostadores(Apostador apostador) {
        this.listaDeApostadores.add(apostador);
    }

    /**
     * Mostra na saída a lista de apostadores cadastrados no sistema, incluindo
     * seus nomes, CPFs e listas de apostas.
     */
    public void mostraListaApostadores() {
        System.out.println("Lista de Apostadores: ");
        System.out.println(
                "==============================================================================================================================");
        for (Apostador apostador : listaDeApostadores) {

            System.out.println("Nome:" + apostador.getNome());
            System.out.println("CPF:" + apostador.getCPF());
            System.out.println("Apostas:" + apostador.getListaDeApostas());
            System.out.println(
                    "==============================================================================================================================");
        }
    }

    /**
     * Calcula e exibe a frequência dos números apostados pelos jogadores.
     *
     * @param listaDeApostadores A lista de apostadores.
     */
    public static void frequenciaNumerosApostados(ArrayList<Apostador> listaDeApostadores) {
        // TreeMap para armazenar a frequência dos números apostados em ordem,
        // inicialmente ordenada pelos números
        TreeMap<Integer, Integer> frequenciaApostas = new TreeMap<>();

        // Itera sobre todos os apostadores na lista
        for (Apostador apostador : listaDeApostadores) {
            // Itera sobre todas as cartelas deste apostador
            for (CartelaDeAposta cartela : apostador.getListaDeApostas()) {
                // Itera sobre cada número na sequência da cartela
                for (int key : cartela.getSequencia()) {
                    // Verifica se o número já foi apostado antes
                    if (frequenciaApostas.containsKey(key)) {
                        // Se sim, incrementa a frequência
                        int valorAtual = frequenciaApostas.get(key);
                        frequenciaApostas.put(key, valorAtual + 1);
                    } else {
                        // Se não, adiciona o número à lista de frequência com valor inicial 1
                        frequenciaApostas.put(key, 1);
                    }

                }

            }
        }
        // Exibe os números apostados e suas frequências, ordenados por frequência de
        // maneira decrescente
        System.out.println("Número apostado:        Frequência apostas:");
        frequenciaApostas.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(String.format("%02d", entry.getKey())
                        + "                              " + String.format("%02d", entry.getValue())));

    }

    /**
     * Mostra as informações dos ganhadores do sorteio, incluindo seus nomes, CPFs e
     * cartelas ganhadoras.
     *
     * @param listaDeGanhadores Um TreeMap contendo os ganhadores e uma lista com
     *                          suas respectivas cartelas ganhadoras.
     */
    public void mostraGanhadores(TreeMap<Apostador, ArrayList<CartelaDeAposta>> listaDeGanhadores) {
        // Itera sobre cada chave (apostador) no TreeMap
        for (Apostador key : listaDeGanhadores.keySet()) {
            System.out.println(
                    ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            // Imprime as informações do apostador (nome e CPF)
            System.out.println("Informações do apostador: ");
            System.out.println("Nome: " + key.getNome());
            System.out.println("CPF: " + key.getCPF());
            System.out.println("Cartelas ganhadoras: ");
            // Itera sobre cada valor (lista de cartelas ganhadoras) associado à chave
            for (CartelaDeAposta value : listaDeGanhadores.get(key)) {
                // Imprime cada cartela ganhadora
                System.out.println(value);

            }
            System.out.println(
                    ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        }

    }

    /**
     * Adiciona um ganhador à lista de ganhadores, juntamente com sua cartela
     * ganhadora.
     * Atualiza a quantidade total de ganhadores no gerenciador do sistema de
     * apostas.
     *
     * @param listaDeGanhadores Um TreeMap contendo os ganhadores e suas respectivas
     *                          cartelas ganhadoras.
     * @param apostador         O objeto Apostador do ganhador.
     * @param cartela           A cartela de aposta ganhadora do Apostador.
     * @param gerenciador       O gerenciador do sistema de apostas.
     */
    public static void adicionaGanhadores(TreeMap<Apostador, ArrayList<CartelaDeAposta>> listaDeGanhadores,
            Apostador apostador, CartelaDeAposta cartela, GerenciadorSistemaDeApostas gerenciador) {
        if (!(listaDeGanhadores.containsKey(apostador)) && cartela.getNumeroAcertos() >= 5) {// se ainda nao esta no
                                                                                             // dicionario, adiciona e
                                                                                             // cria uma lista com a
                                                                                             // aposta
            ArrayList<CartelaDeAposta> apostaAdicionada = new ArrayList<>();
            apostaAdicionada.add(cartela);
            listaDeGanhadores.put(apostador, apostaAdicionada);
            // Incrementa a quantidade total de ganhadores no gerenciador do sistema de
            // apostas
            gerenciador.setQtdGanhadores(gerenciador.getQtdGanhadores() + 1);

        } else if (listaDeGanhadores.containsKey(apostador) && cartela.getNumeroAcertos() >= 5) {// se ja existir no
                                                                                                 // dicionario, atualiza
                                                                                                 // a lista com a nova
                                                                                                 // aposta
            ArrayList<CartelaDeAposta> apostasCombinadas = new ArrayList<>();
            apostasCombinadas.addAll(listaDeGanhadores.get(apostador));
            apostasCombinadas.add(cartela);
            listaDeGanhadores.put(apostador, apostasCombinadas);
            // Incrementa a quantidade total de ganhadores no gerenciador do sistema de
            // apostas
            gerenciador.setQtdGanhadores(gerenciador.getQtdGanhadores() + 1);

        }

    }

}
