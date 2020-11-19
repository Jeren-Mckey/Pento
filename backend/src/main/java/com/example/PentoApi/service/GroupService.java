package com.example.PentoApi.service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
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

    public List<Group> getAllGroups(String query) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference groupCollection = dbFirestore.collection(COL_NAME);
        Query q1 = groupCollection.whereArrayContains("members", query);
        Query q2 = groupCollection.whereEqualTo("tag", query);

        // retrieve  query results asynchronously using query.get()
        ApiFuture<QuerySnapshot> querySnapshot1 = q1.get();
        ApiFuture<QuerySnapshot> querySnapshot2 = q2.get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents1 = querySnapshot1.get().getDocuments();
        List<QueryDocumentSnapshot> documents2 = querySnapshot2.get().getDocuments();

        List<Group> groups = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents1) {
            groups.add(document.toObject(Group.class));
        }

        for (QueryDocumentSnapshot document : documents2) {
            groups.add(document.toObject(Group.class));
        }
        return groups;
    }

    public String postGroup(Group group) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COL_NAME).document(group.getId()).collection("Messages");
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(group.getId()).set(group);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteGroup(String group_id)
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(group_id).delete();
        return group_id+" has been deleted";
    }

    public String updateGroup(Group group) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(group.getId()).set(group);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}
