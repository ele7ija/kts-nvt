package ftn.ktsnvt.culturalofferings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;

@Repository
public interface CulturalOfferingTypeRepository extends JpaRepository<CulturalOfferingType, Long> {

	Optional<CulturalOfferingType> findByTypeName(String name);

	List<CulturalOfferingType> findAllByTypeName(String typeName);
}
