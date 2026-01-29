package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.historyStatus.HistoryStatusRes;
import com.dansmultipro.vemanis.service.HistoryStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history-statuses")
public class HistoryStatusController {

    private final HistoryStatusService historyStatusService;

    public HistoryStatusController(HistoryStatusService historyStatusService) {
        this.historyStatusService = historyStatusService;
    }

    @GetMapping
    public ResponseEntity<List<HistoryStatusRes>> getAll() {
        var res =  historyStatusService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
