package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.checkout.CheckOutRes;
import com.dansmultipro.vemanis.dto.checkout.CheckOutResDetail;
import com.dansmultipro.vemanis.dto.checkout.CreateCheckOutReq;

import java.util.List;

public interface CheckOutService {
    List<CheckOutRes> getAll();
    List<CheckOutResDetail> getByCheckoutId(String checkoutId);
    CreateResDTO create(CreateCheckOutReq req);
}
