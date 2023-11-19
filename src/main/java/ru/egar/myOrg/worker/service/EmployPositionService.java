package ru.egar.myOrg.worker.service;

import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.dto.EmployPositionDto;

import java.util.List;

public interface EmployPositionService extends BaseService<EmployPositionDto, Long> {

    List<String> getPositionName();


}
