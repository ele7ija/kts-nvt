package ftn.ktsnvt.culturalofferings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;

@Repository
public interface CulturalOfferingSubtypeRepository extends JpaRepository<CulturalOfferingSubType, Long> {
	
	Optional<CulturalOfferingSubType> findBySubTypeName(String name);

}
