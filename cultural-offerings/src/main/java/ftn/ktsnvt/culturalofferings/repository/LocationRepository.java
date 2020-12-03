package ftn.ktsnvt.culturalofferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

}
