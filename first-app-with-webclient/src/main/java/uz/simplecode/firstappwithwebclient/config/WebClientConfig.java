package uz.simplecode.firstappwithwebclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

//        @Bean
//        public WebClient getWebClient(WebClient.Builder webClientBuilder) {
//
//            return webClientBuilder
//                    .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
//                    .baseUrl("https://reqres.in/api")
//                    .build();
//        }
//
//        private HttpClient getHttpClient() {
//            return HttpClient.create()
//                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
//                    .doOnConnected(conn - > conn
//                            .addHandlerLast(new ReadTimeoutHandler(10))
//                            .addHandlerLast(new WriteTimeoutHandler(10)));
//        }
}

