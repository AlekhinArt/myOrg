package ru.egar.myOrg.worker.mapper;

import ru.egar.myOrg.worker.dto.WorkerDto;
import ru.egar.myOrg.worker.dto.WorkerShowDto;
import ru.egar.myOrg.worker.model.Worker;

public class WorkerMapper {


    public static Worker toWorker(WorkerDto workerDto) {
        return Worker.builder()
                .id(workerDto.getId())
                .name(workerDto.getName())
                .surname(workerDto.getSurname())
                .patronymic(workerDto.getPatronymic())
                .birthday(workerDto.getBirthday())
                .phoneNumber(workerDto.getPhoneNumber())
                .workNow(workerDto.getWorkNow())
                .workHistory(workerDto.getWorkHistory())
                .build();

    }

    public static WorkerDto toWorkerDto(Worker worker) {
        return WorkerDto.builder()
                .id(worker.getId())
                .name(worker.getName())
                .surname(worker.getSurname())
                .patronymic(worker.getPatronymic())
                .birthday(worker.getBirthday())
                .phoneNumber(worker.getPhoneNumber())
                .workNow(worker.getWorkNow())
                .workHistory(worker.getWorkHistory())
                .organization(worker.getOrganization())
                .valuableObjects(worker.getValuableObjects())
                .build();

    }

    public static WorkerShowDto toShowWorker(Worker worker) {
        return WorkerShowDto.builder()
                .id(worker.getId())
                .name(worker.getName())
                .surname(worker.getSurname())
                .patronymic(worker.getPatronymic())
                .birthday(worker.getBirthday())
                .phoneNumber(worker.getPhoneNumber())
                .employPosition(worker.getWorkHistory().get(0).getEmployPosition().getPosition())
                .build();


    }


}
