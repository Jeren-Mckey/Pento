package com.example.PentoApi.service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.PentoApi.doa.Group;

/**
 * Service that handles Group data requests
 */
@Service
public class GroupService {

    public static final String COL_NAME = "groups";

    public Group getGroup(String group_id) {}

    public void getAllGroups() {}

    public void postGroup() {}

    public void deleteGroup() {}

    public void updateGroup() {}
}
