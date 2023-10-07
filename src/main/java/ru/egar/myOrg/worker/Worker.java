package ru.egar.myOrg.worker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.egar.myOrg.document.PaperDocument;


import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Worker {
    private int id;
    private Boolean workNow;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    private List<PaperDocument> documents;
    private List<WorkHistory> workHistory;


}
