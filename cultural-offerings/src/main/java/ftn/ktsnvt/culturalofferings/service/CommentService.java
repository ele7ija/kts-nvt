package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.repository.CommentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService implements ServiceInterface<Comment> {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public List<Comment> findAll(List<Long> commentIds){
        return commentIds.stream().map((Long commentId) -> {
            Optional<Comment> optional = commentRepository.findById(commentId);
            if(optional.isEmpty()){
                throw new EntityNotFoundException(commentId, Comment.class);
            }
            return optional.get();
        }).collect(Collectors.toList());
    }

    @Override
    public Comment findOne(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                id,
                Comment.class
            );
        return optional.get();
    }

    @Override
    public Comment create(Comment entity) {
        return commentRepository.save(entity);
    }

    @Override
    public Comment update(Comment entity, Long id) {
        Optional<Comment> optional =  commentRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                id,
                Comment.class
            );
        entity.setId(id);
        return commentRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if(optional.isEmpty())
            throw new EntityNotFoundException(
                id,
                Comment.class
            );
        commentRepository.delete(optional.get());
    }
}

