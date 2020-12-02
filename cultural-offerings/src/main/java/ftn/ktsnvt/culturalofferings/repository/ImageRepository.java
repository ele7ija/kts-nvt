package ftn.ktsnvt.culturalofferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.ImageModel;

import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {

	Optional<ImageModel> findByName(String name);
	
}
