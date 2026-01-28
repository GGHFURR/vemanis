package com.dansmultipro.vemanis.service;


import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.supplier.CreateSupplierReq;
import com.dansmultipro.vemanis.dto.supplier.SupplierRes;
import com.dansmultipro.vemanis.dto.supplier.UpdateSupplierReq;

import java.util.List;

public interface SupplierService {

    List<SupplierRes> getAll();
    CreateResDTO create(CreateSupplierReq req);
    UpdateResDTO update(String id, UpdateSupplierReq req);
    DeleteResDTO delete(String id);
}
