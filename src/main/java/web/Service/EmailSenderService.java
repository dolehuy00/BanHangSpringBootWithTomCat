
package web.Service;


import org.springframework.stereotype.Service;


@Service
public interface EmailSenderService {
    public void sendMail(String toEmail, String subject, String body);
}
