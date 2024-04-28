package org.hopenghou.SpringBootApi.repository;
import org.hopenghou.SpringBootApi.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Location entity.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
