import io.github.cdimascio.dotenv.Dotenv;
import service.CurrencyService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
            String apiKey = dotenv.get("API_KEY");

            CurrencyService currencyService = new CurrencyService(apiKey);
            Scanner scanner = new Scanner(System.in);

            boolean firstInteraction = true;

            while (true) {
                if (firstInteraction) {
                    System.out.println("\nBem-vindo à calculadora de conversão de moedas!");
                    System.out.println("Moedas disponíveis para conversão:");
                    System.out.println("ARS - Peso argentino");
                    System.out.println("BOB - Boliviano boliviano");
                    System.out.println("BRL - Real brasileiro");
                    System.out.println("CLP - Peso chileno");
                    System.out.println("COP - Peso colombiano");
                    System.out.println("USD - Dólar americano");
                    System.out.println("Digite 'sair' para encerrar a calculadora.");
                    firstInteraction = false;
                }

                System.out.print("\nDigite o código da moeda de origem: ");
                String fromCurrency = scanner.nextLine().toUpperCase();
                if (fromCurrency.equals("SAIR")) break;

                System.out.print("Digite o código da moeda de destino: ");
                String toCurrency = scanner.nextLine().toUpperCase();
                if (toCurrency.equals("SAIR")) break;

                System.out.print("Digite o valor a ser convertido: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                double convertedValue = currencyService.convertCurrency(fromCurrency, toCurrency, amount);
                System.out.printf("O valor convertido é: %.2f %s%n", convertedValue, toCurrency);
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar a conversão: " + e.getMessage());
        }
    }
}