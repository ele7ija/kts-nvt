package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.dto.CommentDTO;
import ftn.ktsnvt.culturalofferings.mapper.CommentMapper;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private ImageService imageService;
    private UserService userService;
    private CulturalOfferingService culturalOfferingService;

    @Autowired
    public CommentService(CommentRepository commentRepository, ImageService imageService, UserService userService, CulturalOfferingService culturalOfferingService) {
        this.commentRepository = commentRepository;
        this.imageService = imageService;
        this.userService = userService;
        this.culturalOfferingService = culturalOfferingService;
    }


    public List<CommentDTO> findAll() {
        var entities = commentRepository.findAll();

        return CommentMapper.toDTOs(entities);
    }

    public Page<CommentDTO> findAll(Pageable pageable) {
        var commentsPage = commentRepository.findAll(pageable);
        List<Comment> commentList = commentsPage.toList();

        var commentDTOs = CommentMapper.toDTOs(commentList);

        return new PageImpl<>(commentDTOs, commentsPage.getPageable(), commentsPage.getTotalElements());
    }

    public Page<CommentDTO> findAll(Pageable pageable, Long culturalOfferingId){
        var commentsPage = commentRepository.findAllByCulturalOfferingId(culturalOfferingId, pageable);
        List<Comment> commentList = commentsPage.toList();

        var commentDTOs = CommentMapper.toDTOs(commentList);

        return new PageImpl<>(commentDTOs, commentsPage.getPageable(), commentsPage.getTotalElements());
    }

    public List<Comment> findAll(List<Long> commentIds) {
        return commentIds.stream()
                .map(commentId -> this.getEntityById(commentId))
                .collect(Collectors.toList());
    }

    public CommentDTO findOne(Long id) {
        Comment comment = this.getEntityById(id);

        return CommentMapper.toDTO(comment);
    }

    public CommentDTO create(CommentDTO commentDTO) {
        Comment entity = this.dtoToEntity(commentDTO);

        entity = commentRepository.save(entity);

        return CommentMapper.toDTO(entity);
    }

    public CommentDTO update(CommentDTO commentDTO, Long id) {
        // check if entity exists
        this.getEntityById(id);

        // update
        var entity = this.dtoToEntity(commentDTO);
        entity.setId(id);

        var updatedEntity = commentRepository.save(entity);

        return CommentMapper.toDTO(updatedEntity);
    }

    public void delete(Long id) {
        Comment comment = this.getEntityById(id);

        commentRepository.delete(comment);
    }

    private Comment getEntityById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) throw new EntityNotFoundException(id, Comment.class);

        return comment;
    }

    private Comment dtoToEntity(CommentDTO dto) {
        List<ImageModel> images = imageService.findAll(dto.getImageIds());
        var culturalOffering = culturalOfferingService.findOne(dto.getCulturalOfferingId());
        var user = userService.findOne(dto.getUserId());

        return CommentMapper.toEntity(dto, images, culturalOffering, user);
    }
}

