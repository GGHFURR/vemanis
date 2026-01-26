package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.checkout.CheckOutRes;
import com.dansmultipro.vemanis.dto.checkout.CheckOutResDetail;

import java.util.List;

public interface CheckOutService {
    List<CheckOutRes> getAll();

    CheckOutResDetail getById(String id);
}
