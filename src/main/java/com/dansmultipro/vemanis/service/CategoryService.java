package com.dansmultipro.vemanis.service;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.category.CategoryRes;
import com.dansmultipro.vemanis.dto.category.CreateCategoryReq;
import com.dansmultipro.vemanis.dto.category.UpdateCategoryReq;

import java.util.List;

public interface CategoryService {
    List<CategoryRes> getAll();

    CategoryRes getById(String id);

    CreateResDTO create(CreateCategoryReq req);

    UpdateResDTO update(String id, UpdateCategoryReq req);

    DeleteResDTO delete(String id);
}
