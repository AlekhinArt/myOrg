package ru.egar.myOrg.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;

@Slf4j
@Component
public class NotWorksDayValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NotWorksDays.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors er) {
        NotWorksDays nwd = (NotWorksDays) obj;
        if (nwd.getEnd().isBefore(nwd.getStart())) {
            er.rejectValue("end", "value.negative");
        }
        if (nwd.getStart().isAfter(nwd.getEnd())) {
            er.rejectValue("start", "value.negative");
        }


    }
}
