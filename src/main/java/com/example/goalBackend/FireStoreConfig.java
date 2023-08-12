package com.example.goalBackend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FireStoreConfig {
    @Autowired
    private Environment environment;

    @PostConstruct
    public void initialization() throws IOException {
        ClassLoader classLoader = FireStoreConfig.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("serviceAccountKey.json");



        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl(environment.getProperty("dbUrl"))
                .build();

        FirebaseApp.initializeApp(options);
    }
}
