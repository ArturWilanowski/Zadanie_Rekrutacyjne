package pl.wilanowskiartur.services;

import org.springframework.stereotype.Service;
import pl.wilanowskiartur.requirements.ApiSearchRequirements;
import pl.wilanowskiartur.requirements.RandomSearchRequirements;
import pl.wilanowskiartur.utils.HttpClientUtil;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

@Service
public class NumberService {

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public Integer sum(ApiSearchRequirements apiSearchRequirements, RandomSearchRequirements randomSearchRequirements) throws IOException, InterruptedException {
        return getNumberFromApi(apiSearchRequirements) + generateRandomNumber(randomSearchRequirements);
    }

    public Integer getNumberFromApi(ApiSearchRequirements apiSearchRequirements) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(HttpClientUtil.buildUri(apiSearchRequirements))
                .setHeader("User-Agent", "Zadanie rekrutacyjne")
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return Integer.valueOf(httpResponse.body().replace("\n", ""));
    }

    public Integer generateRandomNumber(RandomSearchRequirements randomSearchRequirements) {
        Random r = new Random();
        return r.nextInt((randomSearchRequirements.getMax() - randomSearchRequirements.getMin()) + 1) + randomSearchRequirements.getMin();
    }
}
