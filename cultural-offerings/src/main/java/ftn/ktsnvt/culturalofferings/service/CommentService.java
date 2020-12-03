package ftn.ktsnvt.culturalofferings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.repository.CommentRepository;

import java.util.List;

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

    @Override
    public Comment findOne(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment create(Comment entity) throws Exception {
        return commentRepository.save(entity);
    }

    @Override
    public Comment update(Comment entity, Long id) throws Exception {
        Comment existingComment =  commentRepository.findById(id).orElse(null);
        if(existingComment == null){
            throw new Exception("Comment with given id doesn't exist");
        }
        entity.setId(id);
        return commentRepository.save(entity);
    }

    @Override
    public void delete(Long id) throws Exception {
        Comment existingComment = commentRepository.findById(id).orElse(null);
        if(existingComment == null){
            throw new Exception("Comment with given id doesn't exist");
        }
        commentRepository.delete(existingComment);
    }
}

