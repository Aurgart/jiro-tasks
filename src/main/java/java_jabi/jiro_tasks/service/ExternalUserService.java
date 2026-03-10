package java_jabi.jiro_tasks.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalUserService {

    @Value("${app.url}")
    private String url;
    private final RestClient restClient;

    public ExternalUserService(){
        if(url == null) {
            this.restClient = RestClient.builder().baseUrl("http://localhost:8087/api/v1").build();
        }else {
            this.restClient = RestClient.builder().baseUrl(url).build();
        }
    }

    public boolean checkUser(long userId){
         return Boolean.parseBoolean(restClient.get().uri("/user/check/{id}",userId).retrieve().body(String.class));
    }
    public boolean checkHistUser(long userId){
        return Boolean.parseBoolean(restClient.get().uri("/hist_user/check/{id}",userId).retrieve().body(String.class));
    }
}

