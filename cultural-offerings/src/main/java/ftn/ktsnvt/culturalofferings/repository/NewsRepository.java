package ftn.ktsnvt.culturalofferings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
