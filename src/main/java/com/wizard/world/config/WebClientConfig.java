package com.wizard.world.config;

import com.wizard.world.generated.api.ElixirsApi;
import com.wizard.world.generated.api.IngredientsApi;
import com.wizard.world.generated.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${heroku.url.path}")
    private String basePathUrl;

    @Bean
    public ApiClient apiClient(WebClient.Builder builder) {
        WebClient webClient = builder
                .defaultHeader("Accept",  "application/json")
                .build();

        ApiClient client = new ApiClient(webClient);
        client.setBasePath(basePathUrl);
        return client;
    }

    @Bean
    public IngredientsApi ingredientsApi(ApiClient apiClient) {
        return new IngredientsApi(apiClient);
    }

    @Bean
    public ElixirsApi elixirsApi(ApiClient apiClient) {
        return new ElixirsApi(apiClient);
    }
}
