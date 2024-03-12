package utils;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ModifyMessageRequest;
import reporting.ExtendedReporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import static base.SeleniumBaseTest.USER_DIR;

public class GmailApi {

    public static boolean waitForSignupEmail(int timeout) throws Throwable {
        Gmail service = getGmailService();
        String subjectToWaitFor = "automation, We’ve Got Your vRx Trial Request"; // Customize your email subject here.
        boolean messageReceived = waitForMessageWithSubject(service, "me", subjectToWaitFor, timeout); // Timeout wait.
        ExtendedReporter.log("Message received and marked as read: " + messageReceived);
        return messageReceived;
    }

    private static boolean waitForMessageWithSubject(Gmail service, String user, String subject, long timeoutMillis) throws Exception {
        long endTime = System.currentTimeMillis() + timeoutMillis;
        while (System.currentTimeMillis() < endTime) {
            ListMessagesResponse response = service.users().messages().list(user)
                    .setQ("is:unread subject:\"" + subject + "\"").execute();
            List<Message> messages = response.getMessages();

            if (messages != null && !messages.isEmpty()) {
                String messageId = messages.get(0).getId();
                ModifyMessageRequest mods = new ModifyMessageRequest().setRemoveLabelIds(Collections.singletonList("UNREAD"));
                service.users().messages().modify(user, messageId, mods).execute();
                return true; // Message found and marked as read.
            }
            Thread.sleep(5000); // Wait for 5 seconds before trying again.
        }
        return false; // Timeout reached, message not found.
    }

    private static Gmail getGmailService() throws Exception {
        String CREDENTIALS_PATH = USER_DIR + File.separator + "src" + File.separator + "main" +
                File.separator + "java" + File.separator + "resources" + File.separator + "credentials.json"; // Correct this path for your project structure.

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(
                        new FileInputStream(CREDENTIALS_PATH)));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
                Collections.singletonList(GmailScopes.GMAIL_MODIFY)) // Ensure GMAIL_MODIFY scope for marking messages as read.
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new Gmail.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), new AuthorizationCodeInstalledApp(flow, receiver).authorize("user"))
                .setApplicationName("Gmail API Java Quickstart")
                .build();
    }
}