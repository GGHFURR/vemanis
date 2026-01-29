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
import com.dansmultipro.vemanis.exception.ResourceConflictException;
import com.dansmultipro.vemanis.model.Category;
import com.dansmultipro.vemanis.repository.CategoryRepo;
import com.dansmultipro.vemanis.repository.ProductRepo;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<CategoryRes> getAll(){
        return categoryRepo.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public CategoryRes getById(String id){
        var categoryId = validateId(id);
        return categoryRepo.findById(categoryId).map(this::mapToResponse)
                .orElseThrow(() -> new NotFoundException("Data Category Not Found"));
    }

    @Transactional
    @Override
    public CreateResDTO create(CreateCategoryReq req){
        if(categoryRepo.existsByName(req.getName())){
            throw new BadRequestException("Please Input Another Name");
        }
        if(categoryRepo.existsByCode(req.getCode())){
            throw new BadRequestException("Please Input Another Code");
        }

        Category category = new Category();
        category.setCode(req.getCode());
        category.setName(req.getName());
        createBase(category);

        categoryRepo.save(category);

        return new CreateResDTO(category.getId(),"Category Created");
    }

    @Transactional
    @Override
    public UpdateResDTO update(String id, UpdateCategoryReq req){
        var categoryId = validateId(id);

        var category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Data Category Not Found"));

        if(!category.getName().equals(req.getName())){
            Optional<Category> existingByName = categoryRepo.findByName(req.getName());
            if(existingByName.isPresent()){
                throw new DuplicateResourceException("Category Name Already Exists");
            }
        }

        if(!category.getCode().equals(req.getCode())){
            Optional<Category> existingByCode = categoryRepo.findByCode(req.getCode());
            if(existingByCode.isPresent()){
                throw new DuplicateResourceException("Category Code Already Exists");
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
        var categoryId = validateId(id);
        var category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Data Category Not Found"));

        if(productRepo.existsByCategory(category)){
            throw new ResourceConflictException("Data Can't Be Deleted Because Having Relation In Product");
        }

        categoryRepo.delete(category);
        return new DeleteResDTO("Deleted");
    }

    private CategoryRes mapToResponse(Category category){
        return new CategoryRes(category.getId(), category.getCode(), category.getName());
    }
}
