package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.dto.SearchFilterDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/cultural-offerings")
public interface CulturalOfferingApi {
    @RequestMapping(value = "",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<CulturalOfferingDTO> createCulturalOffering(@RequestBody CulturalOfferingDTO body, BindingResult bindingResult);


    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCulturalOffering(@PathVariable("id") Long id);


    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CulturalOfferingDTO> getCulturalOfferingByID(@PathVariable("id") Long id);


    @RequestMapping(value = "/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<CulturalOfferingDTO> updateCulturalOffering(@RequestBody CulturalOfferingDTO body, BindingResult bindingResult, @PathVariable Long id);
    
    @RequestMapping(value = "/culturalOffering/{id}/uploadImage",
            produces = { "application/json" },
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
    ResponseEntity<CulturalOffering> uploadImageCulturalOffering(@PathVariable("id") String id, @RequestPart(value="file", required=true) MultipartFile file);

    @RequestMapping(value = "/by-page", 
    		method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CulturalOfferingDTO>> findAll(Pageable pageable);
    
    @RequestMapping(value = "/search-filter/by-page", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CulturalOfferingDTO>> searchFilter(Pageable pageable, @RequestBody SearchFilterDTO searchFilterDTO);
}

