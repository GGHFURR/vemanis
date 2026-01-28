package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.product.CreateProductReq;
import com.dansmultipro.vemanis.dto.product.ProductRes;
import com.dansmultipro.vemanis.dto.product.UpdateProductReq;

import java.util.List;

public interface ProductService {

    List<ProductRes> getAll();

    ProductRes getById(String id);

    CreateResDTO create(CreateProductReq req);

    UpdateResDTO update(String id, UpdateProductReq req);

    DeleteResDTO delete(String id);
}