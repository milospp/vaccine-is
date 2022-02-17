package zajednicko.service;

public interface MailService {

    void sendMail(String recipient, String subject, String message);

    void sendSomeMail(String subjectMail, String messageTitle, String messageContent);
}
