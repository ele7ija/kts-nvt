package ftn.ktsnvt.culturalofferings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;

@Repository
public interface CulturalOfferingTypeRepository extends JpaRepository<CulturalOfferingType, Long> {

	Optional<CulturalOfferingType> findByName(String name);
	
}
