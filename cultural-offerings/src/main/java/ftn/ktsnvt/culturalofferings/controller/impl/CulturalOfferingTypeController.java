package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingTypeApi;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.awt.print.Pageable;
import java.util.List;

@Controller
public class CulturalOfferingTypeController implements CulturalOfferingTypeApi {

    @Autowired
    private CulturalOfferingTypeService culturalOfferingTypeService;


    @Override
    public ResponseEntity<List<CulturalOfferingType>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Page<CulturalOfferingType>> getAllByPage(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<CulturalOfferingType> get(String id) {
        return null;
    }

    @Override
    public ResponseEntity<CulturalOfferingType> create(CulturalOfferingTypeDTO body) {
        return null;
    }

    @Override
    public ResponseEntity<CulturalOfferingType> update(CulturalOfferingTypeDTO body, String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        return null;
    }
}

