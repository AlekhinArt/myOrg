package ru.egar.myOrg.email.model;

import lombok.*;
import ru.egar.myOrg.email.model.enumerated.MailType;

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
