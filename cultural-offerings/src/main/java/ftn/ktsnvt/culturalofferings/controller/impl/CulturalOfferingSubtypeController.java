package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingSubtypeApi;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingSubTypeDTO;
import ftn.ktsnvt.culturalofferings.helper.CulturalOfferingSubTypeMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CulturalOfferingSubtypeController implements CulturalOfferingSubtypeApi {

    @Autowired
    private CulturalOfferingSubtypeService culturalOfferingSubtypeService;

    @Autowired
    private CulturalOfferingSubTypeMapper culturalOfferingSubTypeMapper;


    @Override
    public ResponseEntity<List<CulturalOfferingSubTypeDTO>> findAll() {
        return new ResponseEntity<>(
                culturalOfferingSubtypeService
                        .findAll()
                        .stream()
                        .map(x -> culturalOfferingSubTypeMapper.toDto(x))
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Page<CulturalOfferingSubTypeDTO>> findAll(Pageable pageable) {
        Page<CulturalOfferingSubType> page = culturalOfferingSubtypeService.findAll(pageable);

        List<CulturalOfferingSubTypeDTO> culturalOfferingSubTypesDTO = page
                .toList()
                .stream()
                .map(x -> culturalOfferingSubTypeMapper.toDto(x))
                .collect(Collectors.toList());

        Page<CulturalOfferingSubTypeDTO> pageCulturalOfferingSubTypesDTO = new PageImpl<>(culturalOfferingSubTypesDTO,page.getPageable(),page.getTotalElements());
        return new ResponseEntity<>(pageCulturalOfferingSubTypesDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CulturalOfferingSubTypeDTO> get(Long id) {
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubtypeService.findOne(id);
        return new ResponseEntity<>(
                culturalOfferingSubTypeMapper.toDto(culturalOfferingSubType),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingSubTypeDTO> create(@Valid CulturalOfferingSubTypeDTO body, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    CulturalOfferingSubTypeDTO.class
            );
        }
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubtypeService.create(culturalOfferingSubTypeMapper.toEntity(body));
        return new ResponseEntity<>(
                culturalOfferingSubTypeMapper.toDto(culturalOfferingSubType),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<CulturalOfferingSubTypeDTO> update(@Valid CulturalOfferingSubTypeDTO body, BindingResult bindingResult, Long id) {
        if(bindingResult.hasErrors()){
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    CulturalOfferingSubTypeDTO.class
            );
        }
        CulturalOfferingSubType culturalOfferingSubType = culturalOfferingSubtypeService.update(culturalOfferingSubTypeMapper.toEntity(body), id);
        return new ResponseEntity<>(
                culturalOfferingSubTypeMapper.toDto(culturalOfferingSubType),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        culturalOfferingSubtypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
