package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.stockHistory.CreateStockHistoryReq;
import com.dansmultipro.vemanis.dto.stockHistory.StockHistoryRes;
import com.dansmultipro.vemanis.exception.BadRequestException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.model.HistoryStatus;
import com.dansmultipro.vemanis.model.Product;
import com.dansmultipro.vemanis.model.StockHistory;
import com.dansmultipro.vemanis.repository.HistoryStatusRepo;
import com.dansmultipro.vemanis.repository.ProductRepo;
import com.dansmultipro.vemanis.repository.StockHistoryRepo;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.StockHistoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StockHistoryServiceImpl extends BaseService implements StockHistoryService {

    private final StockHistoryRepo stockHistoryRepo;
    private final ProductRepo productRepo;
    private final HistoryStatusRepo historyStatusRepo;

    public StockHistoryServiceImpl(
            StockHistoryRepo stockHistoryRepo,
            ProductRepo productRepo,
            HistoryStatusRepo historyStatusRepo
    ) {
        this.stockHistoryRepo = stockHistoryRepo;
        this.productRepo = productRepo;
        this.historyStatusRepo = historyStatusRepo;
    }

    @Override
    public List<StockHistoryRes> getAll() {
            return stockHistoryRepo.findAll().stream()
                    .map(res -> new StockHistoryRes(
                            res.getId(),
                            res.getDate(),
                            res.getQuantity(),
                            res.getProduct().getName(),
                            res.getStatus().getCode()
                    ))
                    .toList();
    }

    @Transactional
    @Override
    public CreateResDTO create(CreateStockHistoryReq req) {

        Product product = productRepo.findById(UUID.fromString(req.getProduct()))
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        HistoryStatus status = historyStatusRepo.findById(UUID.fromString(req.getStatus()))
                .orElseThrow(() -> new NotFoundException("Status Not Found"));

        if (req.getQuantity() <= 0) {
            throw new BadRequestException("Quantity must be greater than 0");
        }

        if ("OUT".equalsIgnoreCase(status.getCode())) {
            if (product.getStock() < req.getQuantity()) {
                throw new BadRequestException("Stock Not Enough");
            }
            product.setStock(product.getStock() - req.getQuantity());
        } else if ("IN".equalsIgnoreCase(status.getCode())) {
            product.setStock(product.getStock() + req.getQuantity());
        }

        StockHistory history = new StockHistory();
        history.setDate(req.getDate());
        history.setQuantity(req.getQuantity());
        history.setProduct(product);
        history.setStatus(status);
        createBase(history);

        stockHistoryRepo.save(history);

        return new CreateResDTO(history.getId(), "Stock History Created");
    }

    @Override
    public List<StockHistoryRes> getByProduct(String productId) {
        var productsId = validateId(productId);

        var product = productRepo.findById(productsId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        return stockHistoryRepo.findByProduct(product).stream()
                .map(res -> new StockHistoryRes(
                        res.getId(),
                        res.getDate(),
                        res.getQuantity(),
                        res.getProduct().getName(),
                        res.getStatus().getCode()
                ))
                .toList();
    }
}

