package ftn.ktsnvt.culturalofferings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;

@Repository
public interface CulturalOfferingRepository extends JpaRepository<CulturalOffering, Long> {

	Optional<CulturalOffering> findByName(String name);

    List<CulturalOffering> findAllByCulturalOfferingTypeId(Long id);
}

