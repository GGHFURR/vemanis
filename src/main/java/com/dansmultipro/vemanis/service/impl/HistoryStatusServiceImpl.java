package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.dto.historyStatus.HistoryStatusRes;
import com.dansmultipro.vemanis.model.HistoryStatus;
import com.dansmultipro.vemanis.repository.HistoryStatusRepo;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.HistoryStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryStatusServiceImpl extends BaseService implements HistoryStatusService {

    private final HistoryStatusRepo historyStatusRepo;

    public HistoryStatusServiceImpl(HistoryStatusRepo historyStatusRepo) {
        this.historyStatusRepo = historyStatusRepo;
    }

    @Override
    public List<HistoryStatusRes> getAll() {
        return historyStatusRepo.findAll()
                .stream()
                .map(h -> new HistoryStatusRes(h.getCode(),h.getName()))
                .toList();
    }

}
