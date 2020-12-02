package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/nvt-kts/cultural-offering")
public interface CulturalOfferingApi {
    @RequestMapping(value = "/culturalOffering",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<CulturalOffering> createCulturalOffering(@RequestBody CulturalOffering body);


    @RequestMapping(value = "/culturalOffering/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCulturalOffering(@PathVariable("id") String id);


    @RequestMapping(value = "/culturalOffering/{id}",
            produces = { "application/json", "application/xml" },
            method = RequestMethod.GET)
    ResponseEntity<CulturalOffering> getCulturalOfferingByID(@PathVariable("id") String id);


    @RequestMapping(value = "/culturalOffering",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<CulturalOffering> updateCulturalOffering(@RequestBody CulturalOffering body);


    @RequestMapping(value = "/culturalOffering/{id}/uploadImage",
            produces = { "application/json" },
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
    ResponseEntity<CulturalOffering> uploadImageCulturalOffering(@PathVariable("id") String id, @RequestPart(value="file", required=true) MultipartFile file);


}

