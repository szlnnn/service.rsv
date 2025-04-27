package mohi.wedding.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
class FormService {

    @Autowired
    private Sheets sheetsService;

    @Autowired
    private JavaMailSender emailSender;


    @Value("${spreadsheet.id}")
    private String spreadsheetId;

    @Value("${notification.email}")
    private String notificationEmail;

    @Async
    public void processForm(FormData data) {
        System.out.println("Processing Form Data\n" + data);
        sendNotification(data);
        appendToSheet(data);
    }

    public void appendToSheet(FormData data) {
        try {
            List<List<Object>> values = List.of(
                    Arrays.asList(data.getName(), data.getEmail(), data.isAttending(), data.getGuestNumbers(), Arrays.toString(data.getGuests()), data.getVeganMenus(), data.getMessage())
            );
            ValueRange body = new ValueRange().setValues(values);
            sheetsService.spreadsheets().values()
                    .append(spreadsheetId, "Sheet1!A1", body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNotification(FormData data) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notificationEmail);
            message.setSubject("New Form Submission");
            String spreadsheetUrl = "https://docs.google.com/spreadsheets/d/" + spreadsheetId + "/edit";

            message.setText("You've received a new submission:\n\n" + data + "\n Check it out at:  \n" + spreadsheetUrl );
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
