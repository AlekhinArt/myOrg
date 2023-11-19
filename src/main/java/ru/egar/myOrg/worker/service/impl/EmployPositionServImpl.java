package ru.egar.myOrg.worker.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.egar.myOrg.worker.dto.EmployPositionDto;
import ru.egar.myOrg.worker.mapper.EmployPositionMapper;
import ru.egar.myOrg.worker.repository.EmployPositionRepository;
import ru.egar.myOrg.worker.service.EmployPositionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployPositionServImpl implements EmployPositionService {

    public EmployPositionServImpl(EmployPositionRepository employPositionRepository) {
        this.employPositionRepository = employPositionRepository;
    }

    private final EmployPositionRepository employPositionRepository;

    @Cacheable(cacheNames = "emsdto")
    @Override
    public List<EmployPositionDto> getAll() {
        final List<EmployPositionDto> empsDto = employPositionRepository.findAll()
                .stream()
                .map(EmployPositionMapper::toEmployPositionDto)
                .collect(Collectors.toList());
        return empsDto;
    }

    @Override
    public Optional<EmployPositionDto> getById(Long aLong) {
        return employPositionRepository.findById(aLong)
                .map(EmployPositionMapper::toEmployPositionDto);

    }

    @CacheEvict(cacheNames = "emsdto", allEntries = true)
    @Override
    public EmployPositionDto create(EmployPositionDto dto) {
        return EmployPositionMapper.toEmployPositionDto(
                employPositionRepository.save(
                        EmployPositionMapper.toEmployPosition(dto)));
    }

    @Override
    public EmployPositionDto updateById(Long aLong, EmployPositionDto employPositionDto) {
        return null;
    }

    @CacheEvict(cacheNames = "emsdto", allEntries = true)
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
