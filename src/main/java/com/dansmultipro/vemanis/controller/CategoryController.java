package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.category.CategoryRes;
import com.dansmultipro.vemanis.dto.category.CreateCategoryReq;
import com.dansmultipro.vemanis.dto.category.UpdateCategoryReq;
import com.dansmultipro.vemanis.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryRes>> getAll() {
        var res = categoryService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRes> getById(@PathVariable String id) {
        var res = categoryService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> create(
            @Valid @RequestBody CreateCategoryReq req) {

        var res = categoryService.create(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDTO> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateCategoryReq req) {

        var res = categoryService.update(id, req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDTO> delete(@PathVariable String id) {
        var res = categoryService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
