package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingTypeApi;
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
import java.util.List;

@Controller
public class CulturalOfferingTypeController implements CulturalOfferingTypeApi {
    @Autowired
    private CulturalOfferingTypeService culturalOfferingTypeService;

    private CulturalOfferingTypeMapper culturalOfferingTypeMapper;


    @Override
    public ResponseEntity<List<CulturalOfferingType>> findAll() {
        return new ResponseEntity<>(
                culturalOfferingTypeService.findAll(),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Page<CulturalOfferingSubType>> findAll(Pageable pageable) {
        Page<CulturalOfferingSubType> page = culturalOfferingTypeMapper.findAll(pageable);
        Page<CulturalOfferingSubType> pageCulturalOfferingSubTypes = new PageImpl<>(page.toList(),page.getPageable(),page.getTotalElements());
        return new ResponseEntity<>(pageCulturalOfferingSubTypes, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CulturalOfferingSubType> get(Long id) {
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubtypeService.findOne(id);
        return new ResponseEntity<>(
                culturalOfferingSubType,
                culturalOfferingSubType == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingSubType> create(CulturalOfferingSubTypeDTO body) {
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubtypeService.create(culturalOfferingSubTypeMapper.toEntity(body));
        return new ResponseEntity<>(
                culturalOfferingSubtypeService.create(culturalOfferingSubType),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingSubType> update(CulturalOfferingSubTypeDTO body, Long id) {
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubTypeMapper.toEntity(body);
        return new ResponseEntity<>(
                culturalOfferingSubtypeService.update(culturalOfferingSubType, id),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        culturalOfferingSubtypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


