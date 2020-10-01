package com.example.pento.data;

import com.example.pento.data.model.LoggedInUser;


import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            // The connection URL
            String url = "http://10.0.2.2:8080/createUser";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject personJsonObject = new JSONObject();
            personJsonObject.put("username", username);
            personJsonObject.put("password", password);

            // Make the HTTP GET request, marshaling the response to a String
            HttpEntity<String> request =
                    new HttpEntity<String>(personJsonObject.toString(), headers);

            LoggedInUser result =
                    restTemplate.postForObject(url, request, LoggedInUser.class);
            return new Result.Success<>(result);

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}