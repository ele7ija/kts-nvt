package ftn.ktsnvt.culturalofferings.repository;

import ftn.ktsnvt.culturalofferings.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ftn.ktsnvt.culturalofferings.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "SELECT * FROM USER WHERE ROLE = ?1",
            countQuery = "SELECT count(*) FROM USER WHERE ROLE = ?1",
            nativeQuery = true)
    Page<User> findAllByUserRole(String userRole, Pageable pageable);
}
