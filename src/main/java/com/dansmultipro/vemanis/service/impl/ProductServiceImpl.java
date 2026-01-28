package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.product.CreateProductReq;
import com.dansmultipro.vemanis.dto.product.ProductRes;
import com.dansmultipro.vemanis.dto.product.UpdateProductReq;
import com.dansmultipro.vemanis.exception.BadRequestException;
import com.dansmultipro.vemanis.exception.DuplicateResourceException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.exception.ResourceConflictException;
import com.dansmultipro.vemanis.model.Category;
import com.dansmultipro.vemanis.model.Product;
import com.dansmultipro.vemanis.repository.CategoryRepo;
import com.dansmultipro.vemanis.repository.ProductRepo;
import com.dansmultipro.vemanis.repository.StockHistoryRepo;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final StockHistoryRepo historyRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, StockHistoryRepo historyRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.historyRepo = historyRepo;
    }

    @Override
    public List<ProductRes> getAll() {
        return productRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductRes getById(String id) {
        var productId = validateId(id);
        return productRepo.findById(productId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));
    }

    @Transactional
    @Override
    public CreateResDTO create(CreateProductReq req) {

        if (productRepo.existsByCode(req.getCode())) {
            throw new DuplicateResourceException("Product code already exists");
        }

        Category category = categoryRepo.findById(UUID.fromString(req.getCategoryId()))
                .orElseThrow(() -> new NotFoundException("Category Not Found"));

        Product product = new Product();
        product.setCode(req.getCode());
        product.setName(req.getName());
        product.setCategory(category);
        product.setStock(0);

        createBase(product);
        productRepo.save(product);

        return new CreateResDTO(product.getId(), "Product Created");
    }

    @Transactional
    @Override
    public UpdateResDTO update(String id, UpdateProductReq req) {
        var productId = validateId(id);
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        if (!product.getVersion().equals(req.getVersion())) {
            throw new BadRequestException("Please Refresh The Page");
        }

        Category category = categoryRepo.findById(UUID.fromString(req.getCategory()))
                .orElseThrow(() -> new NotFoundException("Category Not Found"));

        product.setCode(req.getCode());
        product.setName(req.getName());
        product.setCategory(category);

        updateBase(product);
        productRepo.saveAndFlush(product);

        return new UpdateResDTO(product.getVersion(), "Updated");
    }

    @Override
    public DeleteResDTO delete(String id) {
        var productId = validateId(id);
        var product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        if(historyRepo.existsByProduct(product)){
            throw new ResourceConflictException("Data Cant Be Deleted Because Having Relation In Transaction Record");
        }

        productRepo.delete(product);
        return new DeleteResDTO("Deleted");
    }

    private ProductRes mapToResponse(Product product) {
        ProductRes res = new ProductRes();
        res.setId(product.getId());
        res.setCode(product.getCode());
        res.setName(product.getName());
        res.setStock(product.getStock());
        res.setCategory(product.getCategory().getName());
        return res;
    }
}
