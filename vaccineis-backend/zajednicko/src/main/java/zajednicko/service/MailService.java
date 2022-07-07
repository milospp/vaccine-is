package zajednicko.service;

public interface MailService {

    void sendMail(String recipient, String subject, String message, String filePath);
}
