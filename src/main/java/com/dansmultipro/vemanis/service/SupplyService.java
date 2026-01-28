package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.supply.CreateSupplyReq;
import com.dansmultipro.vemanis.dto.supply.SupplyRes;
import com.dansmultipro.vemanis.dto.supply.SupplyResDetail;

import java.util.List;

public interface SupplyService {

    List<SupplyRes> getAll();

    List<SupplyResDetail> getDetailBySupplyId(String supplyId);

    CreateResDTO create(CreateSupplyReq req);
}

