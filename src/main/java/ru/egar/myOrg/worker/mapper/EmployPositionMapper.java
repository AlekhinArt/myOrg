package ru.egar.myOrg.worker.mapper;

import ru.egar.myOrg.worker.dto.EmployPositionDto;
import ru.egar.myOrg.worker.model.EmployPosition;

public class EmployPositionMapper {

    public static EmployPosition toEmployPosition (EmployPositionDto employPositionDto){
        return EmployPosition.builder()
                .position(employPositionDto.getPosition())
                .jobDescription(employPositionDto.getJobDescription())
                .build();
    }

    public static EmployPositionDto toEmployPositionDto( EmployPosition employPosition){
        return EmployPositionDto.builder()
                .position(employPosition.getPosition())
                .jobDescription(employPosition.getJobDescription())
                .build();
    }
}
