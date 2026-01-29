package com.dansmultipro.vemanis.service.impl;

import com.dansmultipro.vemanis.dto.CreateResDTO;
import com.dansmultipro.vemanis.dto.DeleteResDTO;
import com.dansmultipro.vemanis.dto.UpdateResDTO;
import com.dansmultipro.vemanis.dto.supplier.CreateSupplierReq;
import com.dansmultipro.vemanis.dto.supplier.SupplierRes;
import com.dansmultipro.vemanis.dto.supplier.UpdateSupplierReq;
import com.dansmultipro.vemanis.exception.BadRequestException;
import com.dansmultipro.vemanis.exception.DuplicateResourceException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import com.dansmultipro.vemanis.exception.ResourceConflictException;
import com.dansmultipro.vemanis.model.Supplier;
import com.dansmultipro.vemanis.repository.SupplierRepo;
import com.dansmultipro.vemanis.repository.SupplyRepo;
import com.dansmultipro.vemanis.service.BaseService;
import com.dansmultipro.vemanis.service.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl extends BaseService implements SupplierService {

    private final SupplierRepo supplierRepo;
    private final SupplyRepo supplyRepo;

    public SupplierServiceImpl(SupplierRepo supplierRepo, SupplyRepo supplyRepo) {
        this.supplierRepo = supplierRepo;
        this.supplyRepo = supplyRepo;
    }

    @Override
    public List<SupplierRes> getAll() {
        return supplierRepo.findAll().stream()
                .map(s -> {
                    SupplierRes res = new SupplierRes();
                    res.setId(s.getId());
                    res.setName(s.getName());
                    return res;
                })
                .toList();
    }

    @Override
    @Transactional
    public CreateResDTO create(CreateSupplierReq req) {
        if(supplierRepo.existsByName(req.getName())){
            throw new BadRequestException("Please Input Another Name");
        }

        Supplier supplier = new Supplier();
        supplier.setName(req.getName());
        createBase(supplier);

        supplierRepo.save(supplier);

        return new CreateResDTO(supplier.getId(), "Supplier Created");
    }

    @Override
    @Transactional
    public UpdateResDTO update(String id, UpdateSupplierReq req) {
        var supplierId = validateId(id);
        var supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        if (!supplier.getName().equals(req.getName())) {
            Optional<Supplier> existingSupplier = supplierRepo.findByName(req.getName());
            if (existingSupplier.isPresent()) {
                throw new DuplicateResourceException("Supplier Name Already Exists");
            }
        }

        if(!supplier.getVersion().equals(req.getVersion())){
            throw new BadRequestException("Please Refresh The Page");
        }

        supplier.setName(req.getName());
        updateBase(supplier);

        supplierRepo.save(supplier);

        return new UpdateResDTO(supplier.getVersion(), "Supplier Updated");
    }

    @Override
    public DeleteResDTO delete(String id){
        var supplierId = validateId(id);
        var supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        if(supplyRepo.existsBySupplier(supplier)){
            throw new ResourceConflictException("Data Cant Be Deleted Because Having Relation In Transaction Record");
        }

        supplierRepo.delete(supplier);

        return new DeleteResDTO("Deleted");
    }
}