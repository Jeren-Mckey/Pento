package com.example.PentoApi.service;
import com.example.PentoApi.doa.Group;
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

    public static final String COL_NAME = "groups/";

    public String postMessage(String group_id, Message message) throws InterruptedException, ExecutionException
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(message.getTimestamp()).set(message);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Message> getAllMessages(String group_id) throws InterruptedException, ExecutionException
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).document(group_id).collection("Mesasages").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Message> messages = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            messages.add(document.toObject(Message.class));
        }
        return messages;
    }

    public String deleteMessage(String group_id, String message_id) throws InterruptedException, ExecutionException
    {
        return "Test";
    }
}
