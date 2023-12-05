package ru.egar.myOrg.email.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.egar.myOrg.email.model.Form;
import ru.egar.myOrg.email.model.Mail;
import ru.egar.myOrg.email.model.enumerated.MailType;
import ru.egar.myOrg.email.service.SenderService;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class SenderServiceImpl implements SenderService {

    private JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void createMail(String from, String mailTo, String subject, String name, String address,
                           String sign, String text, MailType mailType) throws MessagingException {
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setMailTo(mailTo);
        mail.setSubject(subject);
        Form form = Form.builder()
                .name(name)
                .address(address)
                .sign(sign)
                .text(text)
                .mailType(mailType)
                .build();

        Map<String, Object> model = new HashMap<>();
        model.put("name", form.getName());
        model.put("address", form.getAddress());
        model.put("sign", form.getSign());
        model.put("type", form.getMailType());
        model.put("text", form.getText());

        log.info("Send mail to name {}, from {}, sign {} , type {}", name, from, sign, mailType);
        sendEmail(mail);


    }

    @Override
    public void sendEmail(Mail mail) throws MessagingException {


        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());


        Context context = new Context();
        context.setVariables(mail.getModel());

        //при добавлении формы письма меняем тут тии
        final String template = "mail/mailBirthday";
        String html = templateEngine.process(template, context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        emailSender.send(message);

    }


}
