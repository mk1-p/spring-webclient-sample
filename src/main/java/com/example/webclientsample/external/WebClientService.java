package com.example.webclientsample.external;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WebClientService {

    WebClient webClient;

    @PostConstruct
    private void initWebClient() {
        webClient = WebClient.builder()
                .baseUrl("http://localhost/simple")
                .build();
    }


    public Mono<String> getSimple() {
        log.info("Run! getTest Method");
        return webClient.get().retrieve().bodyToMono(String.class);
    }


}
