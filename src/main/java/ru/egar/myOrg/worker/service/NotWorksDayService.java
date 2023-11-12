package ru.egar.myOrg.worker.service;


import ru.egar.myOrg.base.BaseService;
import ru.egar.myOrg.worker.model.notWorksDays.NotWorksDays;

import java.util.Collection;

public interface NotWorksDayService extends BaseService<NotWorksDays, Long> {

    Collection <NotWorksDays> getAllByWhId(Long id);
    Collection <NotWorksDays> getAllByWhIdAndType();
}
