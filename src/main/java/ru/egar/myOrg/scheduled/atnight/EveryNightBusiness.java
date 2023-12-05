package ru.egar.myOrg.scheduled.atnight;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.email.model.enumerated.MailType;
import ru.egar.myOrg.email.service.SenderService;
import ru.egar.myOrg.organization.model.Organization;
import ru.egar.myOrg.organization.service.impl.OrganizationServiceImpl;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.service.WorkerService;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EveryNightBusiness {
    private final OrganizationServiceImpl orgService;
    private final WorkerService workerService;
    private final SenderService senderService;


    public void run() throws MessagingException {
        log.info("Запустили программу поздравления с днем рождения");
        Collection<Organization> orgs = orgService.getAllSentBirthday();
        Collection<Long> ogrgsId = orgs.stream()
                .map(Organization::getId)
                .collect(Collectors.toList());
        Collection<WorkerDto> workers = workerService.getForSendMail(ogrgsId);
        log.info("Длина списка сотрудников для поздравления {}", workers.size());
        Map<Long, Organization> orgsMap = orgs.stream().collect(Collectors.toMap(Organization::getId, Function.identity()));
        for (WorkerDto worker : workers) {
            Organization org = orgsMap.get(worker.getOrgId());
            StringBuilder nam = new StringBuilder(worker.getName());
            nam.append(" ");
            nam.append(worker.getPatronymic());
            String text = "Поздравляем с днем рождения";
            senderService.createMail(org.getEmail(), worker.getEmail(), "Поздравление с днем рождения",
                    nam.toString(), org.getAddress(), "С Уважением Ваша " + org.getName(), text, MailType.CONGRATULATION_BIRTHDAY);
        }
    }


}
