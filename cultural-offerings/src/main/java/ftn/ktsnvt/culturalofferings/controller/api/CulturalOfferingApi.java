package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;

import org.springframework.http.ResponseEntity;
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
    ResponseEntity<CulturalOfferingDTO> createCulturalOffering(@RequestBody CulturalOfferingDTO body);


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
    ResponseEntity<CulturalOfferingDTO> updateCulturalOffering(@RequestBody CulturalOfferingDTO body,  @PathVariable Long id);
    
    @RequestMapping(value = "/culturalOffering/{id}/uploadImage",
            produces = { "application/json" },
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
    ResponseEntity<CulturalOffering> uploadImageCulturalOffering(@PathVariable("id") String id, @RequestPart(value="file", required=true) MultipartFile file);


}

