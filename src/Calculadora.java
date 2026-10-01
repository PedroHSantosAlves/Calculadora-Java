import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Calculadora de console simples.
 * Faz soma, subtração, multiplicação, divisão e potência,
 * trata erros de digitação e guarda um histórico das operações.
 */
public class Calculadora {

    // Lista que guarda o resultado de cada operação feita
    private static final List<String> historico = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("=== CALCULADORA EM JAVA ===");

        do {
            exibirMenu();
            opcao = lerOpcao(scanner);

            switch (opcao) {
                case 1, 2, 3, 4, 5 -> executarOperacao(opcao, scanner);
                case 6 -> exibirHistorico();
                case 0 -> System.out.println("Encerrando a calculadora. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    // ---------- MENU ----------

    private static void exibirMenu() {
        System.out.println();
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Soma");
        System.out.println("2 - Subtração");
        System.out.println("3 - Multiplicação");
        System.out.println("4 - Divisão");
        System.out.println("5 - Potência");
        System.out.println("6 - Ver histórico");
        System.out.println("0 - Sair");
    }

    // ---------- LEITURA DE DADOS ----------

    // Lê a opção do menu; se o usuário digitar algo que não é número, retorna -1
    private static int lerOpcao(Scanner scanner) {
        System.out.print("Opção: ");
        String entrada = scanner.nextLine().trim();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Pede um número até o usuário digitar um valor válido (aceita vírgula ou ponto)
    private static double lerNumero(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }
    }

    // ---------- OPERAÇÕES ----------

    private static void executarOperacao(int opcao, Scanner scanner) {
        double a = lerNumero(scanner, "Primeiro número: ");
        double b = lerNumero(scanner, "Segundo número: ");

        try {
            double resultado;
            String simbolo;

            switch (opcao) {
                case 1 -> { resultado = somar(a, b); simbolo = "+"; }
                case 2 -> { resultado = subtrair(a, b); simbolo = "-"; }
                case 3 -> { resultado = multiplicar(a, b); simbolo = "*"; }
                case 4 -> { resultado = dividir(a, b); simbolo = "/"; }
                default -> { resultado = potencia(a, b); simbolo = "^"; }
            }

            String conta = formatar(a) + " " + simbolo + " " + formatar(b) + " = " + formatar(resultado);
            System.out.println("Resultado: " + conta);
            historico.add(conta);

        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static double somar(double a, double b) {
        return a + b;
    }

    public static double subtrair(double a, double b) {
        return a - b;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public static double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("não é possível dividir por zero.");
        }
        return a / b;
    }

    public static double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    // ---------- HISTÓRICO E FORMATAÇÃO ----------

    private static void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("O histórico está vazio.");
            return;
        }
        System.out.println("--- Histórico ---");
        for (int i = 0; i < historico.size(); i++) {
            System.out.println((i + 1) + ". " + historico.get(i));
        }
    }

    // Mostra 5.0 como "5" e 2.5 como "2.5" para ficar mais bonito
    private static String formatar(double numero) {
        if (numero == (long) numero) {
            return String.valueOf((long) numero);
        }
        return String.valueOf(numero);
    }
}
