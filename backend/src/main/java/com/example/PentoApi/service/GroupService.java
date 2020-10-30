package com.example.PentoApi.service;
import com.example.PentoApi.doa.User;
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

    public Group getGroup(String group_id) throws InterruptedException, ExecutionException
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(group_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Group group = new Group();

        if(document.exists()) {
            group = document.toObject(Group.class);
            return group;
        }else {
            return null;
        }
    }

    public List<Group> getAllGroups(String user_id) throws InterruptedException, ExecutionException
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Group> groups = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            groups.add(document.toObject(Group.class));
        }
        return groups;
    }

    public String postGroup(Group group) throws InterruptedException, ExecutionException
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COL_NAME).document(group.getId()).collection("Messages");
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(group.getId()).set(group);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteGroup(String group_id) throws InterruptedException, ExecutionException
    {
        return "Test";
    }

    public String updateGroup(Group group) throws InterruptedException, ExecutionException
    {
        return "test";
    }
}
