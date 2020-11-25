package ftn.ktsnvt.culturalofferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;

@Repository
public interface CulturalOfferingRepository extends JpaRepository<CulturalOffering, Long> {

}

