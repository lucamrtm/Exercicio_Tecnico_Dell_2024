import java.util.ArrayList;

//Classe do objeto CartelaDeAposta
public class CartelaDeAposta {
    private ArrayList<Integer> sequencia; // sequência de aposta desta cartela
    private int idAposta;// identificador desta cartela
    private int numeroAcertos;// variável utilizada na fase de apuração para controlar o numero de acertos
                              // desta cartela

    /**
     * Construtor da classe CartelaDeAposta.
     * 
     * @param sequencia A sequência apostada.
     * @param idAposta  O identificador desta cartela.
     */
    public CartelaDeAposta(ArrayList<Integer> sequencia, int idAposta) {
        this.sequencia = new ArrayList<>(sequencia);
        this.idAposta = idAposta;
        this.numeroAcertos = 0;
    }

    /**
     * Obtém a sequência de números da cartela de aposta.
     *
     * @return ArrayList contendo a sequência de números da cartela.
     */
    public ArrayList<Integer> getSequencia() {
        return sequencia;
    }

    /**
     * Obtém o identificador desta cartela de aposta.
     *
     * @return int contendo o identificador desta cartela de aposta.
     */
    public int getIdAposta() {
        return idAposta;
    }

    /**
     * Obtém o número de acertos desta cartela.
     *
     * @return int contendo o número de acertos desta cartela.
     */
    public int getNumeroAcertos() {
        return numeroAcertos;
    }

    /**
     * Incrementa em 1 o número e acertos desta cartela.
     */
    public void acertou() {
        this.numeroAcertos++;
    }

    /**
     * Sobrepõe o método ToString, retorna uma representação em string da cartela de
     * aposta.
     * 
     * @return Uma string que representa a cartela de aposta.
     */
    @Override
    public String toString() {
        return "\nCartela de aposta: Sequencia= " + sequencia + ", Identificador= " + idAposta;
    }

}
