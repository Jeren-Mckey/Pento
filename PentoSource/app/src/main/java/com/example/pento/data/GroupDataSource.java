package com.example.pento.data;

import android.os.AsyncTask;

import com.example.pento.data.model.Group;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupDataSource {

    private class CreateGroupTask extends AsyncTask<Group, Void, String>
    {
        @Override
        protected String doInBackground(Group... groups)
        {
            String result;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject groupJsonObject = new JSONObject();
            try {
                groupJsonObject.put("members", groups[0].getMembers());
                groupJsonObject.put("tag", groups[0].getTag());
                groupJsonObject.put("id", groups[0].getId());
                groupJsonObject.put("name", groups[0].getName());
                groupJsonObject.put("reigon", groups[0].getRegion());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Make the HTTP GET request, marshaling the response to a String
            HttpEntity<String> request =
                    new HttpEntity<String>(groupJsonObject.toString(), headers);
            String url = "http://10.0.2.2:8080/group";
            result = restTemplate.postForObject(url, request, String.class);

            return result;
        }
    }

    private class GetGroupsTask extends AsyncTask<String, Void, Group[]>
    {
        @Override
        protected Group[] doInBackground(String... groups)
        {
            Group[] result;
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://10.0.2.2:8080/message/" + groups[0];
            result = restTemplate.getForObject(url, Group[].class);
            return result;
        }
    }

    public Result<String> createGroup(Group group) {
        try {
            AsyncTask<Group, Void, String> task = new GroupDataSource.CreateGroupTask().execute(group);
            String response = task.get();
            return new Result.Success<>(task.get());

        } catch (Exception e) {
            return new Result.Error(new IOException("Error Creating Group", e));
        }
    }

    public Result<List<Group>> getGroupsByQuery(String query) {
        try {
            AsyncTask<String, Void, Group[]> task = new GroupDataSource.GetGroupsTask().execute(query);
            ArrayList<Group> list = new ArrayList<Group>();
            Group[] groups = task.get();
            for (int i = 0; i < groups.length; i++)
            {
                list.add(groups[i]);
            }
            return new Result.Success<>(list);

        } catch (Exception e) {
            return new Result.Error(new IOException("Error Getting Groups", e));
        }
    }

}
