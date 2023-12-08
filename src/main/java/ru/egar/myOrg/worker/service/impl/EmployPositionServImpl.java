package ru.egar.myOrg.worker.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.worker.dto.EmployPositionDto;
import ru.egar.myOrg.worker.mapper.EmployPositionMapper;
import ru.egar.myOrg.worker.model.EmployPosition;
import ru.egar.myOrg.worker.repository.EmployPositionRepository;
import ru.egar.myOrg.worker.service.EmployPositionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployPositionServImpl implements EmployPositionService {

    private final EmployPositionMapper epMap;
    private final EmployPositionRepository employPositionRepository;


    @Override
    public List<EmployPositionDto> getAll() {
        final List<EmployPositionDto> empsDto = employPositionRepository.findAll()
                .stream()
                .map(epMap::toEmployPositionDto)
                .collect(Collectors.toList());
        return empsDto;
    }

    @Override
    public Optional<EmployPositionDto> getById(Long aLong) {
        return employPositionRepository.findById(aLong)
                .map(epMap::toEmployPositionDto);

    }


    @Override
    public EmployPositionDto create(EmployPositionDto dto) {
        return epMap.toEmployPositionDto(
                employPositionRepository.save(
                        epMap.toEmployPosition(dto)));
    }

    @Override
    public EmployPositionDto updateById(Long aLong, EmployPositionDto employPositionDto) {
        employPositionRepository.save(EmployPosition.builder()
                .position(employPositionDto.getPosition())
                .codType(employPositionDto.getCodType())
                .id(employPositionDto.getId())
                .jobDescription(employPositionDto.getJobDescription())
                .build());
        return employPositionDto;
    }


    @Override
    public void deleteById(Long aLong) {
        employPositionRepository.deleteById(aLong);

    }

    @Override
    public List<String> getPositionName() {
        employPositionRepository.getAllPosition();
        return employPositionRepository.getAllPosition();
    }
}
