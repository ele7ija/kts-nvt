package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.data.domain.Pageable;
import java.util.List;


@RequestMapping(value = "/cultural-offerings-types")
public interface CulturalOfferingTypeApi {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CulturalOfferingTypeDTO>> findAll();

    @RequestMapping(value = "/by-page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CulturalOfferingTypeDTO>> findAll(Pageable pageable);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CulturalOfferingTypeDTO> findOne(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CulturalOfferingTypeDTO> create(@RequestBody CulturalOfferingTypeDTO body, BindingResult bindingResult);

    @RequestMapping(value= "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CulturalOfferingTypeDTO> update(@RequestBody CulturalOfferingTypeDTO body, BindingResult bindingResult, @PathVariable("id") Long id);

    @RequestMapping(value= "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
}