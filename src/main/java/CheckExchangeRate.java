import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import java.io.IOException;

public class CheckExchangeRate {
    //URL da API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/38aa19e3f4466d5dd339b436/latest/";
    //Realizando a requisição da API.
    public static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + baseCurrency))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Gson gson = new Gson();
        ExchangeRateResponse exchangeRateResponse = gson.fromJson(json, ExchangeRateResponse.class);

        return exchangeRateResponse.getConversionRates().get(targetCurrency);
    }
}
