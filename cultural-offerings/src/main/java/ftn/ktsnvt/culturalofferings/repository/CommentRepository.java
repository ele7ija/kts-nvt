package ftn.ktsnvt.culturalofferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
