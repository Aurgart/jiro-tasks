package java_jabi.jiro_tasks.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class ExternalUserService {

    private final RestClient restClient;
    @Value("${app.url}")
    private String url;

    public ExternalUserService() {
        if (url == null) {
            this.restClient = RestClient.builder().baseUrl("http://localhost:8087/api/v2").build();
        } else {
            this.restClient = RestClient.builder().baseUrl(url).build();
        }
    }

    public boolean checkUser(long userId) {
        return Boolean.parseBoolean(restClient.get().uri("/user/check/{id}", userId).retrieve().body(String.class));
    }

    public boolean checkHistUser(long userId) {
        return Boolean.parseBoolean(restClient.get().uri("/hist_user/check/{id}", userId).retrieve().body(String.class));
    }

    public boolean checkManagerRole(long userId) {
        if (Objects.equals(restClient.get().uri("/role/{id}", userId).retrieve().body(String.class), "MANAGER")) {
            return true;
        }
        return false;
    }
}

