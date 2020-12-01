package ftn.ktsnvt.culturalofferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.Image;

import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	Optional<Image> findByName(String name);
	
}
