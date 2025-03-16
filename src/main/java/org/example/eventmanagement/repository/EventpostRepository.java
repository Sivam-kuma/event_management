package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.Eventpost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventpostRepository extends JpaRepository<Eventpost, Long> {

    // Custom query to filter by multiple fields (with OR condition) for each field, optionally including userid
    @Query("SELECT e FROM Eventpost e WHERE " +
            "(:userid IS NULL OR e.userid = :userid) " +
            "AND (:name IS NULL OR :name = '' OR e.name LIKE %:name%) " + // Treat empty string as NULL
            "AND (:description IS NULL OR :description = '' OR e.description LIKE %:description%) " + // Treat empty string as NULL
            "AND (:venue IS NULL OR :venue = '' OR e.venue LIKE %:venue%) " + // Treat empty string as NULL
            "AND (:image IS NULL OR :image = '' OR e.image LIKE %:image%) " + // Treat empty string as NULL
            "AND (:category IS NULL OR :category = '' OR e.category LIKE %:category%) " + // Treat empty string as NULL
            "AND (:artist IS NULL OR :artist = '' OR e.artist LIKE %:artist%) " + // Treat empty string as NULL
            "AND (:fare IS NULL OR :fare = '' OR e.fare <= :fare) " + // Treat empty string as NULL
            "AND (:searchQuery IS NULL OR :searchQuery = '' OR " + // Treat empty string as NULL
            "e.name LIKE %:searchQuery% OR " +
            "e.description LIKE %:searchQuery% OR " +
            "e.venue LIKE %:searchQuery% OR " +
            "e.artist LIKE %:searchQuery% OR " +
            "e.category LIKE %:searchQuery%)") // Add searchQuery logic
    Page<Eventpost> findByDynamicFilters(
            @Param("userid") Long userid,
            @Param("name") String name,
            @Param("description") String description,
            @Param("venue") String venue,
            @Param("image") String image,
            @Param("category") String category,
            @Param("artist") String artist,
            @Param("fare") String fare,
            @Param("searchQuery") String searchQuery, // Add searchQuery parameter
            Pageable pageable);

    @Query ("SELECT DISTINCT e.artist from  Eventpost e")
    List<String> findArtists();

    @Query ("SELECT DISTINCT e.category FROM Eventpost e")
    List<String> findCategories();
}


