package ru.egar.myOrg.emailSendler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String from;
    private String mailTo;
    private String subject;

    private Map<String, Object> model;

}
