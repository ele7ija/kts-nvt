package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CommentApi;
import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.helper.CommentMapper;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import ftn.ktsnvt.culturalofferings.service.CommentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController implements CommentApi {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private CommentMapper commentMapper;

    @org.springframework.beans.factory.annotation.Autowired
    public CommentController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<CommentDTO> createComment(@Valid CommentDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    Comment.class
            );
        }
        Comment comment = commentService.create(commentMapper.toEntity(body));
        return new ResponseEntity<>(commentMapper.toDto(comment), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommentDTO> getCommentByID(Long id) {
        Comment comment = commentService.findOne(id);
        return new ResponseEntity<>(commentMapper.toDto(comment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CommentDTO>> getAllComments(Pageable pageable) {
        Page<Comment> page = commentService.findAll(pageable);
        List<CommentDTO> dtos = toCommentDTOList(page.toList());
        Page<CommentDTO> dtopage = new PageImpl<>(dtos,page.getPageable(),page.getTotalElements());
        return new ResponseEntity<>(dtopage, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommentDTO> updateComments(@Valid CommentDTO body, BindingResult bindingResult, Long id) {
        if (bindingResult.hasErrors()) {
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    Comment.class
            );
        }
        Comment comment = commentService.update(commentMapper.toEntity(body), id);
        return new ResponseEntity<>(commentMapper.toDto(comment), HttpStatus.OK);
    }

    private List<CommentDTO> toCommentDTOList(List<Comment> comments){
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOS.add(commentMapper.toDto(comment));
        }
        return commentDTOS;
    }
}
