package com.example.goalBackend.goal;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class GoalFirebaseRepository {
    Firestore dbFireStore = FirestoreClient.getFirestore();

    public Iterable<Goal> getGoalsByUserId(String userId) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = dbFireStore.collection("goals").whereEqualTo("userId", userId).get();
        List<QueryDocumentSnapshot> queryDocumentSnapshotList = querySnapshotApiFuture.get().getDocuments();
        return queryDocumentSnapshotList.stream().map(document -> document.toObject(Goal.class)).collect(Collectors.toList());
    }

    public Goal addGoal(Goal goal) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> collectionsApiFuture = dbFireStore.collection("goals").add(goal);
        DocumentReference documentReference = collectionsApiFuture.get();
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(document.exists()) {
            return document.toObject(Goal.class);
        }
        return null;
    }

    public Goal updateGoal(Goal goal) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("goals").document(goal.getId()).set(goal);
        if(collectionsApiFuture.get() != null) {
            return goal;
        }
        return null;
    }

    public Goal getGoal(String id) throws Exception {
        DocumentReference documentReference = dbFireStore.collection("goals").document(id);
        ApiFuture<DocumentSnapshot> future =  documentReference.get();
        DocumentSnapshot document = future.get();
        if(document.exists()) {
            return document.toObject(Goal.class);
        }
        throw new Exception("Goal id: " + id + " do not exist.");
    }
}
