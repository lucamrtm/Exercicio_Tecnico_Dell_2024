//Classe principal, chama os métodos necessários para iniciar as apostas e o sorteio.
public class App {

    public static void main(String[] args) {
        // Chamando o método estático iniciaApostas() da classe Menu para iniciar o
        // processo de apostas. O resultado dessas apostas é armazenado na variável
        // 'resultadoApostas'.
        ResultadoApostas resultadoApostas = Menu.iniciaApostas();
        // Chama o método estático iniciaSorteio() da classe Menu, passando a lista de
        // apostadores, o scanner e o gerenciador obtidos do objeto resultadoApostas.
        Menu.iniciaSorteio(resultadoApostas.getListaDeApostadores(), resultadoApostas.getScanner(),
                resultadoApostas.getGerenciador());
    }
}
