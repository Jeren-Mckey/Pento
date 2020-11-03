package com.example.pento.data;

import android.os.AsyncTask;

import com.example.pento.data.model.LoggedInUser;
import com.example.pento.data.model.ResponseMessage;
import com.example.pento.data.model.ResponseMessageList;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Class that handles message data sending and retrieval
 */
public class MessageDataSource {

    private class SendMessageTask extends AsyncTask<ResponseMessage, Void, String>
    {
        @Override
        protected String doInBackground(ResponseMessage... messages)
        {
            String result;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject userJsonObject = new JSONObject();
            try {
                userJsonObject.put("member_id", messages[0].getUsername());
                userJsonObject.put("password", messages[0].getText());
                userJsonObject.put("timestamp", messages[0].getTimestamp());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Make the HTTP GET request, marshaling the response to a String
            HttpEntity<String> request =
                    new HttpEntity<String>(userJsonObject.toString(), headers);
            String url = "http://10.0.2.2:8080/message/" + messages[0].getGroup_id();
            result = restTemplate.postForObject(url, request, String.class);

            return result;
        }
    }

    private class GetMessageTask extends AsyncTask<String, Void, ResponseMessage[]>
    {
        @Override
        protected ResponseMessage[] doInBackground(String... groups)
        {
            ResponseMessage[] result;
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://10.0.2.2:8080/message/" + groups[0];
            result = restTemplate.getForObject(url, ResponseMessage[].class);
            return result;
        }
    }

    public Result<String> sendMessage(String content, String member_id, String group_id) {
        try {
            ResponseMessage usr = new ResponseMessage(content, member_id,true, group_id);
            AsyncTask<ResponseMessage, Void, String> task = new MessageDataSource.SendMessageTask().execute(usr);
            String response = task.get();
            return new Result.Success<>(task.get());

        } catch (Exception e) {
            return new Result.Error(new IOException("Error Sending Message", e));
        }
    }

    public Result<ResponseMessageList> getMessages(String group_id) {
        try {
            AsyncTask<String, Void, ResponseMessage[]> task = new MessageDataSource.GetMessageTask().execute(group_id);
            ResponseMessageList list = new ResponseMessageList();
            ResponseMessage[] responses = task.get();
            for (int i = 0; i < responses.length; i++)
            {
                list.add(responses[i]);
            }
            return new Result.Success<>(list);

        } catch (Exception e) {
            return new Result.Error(new IOException("Error Getting Messges", e));
        }
    }
}
