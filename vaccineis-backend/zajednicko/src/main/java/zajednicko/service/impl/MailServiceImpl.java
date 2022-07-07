package zajednicko.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import zajednicko.service.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String email;
    @Value("${spring.mail.password}")
    private String password;

    @Override
    @Async
    public void sendMail(String recipient, String subject, String messageContent, String filePath) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.allow8bitmime", "true");
        properties.put("mail.smtps.allow8bitmime", "true");


        String content = this.startOfMail;
        content = content +
                "                          " + subject + "\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style='font-size:0px;padding:10px 25px;word-break:break-word;'>\n" +
                "                        <div style=\"color:#187272;font-family:'Droid Sans', 'Helvetica Neue', Arial, sans-serif;font-size:16px;line-height:20px;text-align:left;\">\n\n" +
                "                          "+ messageContent +"\n";


        content += this.endOfMail;


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html; charset=UTF-8");

            MimeBodyPart attachmentPart = null;
            if (filePath != null) {
                attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(filePath));
            }

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if (attachmentPart != null)
                multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String startOfMail = "" +
            "<!doctype html>\n" +
            "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
            "  <head>\n" +
            "    <title>\n" +
            "    </title>\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "    <style type=\"text/css\">\n" +
            "      #outlook a{padding: 0;}\n" +
            "      \t\t\t.ReadMsgBody{width: 100%;}\n" +
            "      \t\t\t.ExternalClass{width: 100%;}\n" +
            "      \t\t\t.ExternalClass *{line-height: 100%;}\n" +
            "      \t\t\tbody{margin: 0; padding: 0; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}\n" +
            "      \t\t\ttable, td{border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;}\n" +
            "      \t\t\timg{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic;}\n" +
            "      \t\t\tp{display: block; margin: 13px 0;}\n" +
            "    </style>\n" +
            "    <!--[if !mso]><!-->\n" +
            "    <style type=\"text/css\">\n" +
            "      @media only screen and (max-width:480px) {\n" +
            "      \t\t\t  \t\t@-ms-viewport {width: 320px;}\n" +
            "      \t\t\t  \t\t@viewport {\twidth: 320px; }\n" +
            "      \t\t\t\t}\n" +
            "    </style>\n" +
            "    <!--<![endif]-->\n" +
            "    <!--[if mso]> \n" +
            "\t\t<xml> \n" +
            "\t\t\t<o:OfficeDocumentSettings> \n" +
            "\t\t\t\t<o:AllowPNG/> \n" +
            "\t\t\t\t<o:PixelsPerInch>96</o:PixelsPerInch> \n" +
            "\t\t\t</o:OfficeDocumentSettings> \n" +
            "\t\t</xml>\n" +
            "\t\t<![endif]-->\n" +
            "    <!--[if lte mso 11]> \n" +
            "\t\t<style type=\"text/css\"> \n" +
            "\t\t\t.outlook-group-fix{width:100% !important;}\n" +
            "\t\t</style>\n" +
            "\t\t<![endif]-->\n" +
            "    <style type=\"text/css\">\n" +
            "      @media only screen and (max-width:480px) {\n" +
            "      \n" +
            "      \t\t\t  table.full-width-mobile { width: 100% !important; }\n" +
            "      \t\t\t\ttd.full-width-mobile { width: auto !important; }\n" +
            "      \n" +
            "      }\n" +
            "      @media only screen and (min-width:480px) {\n" +
            "      .dys-column-per-100 {\n" +
            "      \twidth: 100.000000% !important;\n" +
            "      \tmax-width: 100.000000%;\n" +
            "      }\n" +
            "      }\n" +
            "      @media only screen and (min-width:480px) {\n" +
            "      .dys-column-per-50 {\n" +
            "      \twidth: 50.000000% !important;\n" +
            "      \tmax-width: 50.000000%;\n" +
            "      }\n" +
            "      .dys-column-per-100 {\n" +
            "      \twidth: 100.000000% !important;\n" +
            "      \tmax-width: 100.000000%;\n" +
            "      }\n" +
            "      .dys-column-per-90 {\n" +
            "      \twidth: 90% !important;\n" +
            "      \tmax-width: 90%;\n" +
            "      }\n" +
            "      }\n" +
            "      @media only screen and (min-width:480px) {\n" +
            "      .dys-column-per-100 {\n" +
            "      \twidth: 100.000000% !important;\n" +
            "      \tmax-width: 100.000000%;\n" +
            "      }\n" +
            "      }\n" +
            "    </style>\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <div>\n" +
            "      <!--[if mso | IE]>\n" +
            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px;\" width=\"600\"><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
            "<![endif]-->\n" +
            "      <div style='background:#4DBFBF;background-color:#4DBFBF;margin:0px auto;max-width:600px;'>\n" +
            "        <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='background:#4DBFBF;background-color:#4DBFBF;width:100%;'>\n" +
            "          <tbody>\n" +
            "            <tr>\n" +
            "              <td style='direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;'>\n" +
            "                <!--[if mso | IE]>\n" +
            "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"vertical-align:top;width:600px;\">\n" +
            "<![endif]-->\n" +
            "                <div class='dys-column-per-100 outlook-group-fix' style='direction:ltr;display:inline-block;font-size:13px;text-align:left;vertical-align:top;width:100%;'>\n" +
            "                  <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='vertical-align:top;' width='100%'>\n" +
            "                    <tr>\n" +
            "                      <td align='center' style='font-size:0px;padding:10px 25px;word-break:break-word;'>\n" +
            "                        <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='border-collapse:collapse;border-spacing:0px;'>\n" +
            "                          <tbody>\n" +
            "                            <tr>\n" +
            "                              <td style='width:216px;'>\n" +
            "                                <img alt='Descriptive Alt Text' src='https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Coat_of_arms_of_Serbia_small.svg/800px-Coat_of_arms_of_Serbia_small.svg.png?width=500&height=500' style='border:none;display:block;font-size:13px;height:189px;outline:none;text-decoration:none;width:100%;height:auto;' />\n" +
            "                              </td>\n" +
            "                            </tr>\n" +
            "                          </tbody>\n" +
            "                        </table>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                      <td align='center' style='font-size:0px;padding:10px 25px;word-break:break-word;'>\n" +
            "                        <div style=\"color:#FFFFFF;font-family:'Droid Sans', 'Helvetica Neue', Arial, sans-serif;font-size:36px;line-height:1;text-align:center;\">\n";

    private String endOfMail =                 "                        </div>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                  </table>\n" +
            "                </div>\n" +
            "                <!--[if mso | IE]>\n" +
            "</td></tr></table>\n" +
            "<![endif]-->\n" +
            "              </td>\n" +
            "            </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "      <!--[if mso | IE]>\n" +
            "</td></tr></table>\n" +
            "<![endif]-->\n" +
            "      <!--[if mso | IE]>\n";
}
