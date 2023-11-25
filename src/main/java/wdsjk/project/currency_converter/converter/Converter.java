package wdsjk.project.currency_converter.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class Converter {
    private String currencyFrom;
    private String currencyTo;
    private double amount;

    private final String urlString = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/";
    private URL url;

    private double result;

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public double getAmount() {
        return amount;
    }

    public double getResult() {
        return result;
    }

    public void convert() throws IOException {
        // making request
        url = new URL(
                urlString + currencyFrom + "/" + currencyTo + "/" + Double.toString(amount)
        );
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // convert to json
        JsonParser jsonParser = new JsonParser();
        JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonObject = root.getAsJsonObject();

        request.disconnect();
        result = jsonObject.get("conversion_result").getAsDouble();
    }
}
