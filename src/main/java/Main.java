import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Lista de conversões já realizadas.
    private static final List<String> conversionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*****************************************");
        System.out.println("\tBem vindo ao Conversor de moedas!");
        boolean loop = true;
        while (loop) {
            System.out.println("\nOpções de Conversão:");
            System.out.println("1. USD para BRL           2. BRL para USD");
            System.out.println("3. USD para EUR           4. EUR para USD");
            System.out.println("5. EUR para BRL           6. BRL para EUR");
            System.out.println("7. BRL para ARS           8. ARS para BRL");
            System.out.println("9. Histórico              0. Sair");
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
                    case 7:
                        convertCurrency("BRL", "ARS", scanner);
                        break;
                    case 8:
                        convertCurrency("ARS", "BRL", scanner);
                        break;
                    case 9:
                        showConversionHistory();
                        break;
                    case 0:
                        loop = false;
                        System.out.println("Obrigado por usar o nosso app!");
                        System.out.println("Encerrando processo.");
                        break;
                    default:
                        System.out.println("Opção inválida, por favor insira uma das opções presentes na lista.");
                        break;
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Erro ao obter taxas de câmbio: " + e.getMessage());
            }
        }
        scanner.close();
    }
    //Método para realizar a conversão
    private static void convertCurrency(String from, String to, Scanner scanner) throws IOException, InterruptedException {
        System.out.print("Digite o valor em " + from + ": ");
        //valor a ser convertido
        double amount = scanner.nextDouble();
        //taxa de conversão
        double rate = CheckExchangeRate.getExchangeRate(from, to);
        //valor convertido
        double convertedAmount = amount * rate;
        //formatar os valores para apenas 2 números após a casa decimal.
        String formatedConvertedAmount = String.format("%.2f", convertedAmount);
        System.out.println("Valor convertido: " + formatedConvertedAmount + " " + to);

        // Variável que recebe os valores da conversão atual para ser guardado no histórico
        String conversionRecord = "De " + amount + " " + from + " para " + convertedAmount + " " + to + " (Taxa: " + rate + ")";
        addConversionToHistory(conversionRecord);
    }
    //Adicionar a conversão na lista
    private static void addConversionToHistory(String record) {
        conversionHistory.add(record);
    }
    //mostrar a lista de conversões já realizadas
    private static void showConversionHistory() {
        System.out.println("Histórico de Conversões:");
        for (String conversion : conversionHistory) {
            System.out.println(conversion);
        }
    }
}

