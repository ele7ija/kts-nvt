package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CommentApi;
import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.helper.CommentMapper;
import ftn.ktsnvt.culturalofferings.model.Comment;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<CommentDTO> createComment(CommentDTO body) {
        Comment comment;
        try {
            comment = commentService.create(commentMapper.toEntity(body));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(commentMapper.toDto(comment), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long id) {
        try {
            commentService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommentDTO> getCommentByID(Long id) {
        Comment comment;
        try {
            comment = commentService.findOne(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(commentMapper.toDto(comment), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<CommentDTO>> getAllComments(Pageable pageable) {
        Page<Comment> page;
        Page<CommentDTO> dtopage;
        try {
            page = commentService.findAll(pageable);
            List<CommentDTO> dtos = toCommentDTOList(page.toList());
            dtopage = new PageImpl<>(dtos,page.getPageable(),page.getTotalElements());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dtopage, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommentDTO> updateComments(CommentDTO body, Long id) {
        Comment comment = commentMapper.toEntity(body);
        try {
            comment = commentService.update(comment, id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(commentMapper.toDto(comment), HttpStatus.CREATED);
    }

    private List<CommentDTO> toCommentDTOList(List<Comment> comments){
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOS.add(commentMapper.toDto(comment));
        }
        return commentDTOS;
    }
}
