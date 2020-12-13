package ftn.ktsnvt.culturalofferings.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.ktsnvt.culturalofferings.dto.CommentDTO;

@RequestMapping(value = "/comments")
public interface CommentApi {
    @RequestMapping(value = "",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO body, BindingResult bindingResult);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteComment(@PathVariable("id") Long id);


    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<CommentDTO> getCommentByID(@PathVariable("id") Long id);

    @RequestMapping(value = "",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Page<CommentDTO>> getAllComments(Pageable pageable);

    @RequestMapping(value = "/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<CommentDTO> updateComments(@RequestBody CommentDTO body, BindingResult bindingResult, @PathVariable("id") Long id);
}
