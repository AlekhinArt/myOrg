package ru.egar.myOrg.email.senderService;

import jakarta.mail.MessagingException;
import ru.egar.myOrg.email.model.Mail;
import ru.egar.myOrg.email.model.enumerated.MailType;

public interface SenderService {


    void createMail(String from, String mailTo, String subject, String name, String address,
                    String sign, String text, MailType mailType) throws MessagingException;

    void sendEmail(Mail mail) throws MessagingException;
}
