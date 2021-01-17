package ftn.ktsnvt.culturalofferings.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ftn.ktsnvt.culturalofferings.dto.CommentDTO;

import java.util.List;

@RequestMapping(value = "/comments")
public interface CommentApi {

    @GetMapping(value = "/{id}")
    ResponseEntity findOne(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<List<CommentDTO>> findAll();

    @GetMapping(value = "/by-page")
    ResponseEntity findAll(Pageable pageable);

    @GetMapping(value = "/by-page/{culturalOfferingId}")
    ResponseEntity findAll(@PathVariable("culturalOfferingId") Long culturalOfferingId, Pageable pageable);

    @PostMapping
    ResponseEntity create(@RequestBody CommentDTO body, BindingResult bindingResult);

    @PutMapping(value = "/{id}")
    ResponseEntity update(@RequestBody CommentDTO body, BindingResult bindingResult, @PathVariable("id") Long id);

    @DeleteMapping(value = "/{id}")
    ResponseEntity delete(@PathVariable("id") Long id);

}
