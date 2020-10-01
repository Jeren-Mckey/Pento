package com.example.PentoApi.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Collection of utility methods
 */
public class Utils {
    public static ResponseEntity<String> sendRequest(String url, HttpMethod method) throws URISyntaxException {
        URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        // headers.add("Authorization", "Basic some-token-can-go-here");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        RestTemplate rt = new RestTemplate();
        return rt.exchange(uri, method, request, String.class);
    }
}