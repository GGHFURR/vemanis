package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.historyStatus.HistoryStatusRes;

import java.util.List;

public interface HistoryStatusService {
    List<HistoryStatusRes> getAll();
}
