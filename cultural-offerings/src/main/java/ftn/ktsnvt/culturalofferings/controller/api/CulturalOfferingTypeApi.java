package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

<<<<<<< HEAD
import org.springframework.data.domain.Pageable;
=======
import java.awt.print.Pageable;
>>>>>>> main
import java.util.List;


@RequestMapping(value = "/cultural-offerings-type")
public interface CulturalOfferingTypeApi {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
    ResponseEntity<List<CulturalOfferingType>> findAll();

    @RequestMapping(value = "/by-page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CulturalOfferingType>> findAll(Pageable pageable);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CulturalOfferingType> findOne(@PathVariable("id") Long id);
=======
    ResponseEntity<List<CulturalOfferingType>> getAll();

    @RequestMapping(value = "/by-page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CulturalOfferingType>> getAllByPage(Pageable pageable);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CulturalOfferingType> get(@PathVariable("id") String id);
>>>>>>> main

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CulturalOfferingType> create(@RequestBody CulturalOfferingTypeDTO body);

    @RequestMapping(value= "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
    ResponseEntity<CulturalOfferingType> update(@RequestBody CulturalOfferingTypeDTO body, @PathVariable("id") Long id);

    @RequestMapping(value= "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
=======
    ResponseEntity<CulturalOfferingType> update(@RequestBody CulturalOfferingTypeDTO body, @PathVariable("id") String id);

    @RequestMapping(value= "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable("id") String id);
>>>>>>> main

}