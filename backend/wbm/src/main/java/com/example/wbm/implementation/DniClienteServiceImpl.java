package com.example.wbm.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public  class DniClienteServiceImpl {

    @Value("${api.identificacion-url}")
    protected String baseUrl;

    protected final RestTemplate restTemplate;

    protected HttpHeaders builToken(String token){
        HttpHeaders headers= new HttpHeaders();
        headers.set("Content-type","application/json");
        headers.set("Authorization","Bearer "+token);
        return headers;
    }

    public Map<String, Object> consultarDni(String dni, String token) {
        String url = baseUrl + "/dni/" + dni;

        HttpHeaders headers = builToken(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }

}
