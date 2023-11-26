package ru.egar.myOrg.emailSendler.senderService;

import jakarta.mail.MessagingException;
import ru.egar.myOrg.emailSendler.model.Mail;
import ru.egar.myOrg.emailSendler.model.enumerated.MailType;

public interface SenderService {


    void createMail(String from, String mailTo, String subject, String name, String address,
                    String sign, String text, MailType mailType) throws MessagingException;

    void sendEmail(Mail mail) throws MessagingException;
}
