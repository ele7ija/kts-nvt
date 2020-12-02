package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingSubtypeApi;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingSubTypeDTO;
import ftn.ktsnvt.culturalofferings.helper.CulturalOfferingSubTypeMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController(value = "/cultural-offerings-subtype")
public class CulturalOfferingSubtypeController implements CulturalOfferingSubtypeApi {

    @Autowired
    private CulturalOfferingSubtypeService culturalOfferingSubtypeService;

    @Autowired
    private CulturalOfferingSubTypeMapper culturalOfferingSubTypeMapper;


    @Override
    public ResponseEntity<List<CulturalOfferingSubType>> findAll() {
        return new ResponseEntity<>(
                culturalOfferingSubtypeService.findAll(),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Page<CulturalOfferingSubType>> findAll(Pageable pageable) {
        Page<CulturalOfferingSubType> page = culturalOfferingSubtypeService.findAll(pageable);
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
