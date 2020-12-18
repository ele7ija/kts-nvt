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
    ResponseEntity<List<RatingDTO>> findAll();

    @GetMapping(value = "/by-page")
    ResponseEntity<Page<RatingDTO>> findAll(Pageable pageable);

    @GetMapping(value = "/{id}")
    ResponseEntity<RatingDTO> findOne(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<RatingDTO> create(@RequestBody RatingDTO body, BindingResult bindingResult) throws Exception;

    @PutMapping(value= "/{id}")
    ResponseEntity<RatingDTO> update(@RequestBody RatingDTO body, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception;

    @DeleteMapping(value= "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
