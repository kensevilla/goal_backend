package com.example.goalBackend.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserFirebaseRepository {

    Firestore dbFireStore = FirestoreClient.getFirestore();

    public String createUser(User user) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> collectionsApiFuture = dbFireStore.collection("users").add(user);
        DocumentReference documentReference = collectionsApiFuture.get();
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(document.exists()) {
            User newUser = document.toObject(User.class);
            if(newUser != null) {
                return newUser.getId();
            }
        }
        return null;
    }

    public String getUserByUsername(String username) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection("users").whereEqualTo("username", username).get();
        List<QueryDocumentSnapshot> queryDocumentSnapshotList = querySnapshotApiFuture.get().getDocuments();
        if(!queryDocumentSnapshotList.isEmpty()) {
            return queryDocumentSnapshotList.get(0).toObject(User.class).getId();
        }
        return null;
    }



    public String getUser(User user) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection("users").whereEqualTo("username", user.getUsername())
                .whereEqualTo("password", user.getPassword()).get();
        List<QueryDocumentSnapshot> queryDocumentSnapshotList = querySnapshotApiFuture.get().getDocuments();
        if(!queryDocumentSnapshotList.isEmpty()) {
            return queryDocumentSnapshotList.get(0).toObject(User.class).getId();
        }
        return null;
    }

}
