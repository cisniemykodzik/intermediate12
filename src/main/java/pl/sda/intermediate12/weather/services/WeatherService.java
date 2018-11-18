package pl.sda.intermediate12.weather.services;

import com.google.gson.Gson;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.sda.intermediate12.weather.model.WeatherResult;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {
    private String key = "ea900b66f547fd7b23625544873a4200";

    public String getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .build();
        OpenWeatherMapJ8 weatherService = retrofit.create(OpenWeatherMapJ8.class);
        CompletableFuture<WeatherResult> completableFuture = weatherService.currentByCity("London", key, "metric", "pl");

        WeatherResult weatherResult = completableFuture.join();
        return new Gson().toJson(weatherResult);
    }

}
