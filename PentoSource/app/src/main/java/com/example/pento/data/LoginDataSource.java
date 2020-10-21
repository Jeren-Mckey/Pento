package com.example.pento.data;

import android.os.AsyncTask;

import com.example.pento.data.model.LoggedInUser;


import org.json.JSONException;
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

    private class LoginValidateTask extends AsyncTask<LoggedInUser, Void, String>
    {
        @Override
        protected String doInBackground(LoggedInUser... users)
        {
            String result;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject userJsonObject = new JSONObject();
            try {
                userJsonObject.put("username", users[0].getUsername());
                userJsonObject.put("password", users[0].getPassword());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Make the HTTP GET request, marshaling the response to a String
            HttpEntity<String> request =
                    new HttpEntity<String>(userJsonObject.toString(), headers);
            String url = "http://10.0.2.2:8080/user";
            result = restTemplate.postForObject(url, request, String.class);

            return result;
        }
    }

    public Result<String> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser usr = new LoggedInUser(username, password);
            AsyncTask<LoggedInUser, Void, String> task = new LoginValidateTask().execute(usr);
            String response = task.get();
            return new Result.Success<>(task.get());

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}