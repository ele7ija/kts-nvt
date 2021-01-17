package ftn.ktsnvt.culturalofferings.repository;

import ftn.ktsnvt.culturalofferings.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllById(Long id);
    Page<Rating> findAllByCulturalOfferingId(Long culturalOfferingId, Pageable pageable);
    long deleteAllByCulturalOfferingIdAndUserId(Long culturalOfferingId, Long userId);
}
