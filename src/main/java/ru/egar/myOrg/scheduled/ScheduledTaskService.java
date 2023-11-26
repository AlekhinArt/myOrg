package ru.egar.myOrg.scheduled;


import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.scheduled.everyNight.EveryNightBusiness;

@Slf4j
@Service
@EnableAsync
@AllArgsConstructor
public class ScheduledTaskService {
    EveryNightBusiness everyNightBusiness;


    @Scheduled(cron = "0 0 1 * * *", zone = "Europe/Moscow")

    public void everyNight() throws MessagingException {
        log.info("Запустили ночные дела");
        everyNightBusiness.run();
    }
}
