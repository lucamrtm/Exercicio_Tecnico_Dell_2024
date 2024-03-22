import java.util.ArrayList;
import java.util.Scanner;

//Classe criada para garantir a válidade das entradas do usuário.
public class ValidadorEntradaUsuario {
    /**
     * Método para garantir que o usuário retorne um número entre 1 e 50 e verifica
     * se ele já não foi apostado nesta cartela.
     * 
     * @param scanner O objeto Scanner para ler a entrada do usuário.
     * @param aposta  A lista de números já apostados nesta cartela.
     * @return Um número válido entre 1 e 50 que ainda não foi apostado nesta
     *         cartela.
     */
    public static int obterNumeroEntreUmECinquenta(Scanner scanner, ArrayList<Integer> aposta) {
        int numero;
        while (true) {
            String input = scanner.next();
            if (input.matches("[1-9]|[1-4][0-9]|50") && !(aposta.contains(Integer.parseInt(input)))) {
                numero = Integer.parseInt(input);
                break;
            } else {
                System.out.println(
                        "Entrada inválida. Por favor, digite um número entre 1 e 50 que ainda não tenha sido apostado nesta cartela.");
            }
        }
        return numero;
    }

    /**
     * Método para garantir que o usuário retorne um nome válido (maior que 3, menor
     * que 30 e contendo apenas letras e espaços).
     * 
     * @param scanner O objeto Scanner para ler a entrada do usuário.
     * @return Um nome válido inserido pelo usuário.
     */
    public static String obterNomeValido(Scanner scanner) {
        String nome;
        while (true) {
            String input = scanner.nextLine();
            boolean apenasLetras = true;
            for (char c : input.toCharArray()) {
                if (!Character.isLetter(c) && c != ' ') {
                    apenasLetras = false;
                    break;
                }
            }
            if (apenasLetras && input.length() > 3 && input.length() <= 30) {
                nome = input;
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite um nome válido.");
            }
        }
        return nome;
    }

    /**
     * Método para garantir que o usuário retorne apenas números, representando um
     * CPF válido(apenas números e exatamente 11 dígitos).
     * 
     * @param scanner O objeto Scanner para ler a entrada do usuário.
     * @return Um CPF válido inserido pelo usuário.
     */
    public static String obterCpfValido(Scanner scanner) {
        String CPF;
        while (true) {
            String input = scanner.next();
            if (input.matches("[0-9]+") && input.length() == 11) {
                CPF = input;
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite um CPF válido.");
            }
        }
        return CPF;
    }

}
