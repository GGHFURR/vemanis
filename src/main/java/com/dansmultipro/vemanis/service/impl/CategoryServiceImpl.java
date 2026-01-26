package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.category.CategoryRes;
import com.dansmultipro.vemanis.dto.category.CreateCategoryReq;
import com.dansmultipro.vemanis.dto.category.UpdateCategoryReq;
import com.dansmultipro.vemanis.exception.BadRequestException;
import com.dansmultipro.vemanis.exception.DuplicateResourceException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.model.Category;
import com.dansmultipro.vemanis.repository.CategoryRepo;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.CategoryService;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImpl extends BaseService implements CategoryService {

    private final CategoryRepo categoryRepo;


    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<CategoryRes> getAll(){
        return categoryRepo.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public CategoryRes getbyId(String id){
        return categoryRepo.findById(UUID.fromString(id)).map(this::mapToResponse)
                .orElseThrow(() -> new NotFoundException("Agent Tidak Ditemukan"));
    }

    @Transactional
    @Override
    public CreateResDTO create(CreateCategoryReq req){
        if(categoryRepo.existsByName(req.getName())){
            throw new BadRequestException("Please Input Another Name");
        }

        Category category = new Category();
        category.setCode(req.getCode());
        category.setName(req.getName());
        createBase(category);

        return new CreateResDTO(category.getId(),"Category Created");
    }

    @Transactional
    @Override
    public UpdateResDTO update(String id, UpdateCategoryReq req){
        var category = categoryRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Data Agent Tidak Ditemukan"));

        if(category.getName().equals(req.getName())){
            Optional<Category> existingCategory = categoryRepo.findByName(req.getName());
            if(existingCategory.isPresent() && existingCategory.get().getCode().equals(req.getCode())){
                throw new DuplicateResourceException("Data Is Not Unique");
            }
        }

        if(!category.getVersion().equals(req.getVersion())){
            throw new BadRequestException("Please Refresh The Page");
        }

        category.setName(req.getName());
        category.setCode(req.getCode());

        updateBase(category);

        categoryRepo.saveAndFlush(category);

        return new UpdateResDTO(category.getVersion(),"Updated");

    }

    @Override
    public DeleteResDTO delete(String id){
        categoryRepo.deleteById(UUID.fromString(id));
        return new DeleteResDTO("Deleted");
    }


    private CategoryRes mapToResponse(Category category){
        return new CategoryRes(category.getId(), category.getCode(), category.getName());
    }
}
