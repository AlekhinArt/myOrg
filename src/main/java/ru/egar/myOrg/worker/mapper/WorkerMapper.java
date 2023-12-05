package ru.egar.myOrg.worker.mapper;

import org.springframework.stereotype.Component;
import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.model.Worker;

@Component
public class WorkerMapper {

    public Worker toWorker(WorkerDto workerDto) {
        return Worker.builder()
                .id(workerDto.getId())
                .name(workerDto.getName())
                .surname(workerDto.getSurname())
                .patronymic(workerDto.getPatronymic())
                .birthday(workerDto.getBirthday())
                .phoneNumber(workerDto.getPhoneNumber())
                .workNow(workerDto.getWorkNow())
                .minorChildren(workerDto.getMinorChildren())
                .gender(workerDto.getGender())
                .familyStatus(workerDto.getFamilyStatus())
                .organization(workerDto.getOrganization())
                .email(workerDto.getEmail())
                .delete(false)
                .build();

    }

    public WorkerDto toWorkerDto(Worker worker) {
        return WorkerDto.builder()
                .id(worker.getId())
                .name(worker.getName())
                .surname(worker.getSurname())
                .patronymic(worker.getPatronymic())
                .birthday(worker.getBirthday())
                .phoneNumber(worker.getPhoneNumber())
                .workNow(worker.getWorkNow())
                .organization(worker.getOrganization())
                .familyStatus(worker.getFamilyStatus())
                .gender(worker.getGender())
                .minorChildren(worker.getMinorChildren())
                .email(worker.getEmail())
                .build();

    }


}
