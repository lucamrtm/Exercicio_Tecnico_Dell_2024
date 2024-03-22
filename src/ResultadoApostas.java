import java.util.ArrayList;
import java.util.Scanner;

//Classe criada para armazenar os dados nescessarios para avançar para a fase de sorteio.
public class ResultadoApostas {
    private ArrayList<Apostador> listaDeApostadores; // lista de apostadores
    private Scanner scanner; // Scanner para entrada de dados do usuário
    private GerenciadorSistemaDeApostas gerenciador; // O gerenciador do sistema de apostas.

    /**
     * Construtor da classe ResultadoApostas.
     * 
     * @param listaDeApostadores A lista de apostadores.
     * @param scanner            O scanner para entrada de dados.
     * @param gerenciador        O gerenciador do sistema de apostas.
     */
    public ResultadoApostas(ArrayList<Apostador> listaDeApostadores, Scanner scanner,
            GerenciadorSistemaDeApostas gerenciador) {
        this.listaDeApostadores = listaDeApostadores;
        this.scanner = scanner;
        this.gerenciador = gerenciador;
    }

    /**
     * Retorna a lista de apostadores contida no objeto ResultadoApostas.
     * 
     * @return A lista de apostadores.
     */
    public ArrayList<Apostador> getListaDeApostadores() {
        return this.listaDeApostadores;
    }

    /**
     * Retorna o objeto Scanner utilizado no objeto ResultadoApostas.
     * 
     * @return O objeto Scanner.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Retorna o gerenciador do sistema de apostas contido no objeto
     * ResultadoApostas.
     * 
     * @return O gerenciador do sistema de apostas.
     */
    public GerenciadorSistemaDeApostas getGerenciador() {
        return gerenciador;
    }

}
