package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.dto.checkout.CheckOutRes;
import com.dansmultipro.vemanis.dto.checkout.CheckOutResDetail;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.model.CheckOutDetail;
import com.dansmultipro.vemanis.repository.CheckOutDetailRepo;
import com.dansmultipro.vemanis.repository.CheckOutRepo;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.CheckOutService;

import java.util.List;
import java.util.UUID;

public class CheckOutServiceImpl extends BaseService implements CheckOutService {
    private final CheckOutRepo checkOutRepo;
    private final CheckOutDetailRepo checkOutDetailRepo;


    public CheckOutServiceImpl(CheckOutRepo checkOutRepo, CheckOutDetailRepo checkOutDetailRepo) {
        this.checkOutRepo = checkOutRepo;
        this.checkOutDetailRepo = checkOutDetailRepo;
    }

    @Override
    public List<CheckOutRes> getAll(){
        return checkOutRepo.findAll().stream()
                .map(c-> new CheckOutRes(c.getId(),c.getCode(),c.getDate(),c.getAgent().toString())).toList();
    }

    @Override
    public CheckOutResDetail getById(String id) {
        CheckOutDetail detail = checkOutDetailRepo
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Check Out Detail Not Found"));

        return new CheckOutResDetail(
                detail.getProduct().getName(),
                detail.getQuantity()
        );
    }

}
