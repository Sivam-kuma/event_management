package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.Eventpost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface EventpostRepository extends JpaRepository<Eventpost, Long> {

    // Custom query to filter by multiple fields (with OR condition) for each field, optionally including userid
    @Query("SELECT e FROM Eventpost e WHERE " +
            "(:userid IS NULL OR e.userid = :userid) " +
            "AND (:name IS NULL OR e.name LIKE %:name%) " +
            "AND (:description IS NULL OR e.description LIKE %:description%) " +
            "AND (:venue IS NULL OR e.venue LIKE %:venue%) " +
            "AND (:image IS NULL OR e.image LIKE %:image%) " +
            "AND (:category IS NULL OR e.category LIKE %:category%) " +
            "AND (:artist IS NULL OR e.artist LIKE %:artist%) " +
            "AND (:fare IS NULL OR e.fare LIKE %:fare%)")
    Page<Eventpost> findByDynamicFilters(@Param("userid") Long userid,  // userid can be null
                                         @Param("name") String name,
                                         @Param("description") String description,
                                         @Param("venue") String venue,
                                         @Param("image") String image,
                                         @Param("category") String category,
                                         @Param("artist") String artist,
                                         @Param("fare") String fare,
                                         Pageable pageable);
}


