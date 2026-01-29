package com.dansmultipro.vemanis.controller;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.supply.CreateSupplyReq;
import com.dansmultipro.vemanis.dto.supply.SupplyRes;
import com.dansmultipro.vemanis.dto.supply.SupplyResDetail;
import com.dansmultipro.vemanis.service.SupplyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplies")
public class SupplyController {

    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @GetMapping
    public ResponseEntity<List<SupplyRes>> getAll() {
        var res = supplyService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{supplyId}/detail")
    public ResponseEntity<List<SupplyResDetail>> getDetailBySupplyId(
            @PathVariable String supplyId
    ) {
        var res = supplyService.getDetailBySupplyId(supplyId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResDTO> create(
            @Valid @RequestBody CreateSupplyReq req
    ) {
        var res = supplyService.create(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
