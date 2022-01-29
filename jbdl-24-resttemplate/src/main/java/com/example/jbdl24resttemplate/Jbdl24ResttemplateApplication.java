package com.example.jbdl24resttemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Component
public class Jbdl24ResttemplateApplication {

    @Autowired
    RestTemplate restTemplate;

    String getShortUrl(String url, String hostname) throws URISyntaxException {

        Map<String,String> map = new HashMap<>();
        map.put("longUrl",url);
        map.put("hostName",hostname);
        RequestEntity<String> requestEntity = new RequestEntity(map,HttpMethod.POST,new URI("http://localhost:8080/longUrl"));


//        return  restTemplate.exchange("http://localhost:8080/longUrl", HttpMethod.POST,requestEntity,String
//        .class).getBody();
        return restTemplate.postForEntity("http://localhost:8080/longUrl",map,String.class).getBody();

    }

    String getLongUrl(String url) throws URISyntaxException {

        RequestEntity<String> requestEntity = new RequestEntity(HttpMethod.GET,new URI("http://localhost:8080/MQ=="));

//        return restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class).getBody();
        return restTemplate.getForEntity(url,String.class).getBody();



    }
    public static void main(String[] args) {
        Jbdl24ResttemplateApplication app = SpringApplication.run(Jbdl24ResttemplateApplication.class, args).getBean(Jbdl24ResttemplateApplication.class);
        try {
            String url = app.getShortUrl("https://openapi.aarogyasetu.gov.in/","localhost:8080");
            System.out.println(url);
            System.out.println(app.getLongUrl(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
