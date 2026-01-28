package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.supplier.CreateSupplierReq;
import com.dansmultipro.vemanis.dto.supplier.SupplierRes;
import com.dansmultipro.vemanis.dto.supplier.UpdateSupplierReq;
import com.dansmultipro.vemanis.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierRes>> getAll() {
        var res = supplierService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> create(
            @RequestBody @Valid CreateSupplierReq req
    ) {
        var res = supplierService.create(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResDTO> update(
            @PathVariable String id,
            @RequestBody @Valid UpdateSupplierReq req
    ) {
        var res = supplierService.update(id, req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResDTO> delete(
            @PathVariable String id
    ) {
        var res = supplierService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
