package com.example.finalprogressreview.externalApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class OmdbExternalRatingImpl implements OmdbExternalRating {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

    public OmdbExternalRatingImpl(
            @Value("${omdb.api.url}") String apiUrl,
            @Value("${omdb.api.key}") String apiKey) {
        this.restTemplate = new RestTemplate();
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    @Override
    public Integer getRatingByTitle(String title) {
        String url = String.format("%s/?t=%s&apikey=%s", apiUrl, title.replace(" ", "+"), apiKey);
        Map<?, ?> response = restTemplate.getForObject(url, Map.class);
        if (response == null) {
            return null;
        }
        Object imdbRating = response.get("imdbRating");
        if (imdbRating instanceof String str && !str.equals("N/A")) {
            double v = Double.parseDouble(str);
            return (int) Math.round(v * 10);
        }
        return null;
    }
}
