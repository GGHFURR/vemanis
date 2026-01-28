package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.constant.Status;
import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.supply.CreateSupplyReq;
import com.dansmultipro.vemanis.dto.supply.SupplyDataReq;
import com.dansmultipro.vemanis.dto.supply.SupplyRes;
import com.dansmultipro.vemanis.dto.supply.SupplyResDetail;
import com.dansmultipro.vemanis.exception.BadRequestException;
import com.dansmultipro.vemanis.exception.DuplicateResourceException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.model.*;
import com.dansmultipro.vemanis.repository.*;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.SupplyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class SupplyServiceImpl extends BaseService implements SupplyService {

    private final SupplyRepo supplyRepo;
    private final SupplyDetailRepo supplyDetailRepo;
    private final SupplierRepo supplierRepo;
    private final ProductRepo productRepo;
    private final StockHistoryRepo stockHistoryRepo;
    private final HistoryStatusRepo historyStatusRepo;

    public SupplyServiceImpl(
            SupplyRepo supplyRepo,
            SupplyDetailRepo supplyDetailRepo,
            SupplierRepo supplierRepo,
            ProductRepo productRepo,
            StockHistoryRepo stockHistoryRepo,
            HistoryStatusRepo historyStatusRepo
    ) {
        this.supplyRepo = supplyRepo;
        this.supplyDetailRepo = supplyDetailRepo;
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
        this.stockHistoryRepo = stockHistoryRepo;
        this.historyStatusRepo = historyStatusRepo;
    }

    @Override
    public List<SupplyRes> getAll() {
        return supplyRepo.findAll().stream().map(s -> {
            SupplyRes res = new SupplyRes();
            res.setId(s.getId());
            res.setCode(s.getCode());
            res.setDate(s.getDate());
            res.setSupplier(s.getSupplier().getName());
            return res;
        }).toList();
    }

    @Override
    public List<SupplyResDetail> getDetailBySupplyId(String supplyId) {
        var supplyDetailId = validateId(supplyId);
        return supplyDetailRepo.findBySupplyId(supplyDetailId)
                .stream()
                .map(d -> {
                    SupplyResDetail res = new SupplyResDetail();
                    res.setProductName(d.getProduct().getName());
                    res.setQuantity(d.getQuantity());
                    return res;
                })
                .toList();
    }

    @Transactional
    @Override
    public CreateResDTO create(CreateSupplyReq req) {
        var supplierId = validateId(req.getSupplierId());

        Supplier supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        Supply supply = new Supply();
        supply.setCode(generateRandomAlphaNumeric(5));
        supply.setDate(LocalDateTime.now());
        supply.setSupplier(supplier);
        createBase(supply);

        supplyRepo.save(supply);

        HistoryStatus statusIn = historyStatusRepo.findByCode(Status.IN.name())
                .orElseThrow(() -> new NotFoundException("History Status IN Not Found"));

        createDetail(req, supply, statusIn);

        return new CreateResDTO(supply.getId(), "Created");
    }

    private void createDetail(
            CreateSupplyReq req,
            Supply supply,
            HistoryStatus statusIn
    ) {
        var date = LocalDateTime.now();
        var products = new HashSet<>();

        for (SupplyDataReq data : req.getDataSupply()) {
            var productId = validateId(data.getProductId());

            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product Not Found"));

            product.setStock(product.getStock() + data.getQuantity());

            if(!products.add(data.getProductId())){
                throw new DuplicateResourceException("Duplicate product in supply data");
            }

            productRepo.save(product);

            SupplyDetail detail = new SupplyDetail();
            detail.setDate(date);
            detail.setQuantity(data.getQuantity());
            detail.setProduct(product);
            detail.setSupply(supply);
            createBase(detail);

            supplyDetailRepo.save(detail);

            createHistory(req, product, data.getQuantity(), statusIn);
        }
    }

    private void createHistory(
            CreateSupplyReq req,
            Product product,
            Integer quantity,
            HistoryStatus status
    ) {

        StockHistory history = new StockHistory();
        history.setDate(LocalDateTime.now());
        history.setQuantity(quantity);
        history.setProduct(product);
        history.setStatus(status);
        createBase(history);

        stockHistoryRepo.save(history);
    }

    private String generateRandomAlphaNumeric(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            result.append(chars.charAt(index));
        }
        return result.toString();
    }
}