package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingTypeApi;
<<<<<<< HEAD
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.helper.CulturalOfferingTypeMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

=======

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.awt.print.Pageable;
>>>>>>> main
import java.util.List;

@Controller
public class CulturalOfferingTypeController implements CulturalOfferingTypeApi {

    @Autowired
    private CulturalOfferingTypeService culturalOfferingTypeService;

<<<<<<< HEAD
    private CulturalOfferingTypeMapper culturalOfferingTypeMapper;


    @Override
    public ResponseEntity<List<CulturalOfferingType>> findAll() {
        return new ResponseEntity<>(
                culturalOfferingTypeService.findAll(),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Page<CulturalOfferingType>> findAll(Pageable pageable){
        Page<CulturalOfferingType> page = culturalOfferingTypeService.findAll(pageable);
        Page<CulturalOfferingType> pageCulturalOfferingSubTypes = new PageImpl<>(page.toList(),page.getPageable(),page.getTotalElements());
        return new ResponseEntity<>(pageCulturalOfferingSubTypes, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CulturalOfferingType> findOne(Long id) {
        CulturalOfferingType culturalOfferingType = culturalOfferingTypeService.findOne(id);
        return new ResponseEntity<>(
                culturalOfferingType,
                culturalOfferingType == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingType> create(CulturalOfferingTypeDTO body) {
        return new ResponseEntity<>(
                culturalOfferingTypeService.create(culturalOfferingTypeMapper.toEntity(body)),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingType> update(CulturalOfferingTypeDTO body, Long id) {
        CulturalOfferingType culturalOfferingSubType = culturalOfferingTypeMapper.toEntity(body);
        return new ResponseEntity<>(
                culturalOfferingTypeService.update(culturalOfferingSubType, id),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        culturalOfferingTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
=======

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
>>>>>>> main
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


