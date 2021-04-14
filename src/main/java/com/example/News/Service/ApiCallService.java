package com.example.News.Service;

import com.example.News.Model.Response.ApiWeather1Response;
import com.example.News.Model.Response.ApiWeather2Response;
import com.google.gson.Gson;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiCallService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String URL = "http://webcode.me";

    @CircuitBreaker(name = "ApiWeather1", fallbackMethod = "fallback")
    public ApiWeather1Response callApi1() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=Madrid"))
                .header("x-rapidapi-key", "082b1853cfmsh71e738034413ffcp1ed1a1jsnfcc568abcb03")
                .header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        final ApiWeather1Response apiWeather1Response = new Gson().fromJson(response.body(), ApiWeather1Response.class);
        System.out.println(apiWeather1Response);

        if(RandomUtils.nextBoolean()){
            throw new IOException("Trying Circuit Breaker...");
        }
        return apiWeather1Response;
    }

    private ApiWeather1Response fallback(final Throwable t) {
        log.error(t.getStackTrace().toString());
        return ApiWeather1Response.builder().build();
    }

    @CircuitBreaker(name = "ApiWeather2", fallbackMethod = "fallback2")
    public ApiWeather2Response callApi2() throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://climacell-microweather-v1.p.rapidapi.com/weather/realtime?lat=42.8237618&lon=-71.2216286&fields=precipitation"))
                .header("x-rapidapi-key", "082b1853cfmsh71e738034413ffcp1ed1a1jsnfcc568abcb03")
                .header("x-rapidapi-host", "climacell-microweather-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        final ApiWeather2Response apiWeather2Response = new Gson().fromJson(response.body(), ApiWeather2Response.class);

        if (RandomUtils.nextBoolean()){
            throw new IOException("Trying Circuit Breaker");
        }
        return apiWeather2Response;
    }

    private ApiWeather2Response fallback2(final Throwable t) {
        log.error(t.getStackTrace().toString());
        return ApiWeather2Response.builder().build();
    }
}

