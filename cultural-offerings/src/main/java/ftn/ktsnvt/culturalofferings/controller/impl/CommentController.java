package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CommentApi;
import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.helper.DTOValidationHelper;
import ftn.ktsnvt.culturalofferings.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

import static ftn.ktsnvt.culturalofferings.helper.ResponseHelper.created;
import static ftn.ktsnvt.culturalofferings.helper.ResponseHelper.ok;

@Controller
public class CommentController implements CommentApi {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public ResponseEntity findOne(Long id) {
        CommentDTO comment = commentService.findOne(id);

        return ok(comment);
    }

    @Override
    public ResponseEntity findAll() {
        List<CommentDTO> comments = commentService.findAll();

        return ok(comments);
    }

    @Override
    public ResponseEntity findAll(Pageable pageable) {
        Page<CommentDTO> commentPage = commentService.findAll(pageable);

        return ok(commentPage);
    }

    @Override
    public ResponseEntity findAll(Long culturalOfferingId, Pageable pageable) {
        Page<CommentDTO> commentPage = commentService.findAll(pageable, culturalOfferingId);

        return ok(commentPage);
    }

    @Override
    @PreAuthorize("hasAuthority('REVIEW:write')")
    public ResponseEntity create(@Valid CommentDTO body, BindingResult bindingResult) {
        DTOValidationHelper.validateDTO(bindingResult);

        CommentDTO newComment = commentService.create(body);

        return created(newComment);
    }

    @Override
    @PreAuthorize("hasAuthority('REVIEW:write')")
    public ResponseEntity update(@Valid CommentDTO body, BindingResult bindingResult, Long id) {
        DTOValidationHelper.validateDTO(bindingResult);

        CommentDTO comment = commentService.update(body, id);

        return ok(comment);
    }

    @Override
    @PreAuthorize("hasAuthority('REVIEW:write')")
    public ResponseEntity<Void> delete(Long id) {
        commentService.delete(id);

        return ok();
    }

}
