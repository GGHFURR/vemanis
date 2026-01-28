package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.constant.Status;
import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.checkout.CheckOutDataReq;
import com.dansmultipro.vemanis.dto.checkout.CheckOutRes;
import com.dansmultipro.vemanis.dto.checkout.CheckOutResDetail;
import com.dansmultipro.vemanis.dto.checkout.CreateCheckOutReq;
import com.dansmultipro.vemanis.exception.DuplicateResourceException;
import com.dansmultipro.vemanis.exception.NotAllowedStateException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.model.*;
import com.dansmultipro.vemanis.repository.*;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.CheckOutService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class CheckOutServiceImpl extends BaseService implements CheckOutService {
    private final CheckOutRepo checkOutRepo;
    private final CheckOutDetailRepo checkOutDetailRepo;
    private final AgentRepo agentRepo;
    private final ProductRepo productRepo;
    private final HistoryStatusRepo historyStatusRepo;
    private final StockHistoryRepo stockHistoryRepo;

    public CheckOutServiceImpl(CheckOutRepo checkOutRepo, CheckOutDetailRepo checkOutDetailRepo, AgentRepo agentRepo, ProductRepo productRepo, HistoryStatusRepo historyStatusRepo, StockHistoryRepo stockHistoryRepo) {
        this.checkOutRepo = checkOutRepo;
        this.checkOutDetailRepo = checkOutDetailRepo;
        this.agentRepo = agentRepo;
        this.productRepo = productRepo;
        this.historyStatusRepo = historyStatusRepo;
        this.stockHistoryRepo = stockHistoryRepo;
    }

    @Override
    public List<CheckOutRes> getAll(){
        return checkOutRepo.findAll().stream()
                .map(c-> new CheckOutRes(c.getId(),c.getCode(),c.getDate(),c.getAgent().getName())).toList();
    }

    @Override
    public List<CheckOutResDetail> getByCheckoutId(String checkoutId) {
        var detailId = validateId(checkoutId);
        return checkOutDetailRepo
                .findByCheckOutId(detailId)
                .stream()
                .map(detail -> new CheckOutResDetail(
                        detail.getProduct().getName(),
                        detail.getQuantity()
                ))
                .toList();
    }

    @Override
    @Transactional
    public CreateResDTO create(CreateCheckOutReq req) {
        var agentId = validateId(req.getAgentId());
        Agent agent = agentRepo.findById(agentId)
                .orElseThrow(() -> new NotFoundException("Agent Not Found"));

        CheckOut checkout = new CheckOut();
        checkout.setCode(generateRandomAlphaNumeric(5));
        checkout.setDate(LocalDateTime.now());
        checkout.setAgent(agent);
        createBase(checkout);

        checkOutRepo.save(checkout);

        HistoryStatus statusOut = historyStatusRepo.findByCode(Status.OUT.name())
                .orElseThrow(() -> new NotFoundException("History Status IN Not Found"));

        createDetail(req, checkout, statusOut);

        return new CreateResDTO(checkout.getId(), "Check Out Created");
    }

    private void createDetail(CreateCheckOutReq req, CheckOut checkout, HistoryStatus statusOut) {
        var date = LocalDateTime.now();
        var products = new HashSet<>();

        for (CheckOutDataReq data : req.getDataReqList()) {
            var productId = validateId(data.getProductId());

            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product Not Found"));

            if (data.getQuantity() > product.getStock()) {
                throw new NotAllowedStateException(
                        "Stock product " + product.getName() + " Not Sufficient"
                );
            }

            if(!products.add(productId)){
                throw new DuplicateResourceException("Duplicate product in Checkout data");
            }

            product.setStock(product.getStock() - data.getQuantity());
            productRepo.save(product);

            CheckOutDetail detail = new CheckOutDetail();
            detail.setDate(date);
            detail.setQuantity(data.getQuantity());
            detail.setProduct(product);
            detail.setCheckOut(checkout);
            createBase(detail);

            checkOutDetailRepo.save(detail);
            createHistory(product, data.getQuantity(), statusOut);
        }
    }

    private void createHistory(Product product,
                               Integer quantity,
                               HistoryStatus status) {

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
