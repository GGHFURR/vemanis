package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.product.CreateProductReq;
import com.dansmultipro.vemanis.dto.product.ProductRes;
import com.dansmultipro.vemanis.dto.product.UpdateProductReq;
import com.dansmultipro.vemanis.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductRes>> getAll() {
        var res = productService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRes> getById(@PathVariable String id) {
        var res = productService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> create(@Valid @RequestBody CreateProductReq req) {
        var res = productService.create(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDTO> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateProductReq req
    ) {
        var res = productService.update(id, req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDTO> delete(@PathVariable String id) {
        var res = productService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
