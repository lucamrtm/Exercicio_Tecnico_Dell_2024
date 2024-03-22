import java.util.ArrayList;

//Classe para o objeto Apostador, que implementa Comparable para permitir o ordenamento dos nomes em ordem alfabética.
public class Apostador implements Comparable<Apostador> {

    private String nome;
    private String CPF;

    // Lista de listas para armazenar as apostas deste Apostador
    private ArrayList<CartelaDeAposta> listaDeApostas = new ArrayList<>();

    /**
     * Construtor da classe Apostador.
     * 
     * @param nome    O nome do apostador.
     * @param cpf     O CPF do apostador.
     * @param apostas A lista de apostas do apostador.
     */
    public Apostador(String nome, String cpf, ArrayList<CartelaDeAposta> apostas) {
        this.nome = nome;
        this.CPF = cpf;
        listaDeApostas = new ArrayList<>(apostas);// Cria uma nova lista com as apostas enviadas como parametro
    }

    /**
     * Retorna o nome do apostador.
     * 
     * @return O nome do apostador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sobrepõe o método CompareTo que compara este apostador com outro apostador
     * com base no nome,
     * utilizado para
     * manter a ordem alfabética dentro do TreeMap que armazena os ganhadores.
     * 
     * @param next O apostador a ser comparado.
     * @return Um valor negativo se este apostador preceder o outro no ordenamento,
     *         zero se forem iguais e um valor positivo se este apostador seguir o
     *         outro no ordenamento.
     */
    @Override
    public int compareTo(Apostador next) {

        return this.nome.compareTo(next.nome);
    }

    /**
     * Obtém o CPF deste apostador.
     * 
     * @return O CPF do apostador.
     */
    public String getCPF() {
        return CPF;
    }

    /**
     * Obtém a lista de apostas feitas por este apostador.
     * 
     * @return A lista de apostas feitas pelo apostador.
     */
    public ArrayList<CartelaDeAposta> getListaDeApostas() {
        return listaDeApostas;
    }

    /**
     * Sobrepõe o método ToString, e retorna uma representação em formato de string
     * do objeto Apostador, contendo o nome, CPF e a lista de apostas.
     * 
     * @return Uma string representando o objeto Apostador.
     */
    @Override
    public String toString() {
        return "Apostador [nome=" + nome + ", CPF=" + CPF + ", listaDeApostas=" + listaDeApostas + "]";
    }

}
