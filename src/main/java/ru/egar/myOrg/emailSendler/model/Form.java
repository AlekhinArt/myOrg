package ru.egar.myOrg.emailSendler.model;

import lombok.*;
import ru.egar.myOrg.emailSendler.model.enumerated.MailType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Form {
    private String name;
    private MailType mailType;
    private String sign;
    private String text;
    private String address;


}
