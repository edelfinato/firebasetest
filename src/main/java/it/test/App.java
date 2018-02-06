package it.test;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 */
public class App {

    public static final String SEVICE_ACCOUNT = "C:\\Users\\cona77\\Documents\\GitHub\\firebasetest\\fcm-bello-bello-firebase-adminsdk-748uc-c35abbcda2.json";
    private static GoogleCredentials googleCredential;


    private static String getAccessToken() throws IOException {
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(new FileInputStream(SEVICE_ACCOUNT));
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }

    private static GoogleCredentials getGoogleCredetial() throws IOException {
        if(googleCredential == null) {
            InputStream serviceAccount = new FileInputStream(SEVICE_ACCOUNT);
            googleCredential = GoogleCredentials
                    .fromStream(serviceAccount);
        }
        return googleCredential;
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("https.proxyHost", "proxy-na.gbs.pro");
        System.setProperty("https.proxyPort", "8080");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(getGoogleCredetial())
                .setDatabaseUrl("https://fcm-bello-bello.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        System.out.println(getAccessToken());
    }
}
