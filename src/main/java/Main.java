import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Olá");
        System.out.println("Bem-vindo ao Conversor de Moedas!");

        boolean running = true;
        while (running) {
            System.out.println("\nOpções de Conversão:");
            System.out.println("1. USD para BRL");
            System.out.println("2. BRL para USD");
            System.out.println("3. USD para EUR");
            System.out.println("4. EUR para USD");
            System.out.println("5. EUR para BRL");
            System.out.println("6. BRL para EUR");
            System.out.println("0. Sair");

            System.out.print("\nEscolha uma opção: ");
            int option = scanner.nextInt();

            try {
                switch (option) {
                    case 1:
                        convertCurrency("USD", "BRL", scanner);
                        break;
                    case 2:
                        convertCurrency("BRL", "USD", scanner);
                        break;
                    case 3:
                        convertCurrency("USD", "EUR", scanner);
                        break;
                    case 4:
                        convertCurrency("EUR", "USD", scanner);
                        break;
                    case 5:
                        convertCurrency("EUR", "BRL", scanner);
                        break;
                    case 6:
                        convertCurrency("BRL", "EUR", scanner);
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Erro ao obter taxas de câmbio: " + e.getMessage());
            }
        }
    }

    private static void convertCurrency(String from, String to, Scanner scanner) throws IOException, InterruptedException {
        System.out.print("Digite o valor em " + from + ": ");
        double amount = scanner.nextDouble();
        double rate = CheckExchangeRate.getExchangeRate(from, to);
        double convertedAmount = amount * rate;
        System.out.println("Valor convertido: " + convertedAmount + " " + to);
    }

}
