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

import com.example.PentoApi.doa.Message;

/**
 * Service that handles Message data requests
 */
@Service
public class MessageService {

    public static final String COL_NAME = "groups";

    public String postMessage(Message message) throws ExecutionException, InterruptedException {
        System.out.println("Content: " + message.getTimestamp());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore
                .collection(COL_NAME + "/" + message.getGroup() + "/Messages")
                .document(message.getTimestamp())
                .set(message);
        System.out.println("Complete");
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Message> getAllMessages(String group_id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore
                .collection(COL_NAME)
                .document(group_id)
                .collection("Messages")
                .get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Message> messages = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Message m = new Message();
            m.setContent(document.get("content").toString());
            m.setMember(document.get("member").toString());
            m.setTimestamp(document.get("timestamp").toString());
            messages.add(m);
        }
        return messages;
    }
}
