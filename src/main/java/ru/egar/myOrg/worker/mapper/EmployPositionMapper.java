package ru.egar.myOrg.worker.mapper;

import org.springframework.stereotype.Component;
import ru.egar.myOrg.worker.dto.EmployPositionDto;
import ru.egar.myOrg.worker.model.EmployPosition;

@Component
public class EmployPositionMapper {

    public EmployPosition toEmployPosition(EmployPositionDto employPositionDto) {
        return EmployPosition.builder()
                .id(employPositionDto.getId())
                .position(employPositionDto.getPosition())
                .jobDescription(employPositionDto.getJobDescription())
                .build();
    }

    public EmployPositionDto toEmployPositionDto(EmployPosition employPosition) {
        return EmployPositionDto.builder()
                .id(employPosition.getId())
                .position(employPosition.getPosition())
                .jobDescription(employPosition.getJobDescription())
                .build();
    }
}
