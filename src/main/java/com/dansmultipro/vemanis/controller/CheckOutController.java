package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.checkout.CheckOutRes;
import com.dansmultipro.vemanis.dto.checkout.CheckOutResDetail;
import com.dansmultipro.vemanis.dto.checkout.CreateCheckOutReq;
import com.dansmultipro.vemanis.service.CheckOutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

    private final CheckOutService checkOutService;

    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @GetMapping
    public ResponseEntity<List<CheckOutRes>> getAll() {
        var res = checkOutService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{checkoutId}/detail")
    public ResponseEntity<List<CheckOutResDetail>> getDetailByCheckoutId(
            @PathVariable String checkoutId) {

        var res = checkOutService.getByCheckoutId(checkoutId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> create(
            @Valid @RequestBody CreateCheckOutReq req) {

        var res = checkOutService.create(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}


