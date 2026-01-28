package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.stockHistory.StockHistoryRes;
import com.dansmultipro.vemanis.service.StockHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock-history")
public class StockHistoryController {

    private final StockHistoryService stockHistoryService;

    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<StockHistoryRes>> getAll() {
        var res = stockHistoryService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockHistoryRes>> getByProduct(
            @PathVariable String productId
    ) {
        var res =  stockHistoryService.getByProduct(productId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}


