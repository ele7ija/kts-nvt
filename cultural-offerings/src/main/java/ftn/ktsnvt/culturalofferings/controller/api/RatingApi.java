package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.dto.RatingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/rating")
public interface RatingApi {
    @GetMapping
    ResponseEntity findAll();

    @GetMapping(value = "/by-page")
    ResponseEntity findAll(Pageable pageable);

    @GetMapping(value = "/by-page/{culturalOfferingId}")
    ResponseEntity findAll(@PathVariable("culturalOfferingId") Long culturalOfferingId, Pageable pageable);

    @GetMapping(value = "/{id}")
    ResponseEntity findOne(@PathVariable("id") Long id);

    @PutMapping(value = "/{id}")
    ResponseEntity update(@RequestBody RatingDTO body, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception;

    @PostMapping
    ResponseEntity create(@RequestBody RatingDTO body, BindingResult bindingResult) throws Exception;

    @DeleteMapping(value = "/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) throws Exception;
}
