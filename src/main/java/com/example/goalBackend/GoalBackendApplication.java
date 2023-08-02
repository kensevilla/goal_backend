package com.example.goalBackend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class GoalBackendApplication {

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = GoalBackendApplication.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("serviceAccountKey.json");



		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(inputStream))
				.setDatabaseUrl("https://goal-app-da9d5-default-rtdb.asia-southeast1.firebasedatabase.app")
				.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(GoalBackendApplication.class, args);
	}
}
