package ru.egar.myOrg.scheduled.atnight;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.email.model.enumerated.MailType;
import ru.egar.myOrg.email.senderService.SenderService;
import ru.egar.myOrg.exception.NotFoundException;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.organization.service.impl.OrganizationServiceImpl;
import ru.egar.myOrg.worker.dto.WorkerShowDto;
import ru.egar.myOrg.worker.service.WorkerService;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
@Slf4j
public class EveryNightBusiness {
    private final OrganizationServiceImpl orgServ;
    private final WorkerService workerService;
    private final SenderService senderService;


    public void run() throws MessagingException {
        log.info("Запустили программу поздравления с днем рождения");
        Collection<Organization> orgs = orgServ.getAllSentBirthday();
        Collection<Long> ogrgsId = new ArrayList<>();
        for (Organization org : orgs) {
            ogrgsId.add(org.getId());
        }
        Collection<WorkerShowDto> workers = workerService.getForSendMail(ogrgsId);
        log.info("Длина списка сотрудников для поздравления {}", workers.size());
        for (WorkerShowDto worker : workers) {
            Organization org = orgs.stream()
                    .filter(i -> i.getId() == worker.getOrgId())
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Организация не найдена"));
            StringBuilder nam = new StringBuilder(worker.getName());
            nam.append(" ");
            nam.append(worker.getPatronymic());
            String text = "Поздравляем с днем рождения";
            senderService.createMail(org.getEmail(), worker.getEmail(), "Поздравление с днем рождения",
                    nam.toString(), org.getAddress(), "С Уважением Ваша " + org.getName(), text, MailType.CONGRATULATION_BIRTHDAY);
        }
    }


}
