package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class CurrencyService {
    private static final String API_KEY = "3f4252f99ca505347a0d4f2f";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private Map<String, Double> conversionRates = new HashMap<>();

    public CurrencyService() throws Exception {
        loadExchangeRates();
    }

    private void loadExchangeRates() throws Exception {
        // Criando um HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Criando uma requisição GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .GET()
                .build();

        // Enviando a requisição e recebendo a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Processando a resposta JSON
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

        // Extraindo as taxas de câmbio
        if (jsonResponse.has("conversion_rates")) {
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");
            for (String currency : rates.keySet()) {
                conversionRates.put(currency, rates.get(currency).getAsDouble());
            }
        } else {
            throw new Exception("Resposta inválida da API.");
        }
    }

    public void printExchangeRates() {
        System.out.println("Taxas de câmbio disponíveis (base: USD):");
        for (Map.Entry<String, Double> entry : conversionRates.entrySet()) {
            System.out.println("1 USD = " + entry.getValue() + " " + entry.getKey());
        }
    }

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) throws Exception {
        if (!conversionRates.containsKey(fromCurrency) || !conversionRates.containsKey(toCurrency)) {
            throw new Exception("Código de moeda inválido.");
        }

        // Converter o valor para USD e depois para a moeda de destino
        double amountInUsd = amount / conversionRates.get(fromCurrency);
        return amountInUsd * conversionRates.get(toCurrency);
    }
}