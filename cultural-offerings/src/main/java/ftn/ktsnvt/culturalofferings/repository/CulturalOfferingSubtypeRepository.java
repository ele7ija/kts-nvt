package ftn.ktsnvt.culturalofferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;

@Repository
public interface CulturalOfferingSubtypeRepository extends JpaRepository<CulturalOfferingSubType, Long> {

}
