package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingApi;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.helper.DTOValidationHelper;
import ftn.ktsnvt.culturalofferings.mapper.CulturalOfferingsMapper;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CulturalOfferingController implements CulturalOfferingApi {

    private static final Logger log = LoggerFactory.getLogger(CulturalOfferingController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private CulturalOfferingService culturalOfferingService;
    
    @Autowired
    private CulturalOfferingsMapper culturalOfferingsMapper;

    @org.springframework.beans.factory.annotation.Autowired
    public CulturalOfferingController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasAuthority('CULTURAL_OFFERING:write')")
    public ResponseEntity<CulturalOfferingDTO> createCulturalOffering(@Valid CulturalOfferingDTO body, BindingResult bindingResult) {
    	DTOValidationHelper.validateDTO(bindingResult);
    	CulturalOffering culturalOffering = culturalOfferingService.create(culturalOfferingsMapper.toEntity(body, null));
        return new ResponseEntity<>(culturalOfferingsMapper.toDto(culturalOffering), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('CULTURAL_OFFERING:write')")
    public ResponseEntity<CulturalOfferingDTO> updateCulturalOffering(@Valid CulturalOfferingDTO body, BindingResult bindingResult, @PathVariable Long id) {
		DTOValidationHelper.validateDTO(bindingResult);
    	CulturalOffering culturalOffering = culturalOfferingService.update(culturalOfferingsMapper.toEntity(body, id), id);
        return new ResponseEntity<>(culturalOfferingsMapper.toDto(culturalOffering), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CULTURAL_OFFERING:write')")
    public ResponseEntity<Void> deleteCulturalOffering(@PathVariable("id") Long id) {
    	culturalOfferingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CULTURAL_OFFERING:read')")
    public ResponseEntity<CulturalOfferingDTO> getCulturalOfferingByID(@PathVariable("id") Long id) {
    	CulturalOffering culturalOffering = culturalOfferingService.findOne(id);
        return new ResponseEntity<>(culturalOfferingsMapper.toDto(culturalOffering), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CULTURAL_OFFERING:read')")
    public ResponseEntity<Page<CulturalOfferingDTO>> findAll(Pageable pageable) {
        Page<CulturalOffering> page = culturalOfferingService.findAll(pageable);

        List<CulturalOfferingDTO> culturalOfferingDTO = page
                .toList()
                .stream()
                .map(x -> culturalOfferingsMapper.toDto(x))
                .collect(Collectors.toList());

        Page<CulturalOfferingDTO> pageCulturalOfferingDTO = new PageImpl<>(culturalOfferingDTO, page.getPageable(), page.getTotalElements());
        return new ResponseEntity<>(pageCulturalOfferingDTO, HttpStatus.OK);

    }

    public ResponseEntity<CulturalOffering> uploadImageCulturalOffering(@PathVariable("id") String id, @RequestPart(value="file", required=true) MultipartFile file) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CulturalOffering>(objectMapper.readValue("{  \"images\" : [ \"images\", \"images\" ],  \"newsletters\" : [ {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  }, {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  } ],  \"subscriptions\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"comments\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  } ],  \"subtype\" : {    \"name\" : \"name\"  },  \"ratings\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  } ],  \"name\" : \"name\",  \"description\" : \"description\",  \"location\" : {    \"latitude\" : 6.02745618307040320615897144307382404804229736328125,    \"name\" : \"name\",    \"longitude\" : 0.80082819046101150206595775671303272247314453125  },  \"type\" : {    \"name\" : \"name\",    \"icon\" : \"icon\"  }}", CulturalOffering.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CulturalOffering>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CulturalOffering>(HttpStatus.NOT_IMPLEMENTED);
    }

    

}
