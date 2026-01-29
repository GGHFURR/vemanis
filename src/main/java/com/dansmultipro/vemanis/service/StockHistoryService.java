package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.stockHistory.CreateStockHistoryReq;
import com.dansmultipro.vemanis.dto.stockHistory.StockHistoryRes;

import java.util.List;

public interface StockHistoryService {

    List<StockHistoryRes> getAll();
    CreateResDTO create(CreateStockHistoryReq req);
    List<StockHistoryRes> getByProduct(String productId);
}
