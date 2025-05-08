package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.Eventpost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface EventpostRepository extends JpaRepository<Eventpost, Long> {

    // Custom query to filter by multiple fields (with OR condition) for each field, optionally including userid
    @Query("SELECT e FROM Eventpost e WHERE " +
            "(:userid IS NULL OR e.userid = :userid) " +
            "AND (:name IS NULL OR :name = '' OR e.name LIKE %:name%) " +
            "AND (:description IS NULL OR :description = '' OR e.description LIKE %:description%) " +
            "AND (:venue IS NULL OR :venue = '' OR e.venue LIKE %:venue%) " +
            "AND (:image IS NULL OR :image = '' OR e.image LIKE %:image%) " +
            "AND (:category IS NULL OR :category = '' OR e.category LIKE %:category%) " +
            "AND (:artist IS NULL OR :artist = '' OR e.artist LIKE %:artist%) " +
            "AND (:fare IS NULL OR :fare = '' OR e.fare <= :fare) " +
            "AND (:searchQuery IS NULL OR :searchQuery = '' OR " +
            "e.name LIKE %:searchQuery% OR " +
            "e.description LIKE %:searchQuery% OR " +
            "e.venue LIKE %:searchQuery% OR " +
            "e.artist LIKE %:searchQuery% OR " +
            "e.category LIKE %:searchQuery%)")
    Page<Eventpost> findByDynamicFilters(  // Changed from Page to List
                                           @Param("userid") Long userid,
                                           @Param("name") String name,
                                           @Param("description") String description,
                                           @Param("venue") String venue,
                                           @Param("image") String image,
                                           @Param("category") String category,
                                           @Param("artist") String artist,
                                           @Param("fare") String fare,
                                           @Param("searchQuery") String searchQuery,
                                           Pageable pageable);

    @Query ("SELECT DISTINCT e.artist from  Eventpost e")
    List<String> findArtists();

    @Query ("SELECT DISTINCT e.category FROM Eventpost e")
    List<String> findCategories();

    Eventpost findById(long id);
    @Query("SELECT e FROM Eventpost e ORDER BY e.count DESC LIMIT 5")
    List<Eventpost> findTop5ByOrderByCountDesc();

}


