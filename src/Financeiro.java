import java.util.Scanner;

//Classe que engloba as funções relacionadas à premiação, como tributação, parcelamento, entre outros.
public class Financeiro {

    /**
     * Calcula os impostos retidos em cima da premiação bruta.
     * 
     * @param premiacao O valor da premiação bruta.
     * @return O valor do imposto de renda retido pela Caixa Econômica Federal.
     */
    public static double calculaTributos(double premiacao) {
        double taxaCrescimento = 0.03;
        double valor = premiacao - (premiacao * taxaCrescimento);

        return valor; // Valor do Imposto de Renda retido pela Caixa Econômica Federal, calculado
                      // como
                      // 30% do prêmio total.
    }

    /**
     * Realiza o pagamento do prêmio ao vencedor, permitindo que escolha entre
     * receber
     * o valor à vista ou em 30 parcelas anuais com juros de 3% ao ano.
     * 
     * @param scanner          O objeto Scanner para receber entrada do usuário.
     * @param premiacaoLiquida O valor do prêmio líquido a ser pago.
     */
    public static void PagamentoPremio(Scanner scanner, double premiacaoLiquida) {
        System.out.println("Deseja receber a premição à vista(1) ou em 30 parcelas anuais(2) com juros de 3% ao ano?");

        while (!scanner.hasNext("[1-2]")) {
            System.out.println(
                    "Entrada inválida. Por favor, digite apenas 1 para pagamento único ou 2 para parcelar:");
            scanner.next();
        }
        int respostaPagamentoParcelado = scanner.nextInt();

        if (respostaPagamentoParcelado == 1) {

            String numeroFormatado = String.format("%,.2f", premiacaoLiquida);

            System.out.println("Você pode receber seu prêmio de " + numeroFormatado
                    + " em qualquer casa lotérica credenciada ou nas agências da CAIXA.");
        } else {

            pagamentAnual(premiacaoLiquida);

        }
    }

    /**
     * Realiza o pagamento parcelado do prêmio ao vencedor ao longo de 30 anos com
     * juros de 3% ao ano.
     * 
     * @param premiacaoLiquida O valor do prêmio líquido a ser parcelado.
     */
    private static void pagamentAnual(double premiacaoLiquida) {
        double taxaCrescimento = 0.03; // Taxa anual (3% ao ano)
        int anos = 30; // Número de anos
        double montanteFinal = premiacaoLiquida * Math.pow((1 + taxaCrescimento), anos);

        // Imprime as informações para o ganhador
        System.out.println("Informações sobre o recebimento do prêmio:");
        System.out.println("1. Valor do Prêmio Total: R$" + String.format("%,.2f", premiacaoLiquida));
        System.out.println("2. Condições do Contrato: Parcelamento em " + anos + " anos com juros de 3% ao ano.");
        System.out.println("3. Total a Receber no final dos 30 anos: R$" + String.format("%,.2f", montanteFinal));
        System.out.println(
                "4. Impostos e Deduções: É recomendável buscar orientação de um profissional financeiro para obter mais detalhes sobre este tópico.");
        System.out.println("5. Opções de Recebimento: Consulte o contrato para mais informações.");
        System.out.println("Recomendamos consultar um profissional financeiro para orientações.");
        geraTabelaParcelas(anos, 1 + taxaCrescimento, premiacaoLiquida);
    }

    /**
     * Gera uma tabela com a evolução das parcelas anuais ao longo dos anos,
     * considerando a taxa de juros fornecida.
     * 
     * @param anos             O número total de anos para o parcelamento.
     * @param taxaJuros        A taxa de juros anual aplicada sobre as parcelas.
     * @param premiacaoLiquida O valor do prêmio líquido a ser parcelado.
     */
    public static void geraTabelaParcelas(int anos, double taxaJuros, double premiacaoLiquida) {
        double parcelaAtual = 0;
        double parcelaAnterior = premiacaoLiquida / anos;
        // Imprime a primeira parcela que o usuário irá receber
        System.out.printf("Voce recebera sua primeira parcela de %,.2f em breve.\n",
                premiacaoLiquida / anos);

        // Imprime o cabeçalho da tabela
        System.out.println("Evolução de Juros: ");
        System.out.printf("    %-16sParcela\n", "Ano");

        // Loop para calcular e imprimir o valor das parcelas para cada ano
        for (int ano = 1; ano < anos; ano++) {
            parcelaAtual = parcelaAnterior * taxaJuros;
            parcelaAnterior = parcelaAtual;
            System.out.printf("    %02d%-15s%,.2f\n", ano, "",
                    parcelaAtual);

        }

    }

}
